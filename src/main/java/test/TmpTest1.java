package test;

import test.sub.CA;
import test.sub.CB;

public class TmpTest1 {

	public static void main(String[] args) {
		
		CB cb = new CB();
		cb.add();
		s(cb);
	
	}
	
	public static void s(CA tmpA) {
		System.out.println(tmpA.a);
	}
}


