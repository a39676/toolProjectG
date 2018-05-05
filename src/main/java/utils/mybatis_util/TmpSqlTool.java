package utils.mybatis_util;

import org.apache.ibatis.session.SqlSession;

public class TmpSqlTool {
	
	public static void main(String[] args) {
		
		SqlSession session = MybatisUtil.getSqlSessionFactory().openSession();
		
	}

}
