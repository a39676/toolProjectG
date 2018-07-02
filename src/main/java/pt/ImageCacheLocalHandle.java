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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
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
	private String resultOutputPath = "d:/auxiliary/tmp/";

	private String fileNamePart = "imageCache(2018-06-30 171105)";
	private String cacheFilePath = "D:/auxiliary/tmp/" + fileNamePart + ".txt";

	private String recordFilePath = "d:/auxiliary/tmp/recordImageCache.txt";
	private List<String> skipFileName;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
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

	public File downloadImageFromUrl(String savePathPrefix, ImageCache ic) {
		File f = new File(createStorePath(savePathPrefix, ic));
		if(!f.getParentFile().exists()) {
			f.getParentFile().mkdirs();
		}
		URL url;

		try {
			url = new URL(ic.getImageUrl());
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

	private String createStorePath(String savePathPrefix, ImageCache ic) {
		return savePathPrefix + sdf.format(ic.getCreateTime()) + "/" + String.valueOf(ic.getImageTag().intValue()) + "/" + ic.getImageName();
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
		
//		HashMap<String, String> urlAndMd5 = new HashMap<String, String>();
		HashMap<String, ImageCache> imageCacheByUrl = new HashMap<String, ImageCache>();
		
		ImageCache tmpIc;
		for(int i = 0; i < imageCacheJsons.size(); i ++) {
			tmpIc = new ImageCache().createImageCacheFromJson(imageCacheJsons.getJSONObject(i));
			tmpIc.setIsDownload(true);
			tmpIc.setDownloadTime(new Date());
			caches.add(tmpIc);
		}
		
		caches.stream().forEach(ic -> ic.setImageName(getFileNameFromUrl(ic.getImageUrl())));
		caches.stream().forEach(ic -> imageCacheByUrl.put(ic.getImageUrl(), ic));
		
		
		File tmpFolder = new File(tmpImageLocalPath);
		if(!tmpFolder.exists()) {
			tmpFolder.mkdirs();
		}
		
		String fileName;
		int urlSize = imageCacheByUrl.size();
		int urlCount = 0;
		for(Entry<String, ImageCache> entry : imageCacheByUrl.entrySet()) {
			System.out.println("getting from : " + entry.getValue().getImageName() + " : " + LocalDateTime.now().toString());
			fileName = getFileNameFromUrl(entry.getKey());
			urlCount++;
			if(fileName == null || fileName.equals("null")) {
				System.out.println("can not get: " + entry.getKey());
				continue;
			}
			if(skipFileExists || skipFileName.contains(fileName)) {
				if(!new File(tmpImageLocalPath + fileName).exists()) {
					downloadImageFromUrl(tmpImageLocalPath, entry.getValue());
				}
			} else {
				downloadImageFromUrl(tmpImageLocalPath, entry.getValue());
			}
			System.out.println("get: " + fileName + ";(" + urlCount + "/" + urlSize + ")");
			fu.byteToFileAppendAtEnd((fileName + "\n").getBytes(), recordFilePath);
		}
		
		
		
		Long l1 = System.currentTimeMillis();
		Long l2 = l1 + (1000L * 3); // 3 seconds
		while(l1 < l2) {
			System.out.println("waiting..." + (l2 - l1) / 1000);
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			l1 = System.currentTimeMillis();
		}
		
		imageCacheJsons.clear();

		String md5;
		
		for(Entry<String, ImageCache> entry : imageCacheByUrl.entrySet()) {
			md5 = getImageMD5(createStorePath(tmpImageLocalPath, entry.getValue()));
			entry.getValue().setMd5Mark(md5);
		}
		
		for(ImageCache ic : caches) {
			ic.setMd5Mark(imageCacheByUrl.get(ic.getImageUrl()).getMd5Mark());
			imageCacheJsons.add(JSONObject.fromObject(ic));
		}
		
		JSONObject jsonOutput = new JSONObject();
		jsonOutput.put("imageCache", imageCacheJsons);
		
		try {
			fu.byteToFile(jsonOutput.toString().getBytes("utf8"), resultOutputPath + "imageCacheHandled" + System.currentTimeMillis() + ".txt");
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
