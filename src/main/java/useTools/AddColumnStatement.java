package useTools;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import tool_package.io_tools.MysqlIOtools01;

public class AddColumnStatement {
	
    public static void main(String[] args) {
    	
    	MysqlIOtools01 tool01 = new MysqlIOtools01();
    	
    	String tableName = "arp_cash";
    	String tableComment = "收付款表";
    	String path = "d:" + File.separator + "auxiliary" + File.separator + "tmp" + File.separator;
    	String inputPath = (path);
    	String outputPath = (path);
    	String colNameFile = (inputPath  +"colName.txt");
    	String colTypeFile = (inputPath  +"colType.txt");
    	String colCommentFile = (inputPath  +"colComment.txt");
    	
    	List<String> sqlStatement = new ArrayList<String>();
    	
    	List<String> colNameList = tool01.putIntoList(colNameFile);
    	List<String> colTypeList = tool01.modifyListToMySqlDataType(tool01.putIntoList(colTypeFile));
    	List<String> colCommentList = tool01.putIntoList(colCommentFile);
    	
    	sqlStatement.add("\n"
    			+ "alter table " + tableName + "\n");

    	for (int i = 0; i < (colNameList.size()); i++) {
    		sqlStatement.add("add " + colNameList.get(i) + " " + colTypeList.get(i) + " comment \'" 
    	+ colCommentList.get(i) + "\' , \n");
    	}
    	
    	sqlStatement.set(sqlStatement.size() - 1, sqlStatement.get(sqlStatement.size()-1).replace(",", "\n;"));
    	
    	StringBuilder sb = new StringBuilder();
    	for (String s : sqlStatement) {
    		sb.append(s);
    	}
    	
    	
    	System.out.println(sb);
    	
    	try {
    		FileOutputStream fos = new FileOutputStream(outputPath + "addColumnTo" + tableName + ".txt");
    		byte[] sql ;
    		sql = sb.toString().getBytes();
    		fos.write(sql);
    		fos.close();
    	} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
