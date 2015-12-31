package ltssh;




import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TestJDBC {

	@Test
	public void testJdbc() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ltcms","root","root");
		System.out.println(connection);
	}
	
	@Test
	public void testDatasource() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		System.out.println(context);
		ComboPooledDataSource data = (ComboPooledDataSource) context.getBean("dataSource");
		System.out.println(data);
		
		Connection connection = data.getConnection();
		System.out.println("+==="+connection);
	}
	
}
