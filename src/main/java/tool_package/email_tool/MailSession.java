package tool_package.email_tool;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class MailSession {
	
	
	public static Session getSmtpSslSession(Properties properties, String userName, String password) {

		return Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});
		
	}
	
}
