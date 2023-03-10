package co.prod.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
// 현재 하는 import는 모두 apache (mybatis이므로)

// ojdbc 활용 -> db connection 객체 가지고 옴
// 특징 : db 요청 있을 때마다 객체 생성, db 접속 끝나면 반환
// connection 객체 : 서버에 미리 생성
// DataSource => Resource 가져오는 룩업;
public class DataSource {
	//싱글턴 방식
	private static SqlSessionFactory sqlSessionFactory;
	private DataSource() {}
	//정적 메소드로 선언 (new로 인스턴스를 계속 만들지 못하게 하기 위함)
	public static SqlSessionFactory getInstance() {
		String resource = "config/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
}
