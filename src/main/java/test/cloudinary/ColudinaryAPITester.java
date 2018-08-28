package test.cloudinary;

import java.io.File;
import java.io.IOException;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import net.sf.json.JSONObject;

public class ColudinaryAPITester {
	
	public static void main(String[] args) throws IOException {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", "dy20bdekn",
				  "api_key", "915927123645857",
				  "api_secret", "k8483Du7gcUp49UrKKnm9RgXsiY"));
		
		File toUpload = new File("d:/auxiliary/tmp/01.jpeg");
		JSONObject uploadResult = JSONObject.fromObject(cloudinary.uploader().upload(toUpload, ObjectUtils.emptyMap()));
		System.out.println(uploadResult);
	}

}
