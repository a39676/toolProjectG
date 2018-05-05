package job_test.orange.tmpTool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tool_package.date_tool.DateUtilCustom;
import tool_package.io_tools.IOtools;

public class TmpTool {
	
	private static String mainPath = "d:/rawdata_demo/";
	private static List<File> targetFiles = new ArrayList<File>();
	private static List<File> targetFolders = new ArrayList<File>();
	private static String startDate = "20170601";
	private static String endDate = "20170630";

	private static String outputSqlPath = "d:/auxiliary/tmp/data06.sql";
	
//	private static List<String> phoneList;
//	private static String phoneListPath = "d:/auxiliary/tmp/phones.txt";
	
	private static List<String> rawdataFileList = new ArrayList<String>();
	private static String rawdataFileListPath = "d:/auxiliary/tmp/filePaths.txt";
	
	private static Set<String> finishedRecord;
	private static String finishedRecordPath = "d:/auxiliary/tmp/finishedRecord.txt";
	
	private static List<String> badFileRecord = new ArrayList<String>();
	private static String badFileRecordPath = "d:/auxiliary/tmp/badFileRecord.txt";
	
	private static String pattern = "rawdata_\\d{6,8}_\\d{13}\\.zip";
	private static Pattern r = Pattern.compile(pattern);
	private static Matcher m ;
	
