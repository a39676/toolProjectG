package tool_package;

import java.security.MessageDigest;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;

public class Tools {
	
	public String dynamicTransactionId(String perfix) {
		return 
			perfix  
			+ System.currentTimeMillis() 
			+ String.format("%03d", new Random().nextInt(64));
	}
	
	public String encryptWithMD5(String str) throws Exception{
		//将字符串信息采用MD5处理
		MessageDigest md = 
			MessageDigest.getInstance("MD5");
		byte[] output = md.digest(str.getBytes());
		//将MD5处理结果利用Base64转成字符串
		String s = Base64.encodeBase64String(output);
		return s;
	}

}
