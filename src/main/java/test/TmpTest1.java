package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TmpTest1 {

	public static void main(String[] args) {
		
		String url = "http://www.seekingdreamwork.site/test/test";
		Pattern p = Pattern.compile("(?!:http://)(?!:www\\.){0,1}([0-9a-zA-Z_]+\\.[a-z]{1,8})(?!:/.*)");
		Matcher m = p.matcher(url);

		if(m.find()) {
			for(int i = 0; i <= m.groupCount(); i++) {
				System.out.println(m.group(i));
			}
		} else {
			System.out.println("not found");
		}
	}
	
}
