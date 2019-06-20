package demo.cloudinary;

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

public class RandomFileMove {
	

	public static void main(String[] args) throws IOException {
		ChannelType ct = ChannelType.pet;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStr = sdf.format(new Date());
		String sourceFolderPath = "g:/imageCache/notPostYet/" + ct.getChannelTypeCode() + "/";
		String targetFloderPath = "g:/imageCache/" + dateStr + "/" + ct.getChannelTypeName() + "/";
		
		File sourceFolder = new File(sourceFolderPath);
		File targetFolder = new File(targetFloderPath);
		if(!targetFolder.exists()) {
			targetFolder.mkdirs();
		}
		List<File> files = Arrays.asList(sourceFolder.listFiles());
		
		
		int tmpIndex = 0;
		List<String> fileNameRecord = new ArrayList<String>();
		File tmpFile = null;
		for(int i = 0; i < 120 && files.size() > 0; i++) {
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
