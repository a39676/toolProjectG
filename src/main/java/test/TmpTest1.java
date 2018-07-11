package test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TmpTest1 {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		System.out.println(sdf.format(d));
	}
}
