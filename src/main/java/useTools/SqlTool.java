package useTools;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ioHandle.FileUtilCustom;
import utils.constant.LocalEnvironmentConstant;
import utils.sql_utils.MySQLDBUtility;
import utils.sql_utils.MySqlTmpTool;
import utils.sql_utils.MySqlTools;

public class SqlTool {
	
	private static MySqlTools sqlTools = new MySqlTools();
	private static String propertiesFilePath = null;
	public static String getPropertiesFilePath() {
		return propertiesFilePath;
	}
	public static void setPropertiesFilePath(String propertiesFilePath) {
		SqlTool.propertiesFilePath = propertiesFilePath;
	}

	/**
	 * 根据输入的excl 生成创建数据表的语句
	 * (编写中)
	 */
	public void createTable() {
		Workbook workbook = null;
		
		try {
			workbook = new XSSFWorkbook(new FileInputStream(new File(LocalEnvironmentConstant.tablesExclPath)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (workbook != null) {
			sqlTools.createTableFromXLSX(workbook, 3);
		}
	}
	
	/**
	 * 根据properties文件 
	 * 获取其中所有表的表结构
	 */
	public void getTableInfo() {
		
		Connection connect = null;
		
		try {
			connect = MySQLDBUtility.getConnection(propertiesFilePath);
			List<String> tableNames = sqlTools.getTableNameListByShowTables(connect);
			System.out.println(tableNames.toString());
			sqlTools.getTableAllInfoToTxtInOne(connect, tableNames, LocalEnvironmentConstant.outputPath);

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getTableCreatorSql(List<String> tableNames) {
		Connection connect = null;
		
		String sqlTemplate = "show create table ?";
		
		try {
			connect = MySQLDBUtility.getConnection(propertiesFilePath);
			if(tableNames == null || tableNames.size() <= 0) {
				tableNames = sqlTools.getTableNameListByShowTables(connect);
			}
			
			if(tableNames == null || tableNames.size() <= 0) {
				System.out.println("无法获取该数据库表名,或该数据库尚未建表.");
				throw new Exception();
			}
			
			System.out.println(tableNames.toString());
			for(String tableName : tableNames) {
				sqlTools.getResultToTxt(connect, sqlTemplate.replace("?", tableName), "tmpResult", true, false);				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			connect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 根据输入的关键字(可多个), 模糊查找全库表结构, 
	 * 返回所在位置
	 * (表名, 字段名, 备注)
	 * @param keywordList
	 */
	public void getTableWithAim(List<String> keywordList) {
		getTableWithAim(keywordList.toArray(new String[keywordList.size()]));
	}
	
	/**
	 * 根据输入的关键字(可多个), 模糊查找全库表结构, 
	 * 返回所在位置
	 * (表名, 字段名, 备注)
	 * @param keywords
	 */
	public void getTableWithAim(String... keywords) {
		
		Connection connect = null;
		
		List<String> aimWordList = new ArrayList<String>();
		for(String keyword : keywords) {
			aimWordList.add(keyword);
		}
		
		try {
			connect = MySQLDBUtility.getConnection(propertiesFilePath);
			List<String> tableNames = sqlTools.getTableNameListByShowTables(connect);
			System.out.println(tableNames.toString());
			sqlTools.getTableAllInfoWithAim(connect, tableNames, aimWordList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void tmpInsert() {
		MySqlTmpTool tmpTool = new MySqlTmpTool();
		
		Connection connect = null;
		
		try {
			connect = MySQLDBUtility.getConnection(propertiesFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		tmpTool.dateCreater(connect);
		
	}
	
	public void keepAliveAndExecuteOrder() throws InterruptedException {
		
		MySqlTools mySqlTool01 = new MySqlTools();
		FileUtilCustom ioTool = new FileUtilCustom();
		
		Connection connect = null;
		String sql = null;
		String result = null;
		
		try {
			connect = MySQLDBUtility.getConnection(propertiesFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while(true) {
			mySqlTool01.selectOne(connect);
			sql = ioTool.getStringFromFile(LocalEnvironmentConstant.inputPath + "input.txt");
			result = mySqlTool01.executeSql(connect, sql);
			ioTool.byteToFile(result.getBytes(), LocalEnvironmentConstant.outputPath + "result.txt");
			System.out.println(result);
			TimeUnit.SECONDS.sleep(3);
		}
	}
	
	/**
	 * appendFlag = true ---> append at end
	 * appendFlag = false ---> rewrite
	 * @param sql
	 * @param resultName
	 * @param appendFlag
	 */
	public void getResultToTxt(String sql, String resultName, boolean appendFlag) {
		Connection connect = null;
		
		try {
			connect = MySQLDBUtility.getConnection(propertiesFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sqlTools.getResultToTxt(connect, sql, resultName, appendFlag);
	}
	
	public void getResultToCsv(String sql, String resultName, boolean appendFlag, String codeType) {
		Connection connect = null;
		
		try {
			connect = MySQLDBUtility.getConnection(propertiesFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sqlTools.getResultToCsv(connect, sql, resultName, appendFlag, codeType);
	}
	
	public void backupInfoToExcl(String outputPath) {
		Connection connect = null;
		
		try {
			connect = MySQLDBUtility.getConnection(propertiesFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sqlTools.backupInfoToExcl(connect, outputPath);
	}
	
	public void backupInfoToTxt(String outputPath) {
		Connection connect = null;
		
		try {
			connect = MySQLDBUtility.getConnection(propertiesFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sqlTools.backupInfoToTxt(connect, outputPath);
	}
	
	/** 2017-10-23 临时任务,帮数据分析人员导出数据*/
	public void pullDateFromDatabase() throws UnsupportedEncodingException {
		SqlTool tool = new SqlTool();
		String sql = " select    cc.owner_uid,    cc.relation_name          from     certification_contacts as cc          inner join loan_record as loan   on cc.owner_uid = loan.uid  where uid= ? ";
		
		String inputIDFilePath = "d:/auxiliary/tmp/uids20171019.txt";
		String recordIDFilePath = "d:/auxiliary/tmp/uidsWasGet.txt";
		String outputFilePath = "d:/auxiliary/tmp/cc/";
		int startIndex = 1;
		int endIndex = 120000;
		
		FileUtilCustom io = new FileUtilCustom();
		
		String inputUidString = io.getStringFromFile(inputIDFilePath);
		String[] uidArr = inputUidString.split(",");
		List<String> inputUidList = new ArrayList<String>();
		for(String ele : uidArr) {
			inputUidList.add(ele.replaceAll("[^\\d]", ""));
		}
		
		Set<String> recordUidList = new HashSet<String>();

		
		File outputFolder = new File(outputFilePath);
		File[] files = outputFolder.listFiles();
		for(File ele : files) {
			recordUidList.add(ele.getName().replace(".txt", ""));
		}
		
		StringBuffer tmpUidRecord = new StringBuffer();
		for(int i = startIndex; i < endIndex && i < inputUidList.size(); i++) {
			if(!recordUidList.contains(inputUidList.get(i))) {
				tool.getResultToTxt(sql.replace("?", inputUidList.get(i)), "/cc/" + inputUidList.get(i), false);
				tmpUidRecord.append(inputUidList.get(i) + ",\n");
				recordUidList.add(inputUidList.get(i));
			}
			if(i % 50 == 0 || i == endIndex - 1) {
				io.byteToFileAppendAtEnd(tmpUidRecord.toString().getBytes("utf8"), recordIDFilePath);
				tmpUidRecord.setLength(0);
			}
			System.out.println("run to: " + i);
		}
	}
	
	public static void main(String[] args) throws Exception  {
		SqlTool tool = new SqlTool();
		SqlTool.setPropertiesFilePath(LocalEnvironmentConstant.memoryJoyPropertiesFile);
//		IOtools iot = new IOtools();
		
//		 搜索数据库
//		tool.getTableWithAim("approvemoney");
		
		// 索取数据
//		String sql = iot.getStringFromFile("d:/auxiliary/tmp/tmpSql.txt");
//		tool.getResultToTxt(sql, "tmpResult", false);
		
		// 获取数据库概况
//		tool.getTableInfo();
		
		tool.getTableCreatorSql(null);
		
	}


}
