package demo.cloudinary.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import demo.cloudinary.pojo.dto.CloudinaryUploadResult;
import demo.cloudinary.pojo.type.ChannelType;
import ioHandle.FileUtilCustom;

public class CloudinaryCore {
	
	private ChannelType ct = ChannelType.c1;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private String dateStr = sdf.format(new Date());
	private String mainFolder = "d:/auxiliary/imageCache";
	private String sourceFolderPath = mainFolder + "/notPostYet/" + ct.getName();
	private String targetFloderPath = mainFolder + "/" + dateStr + "/" + ct.getName() + "/";
	private String sqlOutputTxtPath = mainFolder + "/" + dateStr + "sql.txt";
	private String uploadResultTxtPath = mainFolder + "/" + dateStr + "uploaded.txt"; 
	private int imageTag = ct.getCode();

	public void outputSqlTxt(CloudinaryUploadResult r) {
		/* 
		 * insert into image_cloudinary_local_record(image_url, image_name, cloud_public_id, image_tag) values
		 *  */
		FileUtilCustom io = new FileUtilCustom();
		String line = "('" + r.getSecureUrl() + "','" + r.getOriginalFilename() + "','" + r.getPublicId() + "'," + imageTag + ")," + "\n";
		io.byteToFileAppendAtEnd(line.getBytes(), sqlOutputTxtPath);
		System.out.print(line);
	}
	
	public void outputUploadResultTxt(CloudinaryUploadResult r) {
		FileUtilCustom io = new FileUtilCustom();
		String line = r.getOriginalFilename() + "\n";
		io.byteToFileAppendAtEnd(line.getBytes(), uploadResultTxtPath);
		System.out.print(line);
	}
	
	public Set<String> getAlreadyUpload() {
		FileUtilCustom io = new FileUtilCustom();
		String content = io.getStringFromFile(uploadResultTxtPath);
		String[] lines = content.split("\n");
		Set<String> fileNames = new HashSet<String>();
		for(String line : lines) {
			fileNames.add(line);
		}
		return fileNames;
	}
	
	public static void main(String[] args) throws IOException {
		CloudinaryCore core = new CloudinaryCore();
		RandomFileMove mover = new RandomFileMove();
		mover.randomFileMove(core.sourceFolderPath, core.targetFloderPath, 0);
		
		File targetFoler = new File(core.targetFloderPath);
		File[] files = targetFoler.listFiles();
		Set<String> fileNames = core.getAlreadyUpload();
		
		CloudinaryAPIUploader uploader = new CloudinaryAPIUploader();
		CloudinaryUploadResult result = null;
		for(File f : files) {
			if(!fileNames.contains(f.getName())) {
				result = uploader.uploadCore(f);
				System.out.println(result.toString());
				core.outputSqlTxt(result);
				core.outputUploadResultTxt(result);
			}
		}
	}
}
