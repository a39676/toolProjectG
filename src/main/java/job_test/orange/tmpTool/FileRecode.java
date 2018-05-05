package job_test.orange.tmpTool;

import tool_package.io_tools.IOtools;

public class FileRecode {

	private static IOtools iot = new IOtools();
	
	public static void main(String[] args) throws Exception {
		String inputPath = "d:/auxiliary/tmp/apps.txt"; 
		String outputPath = "d:/auxiliary/tmp/appGbk.csv";
		iot.fileRecode(inputPath, outputPath, "utf8", "gbk");
	}
}
