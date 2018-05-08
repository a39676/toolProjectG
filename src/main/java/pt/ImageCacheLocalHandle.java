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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import demo.tool.domain.ImageCache;
import encodeHandle.EncodeUtil;
import ioHandle.FileUtilCustom;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ImageCacheLocalHandle {

	private String imageName = "fileNameForMD5";
	private String tmpImageLocalPath = "d:/auxiliary/tmp/imageCache/";

	private String cacheFilePath = "D:\\auxiliary\\tmp/imageCache(2018-05-04 133126).txt";

	public String getFileNameFromUrl(String urlStr) {
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

	public void something() {
		FileUtilCustom fu = new FileUtilCustom();

		String fileStr = fu.getStringFromFile(cacheFilePath);

		List<ImageCache> caches = new ArrayList<ImageCache>();
		JSONObject jsonInput = JSONObject.fromObject(fileStr);
		JSONArray imageCacheJsons = jsonInput.getJSONArray("imageCache");
		
		ImageCache tmpIc;
		for(int i = 0; i < imageCacheJsons.size(); i ++) {
			tmpIc = new ImageCache().createImageCacheFromJson(imageCacheJsons.getJSONObject(i));
			tmpIc.setIsDownload(true);
			tmpIc.setDownloadTime(new Date());
			caches.add(tmpIc);
		}
		
		for(ImageCache ic : caches) {
			getImageFromUrl(ic.getImageUrl(), tmpImageLocalPath + ic.getImageName());			
		}
		
		Long l1 = System.currentTimeMillis();
		Long l2 = l1 + (1000L * 60 * 10); // 10 minutes
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
			getImageFromUrl(ic.getImageUrl(), tmpImageLocalPath + ic.getImageName());
			md5 = getImageMD5(tmpImageLocalPath + ic.getImageName());
			ic.setMd5Mark(md5);
			imageCacheJsons.add(JSONObject.fromObject(ic));
		}
		
		JSONObject jsonOutput = new JSONObject();
		jsonOutput.put("imageCache", imageCacheJsons);
		
		try {
			fu.byteToFile(jsonOutput.toString().getBytes("utf8"), tmpImageLocalPath + "imageCacheHandled" + System.currentTimeMillis() + ".txt");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
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
		ih.something();
	}

}
