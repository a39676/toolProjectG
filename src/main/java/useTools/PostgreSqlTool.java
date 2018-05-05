package useTools;

import tool_package.io_tools.IOtools;
import utils.constant.LocalEnvironmentConstant;
import utils.sql_utils.MySqlTools;
import utils.sql_utils.PostgreDBUtility;

public class PostgreSqlTool {
	
	MySqlTools sqlTools = new MySqlTools();
	private static String propertiesFilePath = null;
	public static String getPropertiesFilePath() {
		return propertiesFilePath;
	}
	public static void setPropertiesFilePath(String propertiesFilePath) {
		PostgreSqlTool.propertiesFilePath = propertiesFilePath;
	}

	
	
	
	public static void main(String[] args) throws Exception  {
		PostgreSqlTool tool = new PostgreSqlTool();
		IOtools iot = new IOtools();
		tool.setPropertiesFilePath(LocalEnvironmentConstant.postgrePropertiesFile);
		
		PostgreDBUtility dbu = new PostgreDBUtility();
		dbu.getConnection(propertiesFilePath);
		
		
	}


}
