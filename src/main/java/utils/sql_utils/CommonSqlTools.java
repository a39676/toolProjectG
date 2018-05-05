package utils.sql_utils;

import tool_package.io_tools.IOtools;
import utils.constant.LocalEnvironmentConstant;

public class CommonSqlTools {
	
	/**
	 * 从文件中获取sql
	 * @param path
	 * @return
	 */
	public String getSqlFromFile(String path) {
		IOtools iotool = new IOtools(); 
		String sql = null;
		byte[] data = null;
		try {
			data = iotool.getByteFromFile(path);
			sql = new String(data, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sql;
	}
	
	public static void main(String[] args) {
		LocalEnvironmentConstant constant = new LocalEnvironmentConstant();
		CommonSqlTools tool = new CommonSqlTools();
		System.out.println(tool.getSqlFromFile(constant.inputPath));
	}

}
