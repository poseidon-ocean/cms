package ltssh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lt.bean.Content;
import com.lt.dao.content.IContentDao;

/**
 * 
 * 类: JunitSpringTest <br>
 * 描述: spring框架和junit框架结合使用案例 <br>
 * 使用注解的方式读取配置文件，自动注入dao进行测试
 * 有点：干净整洁，代码优雅
 * 注意：@RunWith  @ContextConfiguration这两个注解的使用
 * 		我是导入了最新版本的junit.jar和hamcrest-core-1.3.jar
 * 		junit-4.4.jar版本的不支持
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class JunitSpringTest {

	@Autowired
	private IContentDao contentDao;
	
	@Test
	public void handler(){
		Content content = contentDao.get(1);
		System.out.println(content.getTitle());
	}
}
