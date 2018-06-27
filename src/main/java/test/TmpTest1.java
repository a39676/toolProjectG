package test;

import java.util.ArrayList;
import java.util.List;

public class TmpTest1 {

	public static void main(String[] args) {
		
		List<Integer> l = new ArrayList<Integer>();
		
		for(int i = 0; i < 30; i++) {
			l.add(i);
			if(i % 10 == 0 || i == 30) {
				System.out.println(l);
				l.clear();
			}
		}
		
	}
	
}
