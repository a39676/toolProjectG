package utils.mybatis_util;

import java.io.File;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class MybatisUtil {
	
private static SqlSessionFactory sqlSessionFactory;
	
	static {
		String resource = "xmlConfig/memoryJoy.xml";
		Reader reader;
		try {
			reader = Resources.getResourceAsReader(resource);
			File file = new File(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

}
