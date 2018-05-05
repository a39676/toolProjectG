package tool_package.io_tools;

import java.io.File;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShineFileAndFolder {

	/*
	 * 输入文件路径, 获取其中的文件夹和文件
	 */
	public static List<ArrayList<String>> listFiles(String headFolderPath) {
		
		// 创建一个列表 放置 文件夹列表 + 文件列表
		List<ArrayList<String>> headList = new ArrayList<ArrayList<String>>();
		ArrayList<String> folders = new ArrayList<String>();
		ArrayList<String> files = new ArrayList<String>();
		
		
		// 尝试读取路径
		File headFolder = null;
		try {
			headFolder = new File(headFolderPath);
		} catch (Exception e ) {
			e.printStackTrace();
		}
		
		if (!headFolder.isDirectory()){
			System.out.println(headFolderPath + "is not a folder");
			System.exit(0);
		}
		
		// 生成包含所有文件夹 + 文件的数组
		String[] fileList = headFolder.list();
		
		// 按文件夹 或  文件 放置
		if(fileList != null) {
			File subFile = null;
			for (String ele : fileList) {
				try {
					subFile = new File(headFolder + File.separator + ele);
    				if(subFile.isFile()) {
		    			files.add(subFile.getPath());
			    	} else if(subFile.isDirectory()) {
				    	folders.add(subFile.getPath());
				    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		headList.add(folders);
		headList.add(files);
		
		return headList;
	}
	
	public static void deepShine(List headList) {
		
		ArrayList<String> folders = new ArrayList<String>();
		
		if (headList != null){
			try {
				folders = (ArrayList<String>) headList.get(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		String path = "d:" + File.separator + "auxiliary";
		
		List<String> folders = listFiles(path).get(0);
		List<String> files = listFiles(path).get(1);
		
		System.out.println("folders");
		for (String ele : folders) { 
			System.out.println(ele);
		}
		
		System.out.println("\n\n\n\n\n" + "files");
		for (String ele : files) { 
			System.out.println(ele);
		}
		
	}
}
