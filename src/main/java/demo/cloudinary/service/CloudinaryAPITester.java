package demo.cloudinary.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import demo.cloudinary.pojo.dto.CloudinaryUploadResult;
import demo.cloudinary.pojo.type.ChannelType;
import ioHandle.FileUtilCustom;
import net.sf.json.JSONObject;

public class CloudinaryAPITester {
	
	ChannelType ct = ChannelType.zoo;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	String dateStr = sdf.format(new Date());
	String targetFloderPath = "f:/imageCache/" + dateStr + "/" + ct.getName();
	String sqlOutputTxt = ct.getName() + "_sql.txt";
	String uploadResultTxt = ct.getName() + "_uploaded.txt"; 

	public void upload() throws IOException {
		int imageTag = ct.getCode();

		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", "dy20bdekn",
				  "api_key", "915927123645857",
				  "api_secret", "k8483Du7gcUp49UrKKnm9RgXsiYf"));
		
		File targetFolder = new File(targetFloderPath);
		File[] files = targetFolder.listFiles();
		
		
		JSONObject tmpResultJson = null;
		CloudinaryUploadResult tmpResult = null;
		File f = null;
		for(int i = 0; i < files.length;  i++) {
			f = files[i];
			tmpResultJson = JSONObject.fromObject(cloudinary.uploader().upload(f, ObjectUtils.emptyMap()));
			tmpResult = buildResult(tmpResultJson);
			outputSqlTxt(tmpResult, String.valueOf(imageTag));
			outputUploadResultTxt(tmpResult);
			System.out.println(tmpResult.getOriginalFilename() + " uploaded" + "(" + i + "/" + files.length + ")\n" 
			+ "('" + tmpResult.getSecureUrl() + "','" + tmpResult.getOriginalFilename() + "','" + tmpResult.getPublicId() + "'," + imageTag + "),");
		}
		
	}

	public void outputSqlTxt(CloudinaryUploadResult r, String imageTag) {
		/* 
		 * insert into image_cloudinary_local_record(image_url, image_name, cloud_public_id, image_tag) values
		 *  */
		FileUtilCustom io = new FileUtilCustom();
		String line = "('" + r.getSecureUrl() + "','" + r.getOriginalFilename() + "','" + r.getPublicId() + "'," + imageTag + ")," + "\n";
		io.byteToFileAppendAtEnd(line.getBytes(), targetFloderPath + "/" + sqlOutputTxt);
		System.out.println(line);
	}
	
	public void outputUploadResultTxt(CloudinaryUploadResult r) {
		FileUtilCustom io = new FileUtilCustom();
		String line = r.getOriginalFilename() + "\n";
		io.byteToFileAppendAtEnd(line.getBytes(), targetFloderPath + "/" + uploadResultTxt);
		System.out.println(line);
	}
	
	public CloudinaryUploadResult buildResult(JSONObject j) {
		CloudinaryUploadResult r = new CloudinaryUploadResult();
		r.setSecureUrl(j.getString("secure_url"));
		r.setOriginalFilename(j.getString("original_filename"));
		r.setPublicId(j.getString("public_id"));
		return r;
	}
}