	public byte[] readZipFileContent(String filePath) {
		File source = new File(filePath);
		if (!source.exists()) {
			return null;
		}
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ZipInputStream zis = null;
		try {
			zis = new ZipInputStream(new FileInputStream(source));
			ZipEntry entry = null;
			while ((entry = zis.getNextEntry()) != null && !entry.isDirectory()) {
				int read = 0;
				while ((read = zis.read()) != -1) {
					stream.write(read);
				}
			}
			zis.closeEntry();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (zis != null) {
				try {
					zis.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return stream.toByteArray();
	}
	
	public String reportToString(String filePath) {
		try {
			return new String(readZipFileContent(filePath), "utf8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public JSONObject getMainBody(String jsonStr) {
		JSONObject json = JSONObject.fromObject(jsonStr);
		JSONObject rawData = json.getJSONObject("raw_data");
		JSONObject members = rawData.getJSONObject("members");
		JSONObject mainBody = ((JSONObject) members.getJSONArray("transactions").get(0));
		return mainBody;
	}
	
	public String getTargetPhone(JSONObject json) {
		return json.getJSONObject("basic").getString("cell_phone");
	}
	
//	public void getTargetPhoneList() {
//		System.out.println("getTargetPhoneList");
//		try {
//			IOtools iot = new IOtools();
//			byte[] data = iot.getByteFromFile(phoneListPath);
//			String str = new String(data, "utf-8");
//			phoneList = Arrays.asList(str.split("\n"));
//			System.out.println("phoneListSize: " + phoneList.size());
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("get phoneList error");
//		}
//	}
//	
//	public boolean isTarget(String phone) {
//		return phoneList.contains(phone);
//	}
	
	public void getTargetFileList() {
		System.out.println("get targetFileList");
		try {
			IOtools iot = new IOtools();
			byte[] data = iot.getByteFromFile(rawdataFileListPath);
			String str = new String(data, "utf-8");
			List<String> tmpList = Arrays.asList(str.split("\n"));
			for(String ele : tmpList) {
				m = r.matcher(ele);
				if(m.find()) {
					rawdataFileList.add(m.group(0));
				}
			}
			System.out.println("targetFileListSize: " + rawdataFileList.size());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("get targetFileList error");
		}
	}
	
	public boolean isTarget(String fileName) {
		return rawdataFileList.contains(fileName);
	}
	
	public void lockTargetFolder() {
		System.out.println("get target folder");
		Date startTime = DateUtilCustom.stringToDateUnkonwFormat(startDate);
		Date endTime = DateUtilCustom.stringToDateUnkonwFormat(endDate);
		
		File mainFolder = new File(mainPath);
		File[] files = mainFolder.listFiles();
		
		Date tmpDate = null;
		for(File ele : files) {
			if(ele.isDirectory()) {
				try {
					tmpDate = DateUtilCustom.stringToDateUnkonwFormat(ele.getName());
				} catch (Exception e) {
//					e.printStackTrace();
				}
				if(tmpDate != null && tmpDate.getTime() >= startTime.getTime() && tmpDate.getTime() <= endTime.getTime()) {
					targetFolders.add(ele);
				}
			}
		}
		System.out.println("Had get targetFolder. Get " + targetFolders.size() + " folders.");
	}
	
	public void lockTargetFiles() {
		System.out.println("going to get files");
		List<File> subFiles;
		for(File folder : targetFolders) {
			System.out.println("get files in " + folder.getName());
			subFiles = Arrays.asList(folder.listFiles());
			for(File subFolder : subFiles) {
				if(subFolder.isDirectory()) {
					int addCount = 0;
					for(File subFile : Arrays.asList(subFolder.listFiles())) {
						if(subFile.isFile() && subFile.getName().endsWith("zip")) {
							targetFiles.add(subFile);
							addCount ++;
							if(addCount % 50 == 0) {
								System.out.println("adding " + subFile.getPath() + " into list");
							}
						}
					}
				}
			}
		}
//		marrk
//		targetFiles.add(new File("D:/rawdata_demo/20170801/4945/rawdata_104945_1501602841466.zip"));
		
		System.out.println("get " + targetFiles.size() + " files");
	}
	
	public void getFinishedRecord() {
		System.out.println("getFinishedRecordList");
		try {
			IOtools iot = new IOtools();
			byte[] data = iot.getByteFromFile(finishedRecordPath);
			String str = new String(data, "utf-8");
			finishedRecord = new HashSet<String>(Arrays.asList(str.split("\n")));
			System.out.println("finishedRecordSize: " + finishedRecord.size());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("get getFinishedRecordList error");
		}
	}
	
	public boolean hasFinished(String filePath) {
		return finishedRecord.contains(filePath);
	}
	
	public void recordFinished(StringBuffer sb) throws UnsupportedEncodingException {
		IOtools iot = new IOtools();
		iot.byteToFileAppendAtEnd(sb.toString().getBytes("utf-8"), finishedRecordPath);
		sb.setLength(0);
	}
	
	public void recordBadFiles(StringBuffer sb) throws UnsupportedEncodingException {
		IOtools iot = new IOtools();
		iot.byteToFileAppendAtEnd(sb.toString().getBytes("utf-8"), badFileRecordPath);
		sb.setLength(0);
	}

	public void callsToSql(JSONArray calls, StringBuffer sb) {
		if(calls.size() > 0) {
			String sqlPrefix = "INSERT IGNORE INTO `opreport_test`.`certification_phone_analysis_calls` (`cell_phone`, `other_cell_phone`, `start_time`, `update_time`, `init_type`, `use_time`, `place`, `call_type`, `subtotal`) values";
			sb.append(sqlPrefix);
			JSONObject subJson = null;
			for(int i = 0; i < calls.size(); i++) {
				subJson = (JSONObject) calls.get(i);
				sb.append("(");
				if(subJson.getString("cell_phone").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("cell_phone") + "'" + ",");
				}
				if(subJson.getString("other_cell_phone").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("other_cell_phone") + "'" + ",");
				}
				if(subJson.getString("start_time").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("start_time") + "'" + ",");
				}
				if(subJson.getString("update_time").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("update_time") + "'" + ",");
				}
				if(subJson.getString("init_type").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("init_type") + "'" + ",");
				}
				if(subJson.getString("use_time").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("use_time") + "'" + ",");
				}
				if(subJson.getString("place").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("place") + "'" + ",");
				}
				if(subJson.getString("call_type").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("call_type") + "'" + ",");
				}
				if(subJson.getString("subtotal").equals("null")) {
					sb.append("null");
				} else {
					sb.append("'" + subJson.getString("subtotal") + "'");
				}
				sb.append(")");
				if(i < calls.size() -  1) {
					sb.append(",");
				} else {
					sb.append(";\n");
				}
			}
		}
	}
	
	public void transactionsToSql(JSONArray transactions, StringBuffer sb) {
//		INSERT INTO `opreport_test`.`certification_phone_analysis_transactions` (`cell_phone`, `update_time`, `bill_cycle`, `total_amt`, `plan_amt`, `pay_amt`) VALUES 
//		('123', '2017-01-01 00:00:00', '2017-01-01 00:00:00', '43243', '432432', '4324');
		if(transactions.size() > 0) {
			String sqlPrefix = "INSERT IGNORE INTO `opreport_test`.`certification_phone_analysis_transactions` (`cell_phone`, `update_time`, `bill_cycle`, `total_amt`, `plan_amt`, `pay_amt`) values";
			sb.append(sqlPrefix);
			JSONObject subJson = null;
			
			for(int i = 0; i < transactions.size(); i++) {
				subJson = (JSONObject) transactions.get(i);
				sb.append("(");
				if(subJson.getString("cell_phone").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("cell_phone") + "'" + ",");
				}
				if(subJson.getString("update_time").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("update_time") + "'" + ",");
				}
				if(subJson.getString("bill_cycle").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("bill_cycle") + "'" + ",");
				}
				if(subJson.getString("total_amt").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("total_amt") + "'" + ",");
				}
				if(subJson.getString("plan_amt").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("plan_amt") + "'" + ",");
				}
				if(subJson.getString("pay_amt").equals("null")) {
					sb.append("null");
				} else {
					sb.append("'" + subJson.getString("pay_amt") + "'");
				}
				sb.append(")");
				if(i < transactions.size() -  1) {
					sb.append(",");
				} else {
					sb.append(";\n");
				}
			}
		}
	}
	
	public void smsesToSql(JSONArray smses, StringBuffer sb) {
//		INSERT INTO `opreport_test`.`certification_phone_analysis_smses` 
//		(`cell_phone`, `other_cell_phone`, `place`, `start_time`, `update_time`, `init_type`, `subtotal`) VALUES 
//		('18356746081', '18356746081', 'f', '2017-06-27 21:56:43', '2017-06-27 21:56:43', '发送', '1.1');
		if(smses.size() > 0) {
			String sqlPrefix = "INSERT IGNORE INTO `opreport_test`.`certification_phone_analysis_smses` "
					+ "(`cell_phone`, `other_cell_phone`, `place`, `start_time`, `update_time`, `init_type`, `subtotal`) values";
			sb.append(sqlPrefix);
			JSONObject subJson = null;
			for(int i = 0; i < smses.size(); i++) {
				subJson = (JSONObject) smses.get(i);
				sb.append("(");
				if(subJson.getString("cell_phone").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("cell_phone") + "'" + ",");
				}
				
				if(subJson.getString("other_cell_phone").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("other_cell_phone") + "'" + ",");
				}
				
				if(subJson.getString("place").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("place") + "'" + ",");
				}
				
				if(subJson.getString("start_time").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("start_time") + "'" + ",");
				}
				
				if(subJson.getString("update_time").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("update_time") + "'" + ",");
				}
				
				if(subJson.getString("init_type").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("init_type") + "'" + ",");
				}
				
				if(subJson.getString("subtotal").equals("null")) {
					sb.append("null");
				} else {
					sb.append("'" + subJson.getString("subtotal") + "'");
				}
				
				sb.append(")");
				if(i < smses.size() -  1) {
					sb.append(",");
				} else {
					sb.append(";\n");
				}
			}
		}
	}
	
	public void netsToSql(JSONArray nets, StringBuffer sb) {
//		INSERT INTO `opreport_test`.`certification_phone_analysis_nets` (`cell_phone`, `start_time`, `update_time`, `subflow`, `use_time`, `place`, `net_type`, `subtotal`) VALUES 
//		('321', '2017-06-27 21:56:43', '2017-06-27 21:56:43', '432', '3', '安徽', '3g', '43.2');
		if(nets.size() > 0) {
			String sqlPrefix = "INSERT IGNORE INTO `opreport_test`.`certification_phone_analysis_nets` (`cell_phone`, `start_time`, `update_time`, `subflow`, `use_time`, `place`, `net_type`, `subtotal`) values";
			sb.append(sqlPrefix);
			JSONObject subJson = null;
			for(int i = 0; i < nets.size(); i++) {
				subJson = (JSONObject) nets.get(i);
				sb.append("(");
				if(subJson.getString("cell_phone").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("cell_phone") + "'" + ",");
				}
				
				if(subJson.getString("start_time").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("start_time") + "'" + ",");
				}
				
				if(subJson.getString("update_time").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("update_time") + "'" + ",");
				}
				
				if(subJson.getString("subflow").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("subflow") + "'" + ",");
				}
				
				if(subJson.getString("use_time").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("use_time") + "'" + ",");
				}
				
				if(subJson.getString("place").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("place") + "'" + ",");
				}
				
				if(subJson.getString("net_type").equals("null")) {
					sb.append("null" + ",");
				} else {
					sb.append("'" + subJson.getString("net_type") + "'" + ",");
				}
				
				if(subJson.getString("subtotal").equals("null")) {
					sb.append("null");
				} else {
					sb.append("'" + subJson.getString("subtotal") + "'");
				}
				
				sb.append(")");
				if(i < nets.size() -  1) {
					sb.append(",");
				} else {
					sb.append(";\n");
				}
			}
		}
	}
	
	public void basicToSql(JSONObject basic, StringBuffer sb) {
//		INSERT INTO `opreport_test`.`certification_phone_analysis_basic` 
//		(`cell_phone`, `update_time`, `idcard`, `reg_time`, `real_name`) VALUES 
//		('123', '2017-06-27 21:56:43', 'f', '2017-06-27 21:56:43', 'fsd');
		if(basic.size() > 0) {
			String sqlPrefix = "INSERT IGNORE INTO `opreport_test`.`certification_phone_analysis_basic` (`cell_phone`, `update_time`, `idcard`, `reg_time`, `real_name`) values";
			sb.append(sqlPrefix);
			sb.append("(");
			if(basic.getString("cell_phone").equals("null")) {
				sb.append("null" + ",");
			} else {
				sb.append("'" + basic.getString("cell_phone") + "'" + ",");
			}
			
			if(basic.getString("update_time").equals("null")) {
				sb.append("null" + ",");
			} else {
				sb.append("'" + basic.getString("update_time") + "'" + ",");
			}
			
			if(basic.getString("idcard").equals("null")) {
				sb.append("null" + ",");
			} else {
				sb.append("'" + basic.getString("idcard") + "'" + ",");
			}
			
			if(basic.getString("reg_time").equals("null")) {
				sb.append("null" + ",");
			} else {
				sb.append("'" + basic.getString("reg_time") + "'" + ",");
			}
			
			if(basic.getString("real_name").equals("null")) {
				sb.append("null");
			} else {
				sb.append("'" + basic.getString("real_name") + "'");
			}
			
			sb.append(");\n");
		}
	}
	
	public void renameBadFiles() throws UnsupportedEncodingException {
		System.out.println("getBadFileRecordList");
		IOtools iot = new IOtools();
		try {
			byte[] data = iot.getByteFromFile(badFileRecordPath);
			String str = new String(data, "utf-8");
			Set<String> tmpList = new HashSet<String>(Arrays.asList(str.split("\n")));
			for(String ele : tmpList) {
				badFileRecord.add(ele.replaceAll("\\s", ""));
			}
			System.out.println("badFileRecordSize: " + badFileRecord.size());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("get badFileRecordList error");
		}
		
		
		if(badFileRecord.size() > 0) {
			System.out.println("try to rename " + badFileRecord.size() + " files");
			File oldFile = null;
			File rename = null;
			int count = 0;
			while(badFileRecord.size() > 0) {
				try {
					oldFile = new File(badFileRecord.get(0));
					rename = new File(badFileRecord.get(0) + ".notTarget");
					oldFile.renameTo(rename);
					System.out.println("rename: " + oldFile + "; " + count + "/" + badFileRecord.size());
					oldFile = null;
					rename = null;
					badFileRecord.remove(0);
					count ++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("rename " + count + " files");
			// clean bad file record after rename
			iot.byteToFile(",\n".getBytes("utf-8"), badFileRecordPath);
			System.out.println("clean badRecordFile");
		}
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		TmpTool tool = new TmpTool();
		IOtools iot = new IOtools();
//		tool.renameBadFiles();
		tool.lockTargetFolder();
		tool.lockTargetFiles();
		tool.getTargetFileList();
		tool.getFinishedRecord();
		
		StringBuffer sb = new StringBuffer();
		StringBuffer sbRecord = new StringBuffer();
		StringBuffer sbBadRecord = new StringBuffer();
		
		String str = "";
		JSONObject mainBody = null;
		JSONArray calls = null;
		JSONArray transactions = null;
		JSONArray smses = null;
		JSONArray nets = null;
		JSONObject basic = null;
		int count = 0;
		while(targetFiles.size() > 0) {
			System.out.println((targetFiles.get(0)).getPath() + "; " + targetFiles.size());
			if(!tool.hasFinished(targetFiles.get(0).getPath())) {
				str = tool.reportToString((targetFiles.get(0)).getPath());
				if(tool.isTarget((targetFiles.get(0)).getName())) {
					mainBody = tool.getMainBody(str); 
					count ++;
					System.out.println("count: " + count + " ;");
					calls = mainBody.getJSONArray("calls");
					transactions = mainBody.getJSONArray("transactions");
					smses = mainBody.getJSONArray("smses");
					nets = mainBody.getJSONArray("nets");
					basic = mainBody.getJSONObject("basic");
					tool.callsToSql(calls, sb);
					tool.transactionsToSql(transactions, sb);
					tool.smsesToSql(smses, sb);
					tool.netsToSql(nets, sb);
					tool.basicToSql(basic, sb);
					
					iot.byteToFileAppendAtEnd(sb.toString().getBytes("utf-8"), outputSqlPath);
					sb.setLength(0);
					
					File thisFolder = new File((targetFiles.get(0)).getParent());
					sbRecord.append((targetFiles.get(0)).getPath() + "\n");
					tool.recordFinished(sbRecord);
					
					Long useSpace = (targetFiles.get(0)).getTotalSpace();
					
					for(File neighborFile : thisFolder.listFiles()) {
						if(neighborFile.getTotalSpace() == useSpace) {
							
							sbRecord.append(neighborFile.getPath() + "\n");
							tool.recordFinished(sbRecord);
							finishedRecord.add(neighborFile.getPath());
							targetFiles.remove(neighborFile);
							
						}
					}
					targetFiles.remove(0);
					
				} else {
					File thisFolder = new File((targetFiles.get(0)).getParent());
//	???				finishedRecord.add((targetFiles.get(aim)).getParent());
					sbRecord.append((targetFiles.get(0)).getPath() + "\n");
					tool.recordFinished(sbRecord);
					
					System.out.println(thisFolder.getPath() + " found an bad file, ----> " + (targetFiles.get(0)).getName());
					Long useSpace = (targetFiles.get(0)).getTotalSpace();
					targetFiles.remove(0);
					
					int badCount = 0;
					for(File neighborFile : thisFolder.listFiles()) {
						if(neighborFile.getTotalSpace() == useSpace) {
							badCount ++;
							
							sbRecord.append(neighborFile.getPath() + "\n");
							tool.recordFinished(sbRecord);
							finishedRecord.add(neighborFile.getPath());
							
							sbBadRecord.append(neighborFile.getPath() + "\n");
							tool.recordBadFiles(sbBadRecord);
							targetFiles.remove(neighborFile);
						}
					}
					System.out.println("add " + badCount + " bad files");
				}
			} else {
				targetFiles.remove(0);
			}
		}
		
		
		
		
//		for(File file : new File("D:\\rawdata_demo\\20170515\\2121\\").listFiles()) {
//			System.out.println(file.getPath());
//		}
		
	}

}
