package test;

import java.io.IOException;
import java.util.UUID;

public class TmpTest3 {
	
	public static void main(String[] args) throws IOException {
		
		System.out.println(UUID.randomUUID().toString().replaceAll("-", "").replaceAll("a", "["));
		
	}

}
