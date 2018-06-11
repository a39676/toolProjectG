package pt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import demo.image.domain.ImageCache;
import encodeHandle.EncodeUtil;
import ioHandle.FileUtilCustom;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ImageCacheLocalHandle {

	private String imageName = "fileNameForMD5";
	private String tmpImageLocalPath = "d:/auxiliary/tmp/imageCache/";

	private String cacheFilePath = "D:/auxiliary/tmp/imageCache(2018-06-09 113222).txt";

	private String recordFilePath = "d:/auxiliary/tmp/recordImageCache.txt";
	private List<String> skipFileName;
	
	public void loadSkipFileName() {
		File f = new File(recordFilePath);
		if(!f.exists() ) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
		FileUtilCustom fu = new FileUtilCustom();
		String fileStr = fu.getStringFromFile(recordFilePath);
		skipFileName = Arrays.asList(fileStr.split("\n"));
	}
	
	public String getFileNameFromUrl(String urlStr) {
		urlStr = urlStr.replaceAll("\\s", "");
		Pattern pattern = Pattern.compile("^https?://(?:.*)(/\\S+\\.\\w{1,4})(?:\\?.*)?$");
		Matcher matcher = pattern.matcher(urlStr);
		if (matcher.find()) {
			return matcher.group(1).replaceAll("/", "");
		}
		return null;
	}

	public File getImageFromUrl(String urlStr, String savePath) {
		File f = new File(savePath);
		URL url;

		try {
			url = new URL(urlStr);
			ReadableByteChannel rbc = Channels.newChannel(url.openStream());
			FileOutputStream fos = new FileOutputStream(f.getAbsolutePath());
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			return f;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		f = null;
		return f;
	}

	private String getImageMD5(String filePath) {
		File oldFile = new File(filePath);

		System.out.println("creating md5 for:" + filePath);
		
		if(!oldFile.exists()) {
			return "fileNotExist";
		}
		String newPath = oldFile.getAbsolutePath().replace(oldFile.getName(), imageName);
		File newFile = new File(newPath);

		oldFile.renameTo(newFile);
		EncodeUtil eu = new EncodeUtil();

		String imageMD5 = new String(eu.byteArrayToHex16(eu.fileMD5(newPath)));

		newFile.renameTo(oldFile);
		return imageMD5;
	}


	public HashMap<String, Integer> getKeyIndex(String line) {
		String[] columnNames = line.split("\\t");
		HashMap<String, Integer> result = new HashMap<String, Integer>();

		for (int i = 0; i < columnNames.length; i++) {
			result.put(columnNames[i], i);
		}

		return result;
	}

	public void something(boolean skipFileExists) {
		FileUtilCustom fu = new FileUtilCustom();

		String fileStr = fu.getStringFromFile(cacheFilePath);

		List<ImageCache> caches = new ArrayList<ImageCache>();
		JSONObject jsonInput = JSONObject.fromObject(fileStr);
		JSONArray imageCacheJsons = jsonInput.getJSONArray("imageCache");
		
		HashMap<String, String> urlAndMd5 = new HashMap<String, String>();
		
		ImageCache tmpIc;
		for(int i = 0; i < imageCacheJsons.size(); i ++) {
			tmpIc = new ImageCache().createImageCacheFromJson(imageCacheJsons.getJSONObject(i));
			tmpIc.setIsDownload(true);
			tmpIc.setDownloadTime(new Date());
			caches.add(tmpIc);
		}
		
		caches.stream().forEach(ic -> urlAndMd5.put(ic.getImageUrl(), ""));
		
		File tmpFolder = new File(tmpImageLocalPath);
		if(!tmpFolder.exists()) {
			tmpFolder.mkdirs();
		}
		
		String fileName;
		int urlSize = urlAndMd5.size();
		int urlCount = 0;
		for(String url : urlAndMd5.keySet()) {
			System.out.println("getting from : " + url + " : " + LocalDateTime.now().toString());
			fileName = getFileNameFromUrl(url);
			urlCount++;
			if(fileName == null || fileName.equals("null")) {
				System.out.println("can not get: " + url);
				continue;
			}
			if(skipFileExists || skipFileName.contains(fileName)) {
				if(!new File(tmpImageLocalPath + fileName).exists()) {
					getImageFromUrl(url, tmpImageLocalPath + fileName);
				}
			} else {
				getImageFromUrl(url, tmpImageLocalPath + fileName);
			}
			System.out.println("get: " + fileName + ";(" + urlCount + "/" + urlSize + ")");
			fu.byteToFileAppendAtEnd((fileName + "\n").getBytes(), recordFilePath);
		}
		
		
		
		Long l1 = System.currentTimeMillis();
		Long l2 = l1 + (1000L * 30); // 30 seconds
		while(l1 < l2) {
			System.out.println("waiting..." + (l2 - l1) / 1000);
			try {
				Thread.sleep(10000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			l1 = System.currentTimeMillis();
		}
		
		imageCacheJsons.clear();

		String md5;
		for(ImageCache ic : caches) {
			md5 = getImageMD5(tmpImageLocalPath + ic.getImageName());
			ic.setMd5Mark(md5);
			urlAndMd5.put(ic.getImageUrl(), md5);
			imageCacheJsons.add(JSONObject.fromObject(ic));
		}
		
		JSONObject jsonOutput = new JSONObject();
		jsonOutput.put("imageCache", imageCacheJsons);
		
		try {
			fu.byteToFile(jsonOutput.toString().getBytes("utf8"), tmpImageLocalPath + "imageCacheHandled" + System.currentTimeMillis() + ".txt");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		System.out.println("done");
	}
	
	public List<String> getFieldsName(Object o) {
		Class<?> clazz = o.getClass();
		List<String> attrNames = new ArrayList<String>();
		for (Field field : clazz.getDeclaredFields()) {
			attrNames.add(field.getName());
		}
		return attrNames;
	}

	public static void main(String[] args) {
		ImageCacheLocalHandle ih = new ImageCacheLocalHandle();
		ih.loadSkipFileName();
		ih.something(false);
	}

}
