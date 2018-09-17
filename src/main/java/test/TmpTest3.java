package test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TmpTest3 {
	
	public static void main(String[] args) throws IOException {
		
		String sourceFolderPath = "g:/imageCache/notPostYet/1/";
		
		File folder = new File(sourceFolderPath);
		List<File> files = Arrays.asList(folder.listFiles());
		
		files.stream().filter(f -> f.getName().contains("(1)")).forEach(f -> System.out.println(f.getName()));
		
	}

}
