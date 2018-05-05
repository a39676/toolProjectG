package tool_package.email_tool;

import java.util.Properties;

import tool_package.io_tools.IOtools;

public class MailProperties {
	
	
	public static Properties getAliyunSmtpSslProperties() {
		return new IOtools().getPropertiesFromFile("src/main/java/mail_test/aliyunSmtpSsl.properties");
	}
	
	public static Properties getAliyunImapSslProperties() {
		return new IOtools().getPropertiesFromFile("src/main/java/mail_test/aliyunImapSsl.properties");
	}
}
