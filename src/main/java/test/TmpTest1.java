package test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TmpTest1 {

	public static void main(String[] args) {

		String str = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//		String str = "0123456789abcdefghijklmnopqrstuvwxyz";
		List<Character> ranChars = new ArrayList<Character>();
		List<Character> tmpChars = new ArrayList<Character>();
		for(int i = 0; i < str.length(); i++) {
			ranChars.add(str.charAt(i));
		}
		
		tmpChars.addAll(ranChars);
		
		List<List<Character>> keys = new ArrayList<List<Character>>();
		List<Character> tmpKey = null;

		int ran = 0;
		for(int j = 0; j < 8; j ++) {
			tmpKey = new ArrayList<Character>();
			for (int i = 0; i < 10; i++) {
				ran = ThreadLocalRandom.current().nextInt(0, tmpChars.size());
				tmpKey.add(tmpChars.remove(ran));
			}
			tmpChars.clear();
			tmpChars.addAll(ranChars);
			
			keys.add(tmpKey);
		}
		for(List<Character> key : keys) {
			System.out.println(key);
		}
		String inputStr = "456123";
		
		StringBuffer builder = new StringBuffer();
		int keyIndex = 0;
		for(int i = 0; i < inputStr.length(); i++) {
			if(i == inputStr.length() - 1) {
				builder.append(inputStr.charAt(0));
				keyIndex = Integer.parseInt(String.valueOf(inputStr.charAt(0)));
				builder.append(keys.get(keyIndex).get(Integer.parseInt(String.valueOf(inputStr.charAt(i)))));
			} else {
				builder.append(inputStr.charAt(i + 1));
				keyIndex = Integer.parseInt(String.valueOf(inputStr.charAt(i + 1)));
				builder.append(keys.get(keyIndex).get(Integer.parseInt(String.valueOf(inputStr.charAt(i)))));
			}
		}
		
		System.out.println(builder.toString());
		
		for(int i = 0; i < builder.length(); i = i + 2) {
			builder.replace(i, i+1, keys.get(0).get(Integer.parseInt(String.valueOf(builder.charAt(i)))).toString());
		}
		
		System.out.println(builder.toString());
		
//		String result = builder.toString();
		
		for(int i = 0; i < builder.length(); i = i + 2) {
			builder.replace(i, i+1, String.valueOf(keys.get(0).indexOf((builder.charAt(i)))));
		}
		System.out.println(builder.toString());
		
		StringBuffer resultBuilder = new StringBuffer();
		for(int i = 0; i < builder.length(); i = i + 2) {
			keyIndex = Integer.parseInt(String.valueOf(builder.charAt(i)));
			resultBuilder.append(keys.get(keyIndex).indexOf(builder.charAt(i + 1)));
		}
		
		System.out.println(resultBuilder.toString());
	}
	
}
