package test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONObject;

public class TmpTest3 {
	
	public static void main(String[] args) throws IOException {
		
		JSONObject json = JSONObject.fromObject("{\"key\":\"val\"}");
		
		System.out.println(json.getString("key"));
		
	}

}
