package utils.sql_utils;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class ConnectTest {
	
	public static void main(String[] args) {
		
		Connection connect = null;
		String file = "d:" + File.separator + "auxiliary" + File.separator + "Item" + File.separator +
				"MySqlTestDB01.properties";
		
		try {
			connect = MySQLDBUtility.getConnection(file);
			
//			PreparedStatement ps1 = connect.prepareStatement("show tables");
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery("show databases");
			ResultSetMetaData rsmd = rs.getMetaData();
//			while (rs.next()) {
//		        System.out.println("bas_customer: " + rs.getString("bas_customer") + ", " 
//			+ "customer_id_a: " + rs.getString("customer_id_a") + ", "
//			+ "customer_id_b: " + rs.getString("customer_id_b"));
//		      }
			try {
				System.out.println(rsmd.getColumnCount());
				int colCount = rsmd.getColumnCount();
				for (int i = 0; i <colCount; i++) {
					System.out.println(rsmd.getColumnName(i+1));
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
