package tool_package.email_tool.send;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;

import tool_package.email_tool.MailProperties;
import tool_package.email_tool.MailSession;

public class SendEmail {

	private static final String sendTo = "sendTo";
	private static final String sendFrom = "sendFrom";
	private static final String password = "";
	
	public static void main(String[] args) {
		SendEmail sm = new SendEmail();
		sm.sendSimpalMail();
	}
	
	public void sendSimpalMail() {

		Properties properties = MailProperties.getAliyunSmtpSslProperties();
		
		Session session = MailSession.getSmtpSslSession(properties, sendFrom, password);
		
		Message message = new MailMessageCreator().createSimpleMessage(session, sendFrom, sendTo, "测试标题3", "测试内容5");
//		List<String> attachmentsList = new ArrayList<String>();
//		attachmentsList.add("D:/auxiliary/tmp/tmp.txt");
//		attachmentsList.add("D:/auxiliary/tmp/tmp.html");
//		attachmentsList.add("D:/auxiliary/tmp/tmp0.txt");
//		attachmentsList.add("D:/auxiliary/tmp/tmp.jsp");
//		attachmentsList.add("D:/auxiliary/tmp/tmp2.txt");
//		Message message = new MailMessageCreator().createMailWithAttachments(session, sendFrom, sendTo, "测试标题3", "测试附件2", attachmentsList);
		
		try {
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}