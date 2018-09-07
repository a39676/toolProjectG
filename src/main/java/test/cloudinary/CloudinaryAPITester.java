package test.cloudinary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import ioHandle.FileUtilCustom;
import net.sf.json.JSONObject;

public class CloudinaryAPITester {
	
	private static ChannelType ct = ChannelType.c9;
	private static String targetFloderPath = "D:\\imageCache\\20180904\\" + ct.getChannelTypeName();
	private static int imageTag = ct.getChannelTypeCode();

	public static void main(String[] args) throws IOException {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", "dy20bdekn",
				  "api_key", "915927123645857",
				  "api_secret", "k8483Du7gcUp49UrKKnm9RgXsiYf"));
		
		File targetFolder = new File(targetFloderPath);
		File[] files = targetFolder.listFiles();
		
		
		JSONObject tmpResult = null;
		List<JSONObject> resultList = new ArrayList<JSONObject>();
		File f = null;
		for(int i = 0; i < files.length;  i++) {
			f = files[i];
			tmpResult = JSONObject.fromObject(cloudinary.uploader().upload(f, ObjectUtils.emptyMap()));
			resultList.add(tmpResult);
			System.out.println(tmpResult.getString("original_filename") + " uploaded" + "(" + i + "/" + files.length + ")");
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("insert into image_cloudinary_local_record(image_url, image_name, cloud_public_id, image_tag) values");
		
		for(JSONObject j : resultList) {
			sb.append("('" + j.getString("secure_url") + "','" + j.getString("original_filename") + "','" + j.getString("public_id") + "'," + imageTag + ")," + "\n");
		}
		
		for(JSONObject j : resultList) {
			sb.append(j.getString("secure_url") + "\n");
		}
		FileUtilCustom io = new FileUtilCustom();
		io.byteToFile(sb.toString().getBytes(), targetFloderPath + "/" + ct.getChannelTypeName() + ".txt");
		System.out.println(sb.toString());
	}

}
