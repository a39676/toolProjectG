package job_test.orange.tmpTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tool_package.io_tools.IOtools;
import useTools.SqlTool;
import utils.constant.LocalEnvironmentConstant;

public class GetSmsKeyWordFromDatabase {
	
	private static List<String> uids = new ArrayList<String>();
	private static IOtools iot = new IOtools();
	private static SqlTool sqlTool = new SqlTool();
	private static int startIndex = 0;
	private static int endIndex = 97000;
	private static int tmpIndex = 0;
	private static int stepLong = 5000;
	private static StringBuffer sb = new StringBuffer();
	private static String mainSql = iot.getStringFromFile("d:/auxiliary/tmp/sql5.txt");
	
	private void getUids() {
		String uidStr = iot.getStringFromFile("d:/auxiliary/tmp/uidLost.txt");
		List<String> tmpList = Arrays.asList(uidStr.split("\\n"));
		for(String ele : tmpList) {
			uids.add(ele.replaceAll("\\t$", "").replaceAll("^\\d{1,5}\\t", ""));
		}
	}
	
	private void getSubSmsKeyWord() {
		sb.setLength(0);
		sb.append(mainSql);
		sb.append("and askc.uid in ");
		sb.append("(");
		int stepCount = 0;
		for(;tmpIndex <= endIndex && tmpIndex < uids.size() && stepCount < stepLong; tmpIndex++, stepCount++) {
			sb.append(uids.get(tmpIndex));
			if(stepCount != (stepLong - 1) && tmpIndex != (uids.size() - 1)) {
				sb.append(",");
			}
		}
		sb.append(")");
		
		System.out.println(sb);
		System.out.println("now index: " + tmpIndex);
		sqlTool.getResultToCsv(sb.toString(), "smsKeyWords5", true, "gbk");
	}

	public static void main(String[] args) {
		GetSmsKeyWordFromDatabase t = new GetSmsKeyWordFromDatabase();
		sqlTool.setPropertiesFilePath(LocalEnvironmentConstant.orangePropertiesFile);
		t.getUids();
		GetSmsKeyWordFromDatabase.tmpIndex = GetSmsKeyWordFromDatabase.startIndex;
		
		
		while(tmpIndex < endIndex && tmpIndex < uids.size() -1) {
			t.getSubSmsKeyWord();
		}
	}

}
