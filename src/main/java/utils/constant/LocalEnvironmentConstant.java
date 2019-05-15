package utils.constant;

import java.io.File;

public class LocalEnvironmentConstant {
	
//	public static String propertiesFile = "src/main/resources/properties/orangeBankApi.properties";
	public static String memoryJoyPropertiesFile = "src/main/resources/properties/database/memoryJoy.properties";
	public static String ssmPropertiesFile = "src/main/resources/properties/database/ssm.properties";
	public static String woqu3 = "src/main/resources/properties/database/woqu3.properties";
	public static String woquIntelligentDevice = "src/main/resources/properties/database/woquIntelligentDevice.properties";
	public static String woquDevNotification = "src/main/resources/properties/database/woquDevNotification.properties";
	public static String woquDevFinance= "src/main/resources/properties/database/woquDevFinance.properties";
	public static String woquDevOperation= "src/main/resources/properties/database/woquDevOperation.properties";
	
	
	// 表结构汇总excel
	public static String tablesExclPath = "src/main/resources/tmp/test01.xlsx";

	// 基础路径
	public static String mainPath = "d:" + File.separator + "auxiliary" + File.separator + "tmp" + File.separator;
	public static String inputPath = (mainPath);
	public static String outputPath = (mainPath + "output" + File.separator);
	
}
