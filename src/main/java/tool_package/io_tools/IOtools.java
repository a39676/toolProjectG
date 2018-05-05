package tool_package.io_tools;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;


public class IOtools {
	
	public String getStringFromFile(String filePath) {
		
		try {
			FileReader file = new FileReader(filePath);
			BufferedReader br = new BufferedReader(file);
			StringBuffer sb = new StringBuffer();
			
			String sCurrentLine = null;
			
			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine + "\n");
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			
		}
	}
	
	public byte[] getByteFromFile(String path) throws Exception {  
        byte[] b = null;  
        File file = new File(path);  
  
        FileInputStream fis = null;  
        ByteArrayOutputStream ops = null;  
        try {  
  
            if (!file.exists()) {  
                System.out.println("文件不存在！");  
            }  
            if (file.isDirectory()) {  
                System.out.println("不能上传目录！");  
            }  
  
            byte[] temp = new byte[2048];  
  
            fis = new FileInputStream(file);  
            ops = new ByteArrayOutputStream(2048);  
  
            int n;  
            while ((n = fis.read(temp)) != -1) {  
                ops.write(temp, 0, n);  
            }  
            b = ops.toByteArray();  
        } catch (Exception e) {  
            throw new Exception();  
        } finally {  
            if (ops != null) {  
                ops.close();  
            }  
            if (fis != null) {  
                fis.close();  
            }  
        }  
        return b;  
    }
	
	public void byteToFile(byte[] byteArray, String filePath) {
		
		if(byteArray == null || byteArray.length <= 0) {
			return;
		}
		
		File file = new File(filePath);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}

		if(!file.isFile()) {
			return;
		}
		
		
		
		
		try {
			FileUtils.writeByteArrayToFile(file, byteArray);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void fileRecode(String inputPath, String outputPath, String inputCode, String outputCode) throws Exception {  
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
  
        FileInputStream fis = null;  
        ByteArrayOutputStream ops = null;  
        
        try {  
        	
        	if (!inputFile.exists()) {  
        		System.out.println("文件不存在！");  
        		return;
        	}  
        	if (inputFile.isDirectory()) {  
        		System.out.println("不能指定一个目录！");
        		return;
        	}  
        	if(!outputFile.exists()) {
        		outputFile.createNewFile();
        	}
        	
            BufferedReader br = new BufferedReader(
         		   new InputStreamReader(
                           new FileInputStream(inputPath), inputCode));
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputFile, true), outputCode);
            
            String line = br.readLine();
            while (line != null) {
                if(line != null && line.length() > 0) {
        			writer.write(line);
        		}
                line = br.readLine();
            }
            br.close();
            writer.close();
            
        } catch (Exception e) {  
        	e.printStackTrace();
        } finally {  
        	if (ops != null) {  
        		ops.close();  
        	}  
        	if (fis != null) {  
        		fis.close();  
        	}  
        }  
        
	}
	
	public void byteToFileAppendAtEnd(byte[] byteArray, String filePath) {
		
		if(byteArray == null || byteArray.length <= 0) {
			return;
		}
		
		File file = new File(filePath);
		if(!file.exists() || !file.isFile()) {
			return;
		}
		
		try {
			FileUtils.writeByteArrayToFile(file, byteArray, true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public Properties getPropertiesFromFile(String filePath) {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(filePath);

			prop.load(input);

			return prop;

		} catch (Exception ex) {
			ex.printStackTrace();
			
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	
	public boolean setPropertiesToFile(String filePath, HashMap<String, String> propertiesMap) {
		
		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream(filePath);

			for(Map.Entry<String, String> entry : propertiesMap.entrySet()) {
				prop.setProperty(entry.getKey(), entry.getValue());
			}

			prop.store(output, null);
			
			return true;
		} catch (Exception io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		
		return false;
	}

}
