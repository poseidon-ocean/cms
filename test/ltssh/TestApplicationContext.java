package ltssh;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lt.bean.Content;
import com.lt.service.content.IContentService;
import com.lt.web.content.ContentAction;
 
public class TestApplicationContext {

	@Test
	public void testContent() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		System.out.println(context);
		ContentAction contentAction = context.getBean(ContentAction.class);
		System.out.println(contentAction);
		Content content = new Content();
		content.setId(1);
		content.setTitle("nih");
		content.setContent("neir");
		try {
			
			IContentService cser = context.getBean(IContentService.class);
			cser.save(content);
			System.out.println(cser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//contentAction.save(content);;
	}
	
}
