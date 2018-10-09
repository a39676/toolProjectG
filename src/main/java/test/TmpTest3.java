package test;

import java.io.IOException;
import java.util.UUID;

import net.sf.json.JSONObject;

public class TmpTest3 {
	
	public static void main(String[] args) throws IOException {
		
		TestA ab = new TestB();
		ab = new TestB();
		
		System.out.println(UUID.randomUUID().toString().replaceAll("-", "").replaceAll("a", "["));
		
	}

}

class TestA {
	
	static {
		System.out.println("1");
	}
	
	public TestA() {
		System.out.println("2");
	}
}

class TestB extends TestA {
	static {
		System.out.println("x");
	}
	
	public TestB() {
		System.out.println("y");
	}
}