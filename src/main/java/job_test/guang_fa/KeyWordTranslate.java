package job_test.guang_fa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ioHandle.FileUtilCustom;

public class KeyWordTranslate {

	private static String ownStringFilePath = "D:/auxiliary/tmp/ZJ_HTGL_HTZB.txt";
	private static String attrKeyWordFilePath = "src/main/resources/static_resources/txt/guangFa/attrC2E.txt";
	private static String entityKeyWordFilePath = "src/main/resources/static_resources/txt/guangFa/EntityC2E.txt";
	private static List<String> keyWords;
	private static List<String> ownString;
	
	static {
		getKeyWords();
		getOwnString();
	}

	private static void getKeyWords() {
		FileUtilCustom ioUtil = new FileUtilCustom();
		String attrStr = ioUtil.getStringFromFile(attrKeyWordFilePath);
		String[] attrLines = attrStr.split("\n");
		
		String entityStr = ioUtil.getStringFromFile(entityKeyWordFilePath, "utf8");
		String[] entityLines = entityStr.split("\n");
		
		keyWords = new ArrayList<String>();
		keyWords.addAll(Arrays.asList(attrLines));
		keyWords.addAll(Arrays.asList(entityLines));
		
	}
	
	private String getTargetLine(String inputStr) {
		StringBuffer sb = new StringBuffer();
		for(String line : keyWords) {
			if(line.toLowerCase().contains(inputStr)) {
				sb.append(line);
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	private static void getOwnString() {
		FileUtilCustom ioUtil = new FileUtilCustom();
		String attrStr = ioUtil.getStringFromFile(ownStringFilePath);
		String[] ownStrings = attrStr.split("\n");
		
		ownString = new ArrayList<String>();
		ownString.addAll(Arrays.asList(ownStrings));
	}
	
	private void matchKeyWords() {
		if(keyWords == null || keyWords.size() <= 0) {
			System.out.println("无法读取键值对");
			return;
		}
		if(ownString == null || ownString.size() <= 0) {
			System.out.println("无法读取需要转换的文档");
			return;
		}
		
		String tmpLine = "";
		for(int i = 0; i < ownString.size(); i++) {
			for(int j = 0; j < keyWords.size(); j++) {
				if(ownString.get(i).toLowerCase().contains((keyWords.get(j).split(",")[1]).toLowerCase())) {
					tmpLine = ownString.get(i) + " -- " + keyWords.get(j);
					ownString.remove(i);
					ownString.add(i, tmpLine);
				}
			}
		}
	}

	public static void main(String[] args) {
		KeyWordTranslate demo = new KeyWordTranslate();
		
//		System.out.println(demo.getTargetLine("ratio"));
		demo.matchKeyWords();
		for(String ele : demo.ownString) {
			System.out.println(ele);
		}
	}

}
