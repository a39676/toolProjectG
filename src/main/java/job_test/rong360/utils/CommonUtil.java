package job_test.rong360.utils;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import java.util.Scanner;

public class CommonUtil {
	
	public static String getUserInput(){
		String input = "";
		Scanner scanner = new Scanner(System.in, "utf8");
		input = scanner.next();
		return input ;
	}
	/**
	 * 按key进行正序排列，之间以&相连
	 * <功能描述>
	 * @param params
	 * @return
	 */
	public static String getSortParams(Map<String, String> params) {
		Map<String, String> map = new TreeMap<String, String>(
				new Comparator<String>() {
					public int compare(String obj1, String obj2) {
						// 升序排序
						return obj1.compareTo(obj2);
					}
				});
		for (String key: params.keySet()) {
			map.put(key, params.get(key));
		}

		Set<String> keySet = map.keySet();
		Iterator<String> iter = keySet.iterator();
		String str = "";
		while (iter.hasNext()) {
			String key = iter.next();
			String value = map.get(key);
			str += key + "=" + value + "&";
		}
		if(str.length()>0){
			str = str.substring(0, str.length()-1);
		}
		return str;
	}
}
