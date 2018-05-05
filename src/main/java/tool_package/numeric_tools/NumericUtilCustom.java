package tool_package.numeric_tools;

import java.text.NumberFormat;

public class NumericUtilCustom {
	
	public static String simpleNumberRegex = "-?\\d+($|\\.?\\d+)";
	public static String integerRegex = "-?\\d+";
	public static String positiveIntegerRegex = "\\d+";
	public static String decimalRegex = "-?\\d+\\.\\d+";
	
	public static Number strToNumber(String numberStr) {
		
		if(matchSimpleNumber(numberStr)) {
			System.out.println("not a simple number");
			return null;
		}
		
		Number num = null;
		
		try {
			num = NumberFormat.getInstance().parse(numberStr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return num;
	}
	
	public static boolean matchSimpleNumber(String numberStr) {
		if (isEmpty(numberStr)) {
			return false;
		}
		return numberStr.matches(numberStr);
	}
	
	public static boolean matchInteger(String numberStr) {
		if (isEmpty(numberStr)) {
			return false;
		}
		return numberStr.matches(integerRegex);
	}
	
	public static boolean matchPositiveInteger(String numberStr) {
		if (isEmpty(numberStr)) {
			return false;
		}
		return numberStr.matches(positiveIntegerRegex);
	}
	
	public static boolean matchDecimal(String numberStr) {
		if (isEmpty(numberStr)) {
			return false;
		}
		return numberStr.matches(decimalRegex);
	}
	
	private static boolean isEmpty(String str) {
		return (str == null || str == "");
	}

}