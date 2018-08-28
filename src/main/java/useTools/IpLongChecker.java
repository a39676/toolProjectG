package useTools;

import java.util.ArrayList;
import java.util.List;

import httpHandel.HttpUtil;
import numericHandel.NumericUtilCustom;

public class IpLongChecker {

	private static String tmpKey = "ee656d8923ebc4a32df5993754dbd019";
	private static String baseUrl = "http://api.ipstack.com/";
	
	
	public static void main(String[] args) throws Exception {
		HttpUtil hu = new HttpUtil();
		List<Long> checkList = new ArrayList<Long>();
//		checkList.add(1971855321L);
//		checkList.add(875940174L);
//		checkList.add(1297624868L);
		checkList.add(2102924730L);
		checkList.add(3683237453L);
		
		for(Long l : checkList) {
			System.out.println(hu.sendGet(baseUrl + NumericUtilCustom.longToIp(l) + "?access_key=" + tmpKey, null));
		}
	}
}
