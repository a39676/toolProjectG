package demo.cloudinary.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import demo.cloudinary.pojo.type.ChannelType;

public class RandomFileMove {
	

	public static void main(String[] args) throws IOException {
		ChannelType ct = ChannelType.c10;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStr = sdf.format(new Date());
		String sourceFolderPath = "f:/imageCache/notPostYet/" + ct.getName() + "/";
		String targetFloderPath = "f:/imageCache/" + dateStr + "/" + ct.getName() + "/";
		
		File sourceFolder = new File(sourceFolderPath);
		File targetFolder = new File(targetFloderPath);
		if(!targetFolder.exists()) {
			targetFolder.mkdirs();
		}
		List<File> files = Arrays.asList(sourceFolder.listFiles());
		
		
		int tmpIndex = 0;
		int movingCount = 12;  // 需要移动多少图片
		List<String> fileNameRecord = new ArrayList<String>();
		File tmpFile = null;
		for(int i = 0; i <= movingCount && files.size() > 0; i++) {
			files = Arrays.asList(sourceFolder.listFiles());
			if(files.size() > 1) {
				tmpIndex = ThreadLocalRandom.current().nextInt(0, files.size() - 1);
			} else {
				tmpIndex = 0;
			}
			tmpFile = files.get(tmpIndex);
			if(fileNameRecord.contains(tmpFile.getName())) {
				i--;
				continue;
			} else {
				System.out.println(tmpIndex + " moving " + "(" + i + "/" + files.size() + ") : " + tmpFile.getName());
				fileNameRecord.add(tmpFile.getName());
				Files.move(Paths.get(tmpFile.getAbsolutePath()), Paths.get(targetFloderPath + tmpFile.getName()), StandardCopyOption.REPLACE_EXISTING);
			}
		}
	}
}
