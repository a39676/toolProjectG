package demo.cloudinary;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import ioHandle.FileUtilCustom;
import net.sf.json.JSONObject;

public class CloudinaryAPITester {
	
	public static void main(String[] args) throws IOException {
		ChannelType ct = ChannelType.c9;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStr = sdf.format(new Date());
		String targetFloderPath = "g:/imageCache/" + dateStr + "/" + ct.getChannelTypeName();
		int imageTag = ct.getChannelTypeCode();

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
			System.out.println(tmpResult.getString("original_filename") + " uploaded" + "(" + i + "/" + files.length + ")\n" 
			+ "('" + tmpResult.getString("secure_url") + "','" + tmpResult.getString("original_filename") + "','" + tmpResult.getString("public_id") + "'," + imageTag + "),");
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
