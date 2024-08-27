package api;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class dbcon {
	
	/*
	public DataSource dataSource2() {
		org.apache.tomcat.jdbc.pool.DataSource bs = new org.apache.tomcat.jdbc.pool.DataSource();
	*/
	@Bean(destroyMethod = "close")	
	public BasicDataSource dataSource2() {
		BasicDataSource bs = new BasicDataSource();
		bs.setDriverClassName("com.mysql.cj.jdbc.Driver");
		bs.setUrl("jdbc:mysql://localhost:3306/cms");
		bs.setUsername("hana");
		bs.setPassword("hana1234");
		bs.setInitialSize(2);	//IP당 사용할수 있는 연결 개수
		bs.setMaxActive(10);	//동시에 사용할 수 있는 최대 연결 개수
		bs.setTestWhileIdle(true);	//비활성화된 DB연결 부분을 유효성 검사
		bs.setMinEvictableIdleTimeMillis(60000 * 3);	//DB연결 유지시간
		return bs;
	}
	
}
