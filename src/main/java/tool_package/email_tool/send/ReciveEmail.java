package tool_package.email_tool.send;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;

import tool_package.email_tool.MailProperties;
import tool_package.email_tool.MailSession;

public class ReciveEmail {

	private static final String sendTo = "sendTo";
	private static final String sendFrom = "sendFrom";
	private static final String password = "";
	
	public static void main(String[] args) {
		ReciveEmail rm = new ReciveEmail();
		rm.sendSimpalMail();
	}
	
	public void sendSimpalMail() {

		Properties properties = MailProperties.getAliyunSmtpSslProperties();
		
		Session session = MailSession.getSmtpSslSession(properties, sendFrom, password);
		
		Message message = new MailMessageCreator().createSimpleMessage(session, sendFrom, sendTo, "测试标题3", "测试内容5");
		
		try {
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}