package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ioHandle.FileUtilCustom;

public class TmpTest2 {
	
//	TODO
//	尚不可删除
//	
	private static FileUtilCustom io = new FileUtilCustom();
	private static String mainFloderPath = "d:/auxiliary/tmp/test/";

	public static void main(String[] args) throws IOException {
		
		File mainFolder = new File(mainFloderPath);
		
		File[] files = mainFolder.listFiles();
		List<String> imagePathList = new ArrayList<String>();
		String tmpSrc = null;
		for(File f : files) {
			if(f.isFile()) {
				tmpSrc = getTargetImagePath(f.getName());
				if(!StringUtils.isBlank(tmpSrc)) {
					tmpSrc = tmpSrc.substring(2, tmpSrc.length());
					imagePathList.add(mainFloderPath + tmpSrc);
					System.out.println(mainFloderPath + tmpSrc);
				}
			}
		}
		
		File tmpFile = null;
		File tmpResultFile = null;
		for(String sf : imagePathList) {
			tmpFile = new File(sf);
			tmpResultFile = new File(mainFloderPath + tmpFile.getName());
			FileUtils.copyFile(tmpFile, tmpResultFile);
		}
		
//		System.out.println(imagePathList);
		
		
	}
	
	public static String getTargetImagePath(String fileName) {
		Document doc = null;
		String htmlStr = io.getStringFromFile(mainFloderPath + fileName);
//		doc = Jsoup.connect("https://500px.com/photo/266323287/katarina-s-ass-by-nikita-shvedov").get();
		doc = Jsoup.parse(htmlStr); 
		Elements eleClazzPhoto = doc.getElementsByClass("photo");
		String src = null;
		for(Element ele : eleClazzPhoto) {
			if(!StringUtils.isBlank(ele.attr("src"))) {
				src = ele.attr("src");
			}
		}
		return src;
	}
//	D:\auxiliary\tmp\test\Dawn at Sasamat Lake. 作者 Salvador Boissett - 照片 130195631 _ 500px_files
}
