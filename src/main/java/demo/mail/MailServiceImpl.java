package demo.mail;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.SearchTerm;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import demo.base.base.domain.SystemConstantStore;
import demo.base.user.domain.bo.UserMailAndMailKeyBO;
import demo.common.domain.constant.ResourceConstant;
import emailHandle.MailHandle;
import ioHandle.FileUtilCustom;

public class MailServiceImpl {
	
	private static String mailName;
	private static String mailPwd;

	
	public Message[] searchInbox(SearchTerm st) {
		MailHandle mailHandle = new MailHandle();
		FileUtilCustom ioUtil = new FileUtilCustom();

		Resource resourceSmtpProperties = new ClassPathResource(ResourceConstant.mailSinaSmtpSslProperties);
		Resource resourceImapProperties = new ClassPathResource(ResourceConstant.mailSinaImapSslProperties);
		Properties imapProperties = null;
		Properties smtpProperties = null;
		try {
			imapProperties = ioUtil.getPropertiesFromFile(resourceImapProperties.getFile().getAbsolutePath());
			smtpProperties = ioUtil.getPropertiesFromFile(resourceSmtpProperties.getFile().getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
			return new Message[] {};
		}
		
		Store store = mailHandle.getMailStore(
				SystemConstantStore.store.get(SystemConstantStore.adminMailName), 
				SystemConstantStore.store.get(SystemConstantStore.adminMailPwd), 
				smtpProperties, 
				imapProperties
				);
		
		Folder inbox = mailHandle.getInbox(store);
		
		Message[] targetMail = mailHandle.getMailReadOnly(inbox, st);
		
		return targetMail;
	}

	
	public SearchTerm singleSearchTerm(String targetSendFrom, String targetContent, Date startDate) {
		SearchTerm searchTerm = new SearchTerm() {
			private static final long serialVersionUID = 7873209385471356176L;

			
			public boolean match(Message message) {
				Date receiveDate = null;
				try {
					receiveDate = message.getReceivedDate();
				} catch (MessagingException e1) {
					e1.printStackTrace();
					return false;
				}
				if(receiveDate.before(startDate)) {
					return false;
				}
				
				Address[] from = null;
				try {
					from = message.getFrom();
				} catch (MessagingException e) {
					e.printStackTrace();
					return false;
				}
				if(from == null || from.length < 1) {
					return false;
				}
				boolean flag = false;
				for(Address f : from) {
					if(f.toString().equals(targetSendFrom)) {
						flag = true;
					}
				}
				if(!flag) {
					return false;
				}
				
				try {
					MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
					String content = mimeMultipart.getBodyPart(0).getContent().toString();
					if(content.contains(targetContent)) {
						return true;
					}
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
			}
		};
		return searchTerm;
	}
	
	
	public SearchTerm searchByTargetContents(List<UserMailAndMailKeyBO> userMailAndMailKeyBOList) {
		SearchTerm searchTerm = new SearchTerm() {

			private static final long serialVersionUID = -4492471468971682840L;

			
			public boolean match(Message message) {
				UserMailAndMailKeyBO bo = null;
				boolean flag = false;
				for(int i = 0; i < userMailAndMailKeyBOList.size(); i ++) {
					bo = userMailAndMailKeyBOList.get(i);
					try {
						if(message.getReceivedDate().after(bo.getValidTime())) {
							continue;
						}
						String content = "";
						MimeMultipart mimeMultipart = null;
						if(message.getContent().getClass().equals(String.class)) {
							content = (String) message.getContent();
						} else if(message.getContent().getClass().equals(MimeMultipart.class)) {
							mimeMultipart = (MimeMultipart) message.getContent();
							content = mimeMultipart.getBodyPart(0).getContent().toString();
						}
						if(!content.contains(bo.getMailKey())) {
							continue;
						}
						
						Address[] from = message.getFrom();
						for(Address f : from) {
							if(f.toString().equals(bo.getEmail())) {
								flag = true;
							}
						}
						return flag;
					} catch (MessagingException e1) {
						e1.printStackTrace();
						continue;
					} catch (IOException e) {
						e.printStackTrace();
						continue;
					}
				}
				return false;
			}
		};
		return searchTerm;
	}

}
