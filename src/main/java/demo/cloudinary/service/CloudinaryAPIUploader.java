package demo.cloudinary.service;

import java.io.File;
import java.io.IOException;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import demo.cloudinary.pojo.dto.CloudinaryUploadResult;
import net.sf.json.JSONObject;

public class CloudinaryAPIUploader {
	
	public CloudinaryUploadResult uploadCore(File f) throws IOException {

		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", "dy20bdekn",
				  "api_key", "915927123645857",
				  "api_secret", "k8483Du7gcUp49UrKKnm9RgXsiYf"));
		
		JSONObject resultJson = JSONObject.fromObject(cloudinary.uploader().upload(f, ObjectUtils.emptyMap()));
		CloudinaryUploadResult result = buildResult(resultJson);
		return result;
	}
	
	private CloudinaryUploadResult buildResult(JSONObject j) {
		CloudinaryUploadResult r = new CloudinaryUploadResult();
		r.setSecureUrl(j.getString("secure_url"));
		r.setOriginalFilename(j.getString("original_filename"));
		r.setPublicId(j.getString("public_id"));
		return r;
	}
}
