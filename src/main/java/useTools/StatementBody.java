package useTools;

import java.util.List;

import tool_package.io_tools.MysqlIOtools01;

public class StatementBody {

	static MysqlIOtools01 tool01 = new MysqlIOtools01();
	
	public static String createBody() {
		
		StringBuffer sb = new StringBuffer();
		
		String path = "d:/auxiliary/tmp/";
		String inputPath = (tool01.separatorFilter(path));
		String colNameFile = (inputPath + "colName.txt");
		String colTypeFile = (inputPath + "colType.txt");
		String colCommentFile = (inputPath + "colComment.txt");
		String colDefaultValFile = (inputPath + "colDefaultVal.txt");
		
    	List<String> colNameList = tool01.putIntoList(colNameFile);
    	List<String> colTypeList = tool01.modifyListToMySqlDataType(tool01.putIntoList(colTypeFile));
    	List<String> colCommentList;
    	List<String> colDefaultValList;

    	int referenceSize = colNameList.size();
    	colCommentList = tool01.putIntoList(colCommentFile, referenceSize);
    	colDefaultValList = tool01.putIntoList(colDefaultValFile, referenceSize);
    	
    	return null;

	}

}
