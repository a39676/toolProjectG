package useTools;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MavenSettingFileRename {

	private static boolean setMyFile = false;

	private static String mavenFolderPath = "D:/soft/apache-maven-3.6.0";
	private static String mavenConfFolderPath = mavenFolderPath + "/conf/";
	private static String mySettingFileName = "settingsUse.xml";
	private static String jobSettingFileName = "settingsPlatenogroup.xml";
	private static String normalSettingFileName = "settings.xml";
	private static File mavenConfFolder = null;
	private static File normalSettingFile = null;

	static {
		mavenConfFolder = new File(mavenConfFolderPath);
		normalSettingFile = new File(mavenConfFolder + "/" + normalSettingFileName);
	}

	/**
	 * 随时切换maven的配置文件
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		if (!mavenConfFolder.exists() || mavenConfFolder.isFile()) {
			System.err.println("folder path error");
			return;
		}

		if (normalSettingFile.exists()) {
			if (!normalSettingFile.delete()) {
				System.err.println("normal file delete error");
				return;
			}
		}
		
		setMyFile = true;
		if(setMyFile) {
			copyFile(mavenConfFolderPath+"/"+mySettingFileName, mavenConfFolderPath+"/"+normalSettingFileName);
		} else {
			copyFile(mavenConfFolderPath+"/"+jobSettingFileName, mavenConfFolderPath+"/"+normalSettingFileName);
		}

	}

	private static void copyFile(String sourceFilePath, String targetFilePath) {
		try {
			File sourceFile = new File(sourceFilePath);
			Path targetPath = Paths.get(targetFilePath);
			File targetFile = new File(targetFilePath);
			Path originalPath = sourceFile.toPath();
			Files.copy(originalPath, targetPath, StandardCopyOption.REPLACE_EXISTING);

			if (targetFile.exists()) {
				Files.readAllLines(originalPath).equals(Files.readAllLines(targetPath));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
