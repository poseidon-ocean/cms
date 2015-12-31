package ltssh;



import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lt.bean.Content;
import com.lt.dao.content.IContentDao;
import com.lt.util.LtStringUtil;
import com.lt.util.LtStringUtils;
import com.lt.util.TmGatherContentUtil;
import com.lt.util.Util;

public class LinkTest {

	@Test
	public void testGather() throws Exception {
		Document document = Jsoup.parse(new URL("http://www.xinhuanet.com/"),3000);
		Elements links = document.getElementsByTag("a");
		for(Element element:links){
			String href = element.attr("href");
			System.out.println(href);
		}
	}
	
	//抓取数据保存到Content
	/**
	 * http://www.xinhuanet.com/
	 * http://news.xinhuanet.com/politics/
	 * 
	 * http://sike.news.cn/statics/sike/posts/2015/12/219488167.html
	 * 
	 */
	@Test
	public void testSave() throws Exception {
		String url = "http://www.xinhuanet.com/";
		String filterUrl  = "http://news.xinhuanet.com/politics/";
		@SuppressWarnings("resource")
		ApplicationContext cxt = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		IContentDao cdao = null;
		try {
			cdao = cxt.getBean(IContentDao.class);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("-----------------");
		}
		
		//http://www.xinhuanet.com/
		//第一步：导入解析包jsoup.jar java中的javascript+jquery
		//第二步：根据URL获取网页源代码。
		Document document = Jsoup.parse(TmGatherContentUtil.getHtmlResourceByURL(url,"GBK"));//java.net下面api
		//第三步：抓取网页中所有需要的URL
		Elements links = document.getElementsByTag("a");
		Set<String> urls = new HashSet<String>();
		for (Element element : links) {
			String href = element.attr("href");
			if(LtStringUtils.isNotEmpty(href) && href.startsWith(filterUrl)){
				urls.add(href);
				//System.out.println("============"+href);
			}
		}
		int i = 0;
		//第四步：解析匹配出来的URL，讲需要的匹配出来
		for (String string : urls) {
			try {
				Date pdate = null;
				Document document2 = Jsoup.parse(TmGatherContentUtil.getHtmlResourceByURL(string,"utf-8"));
				String title = document2.getElementById("title").html();
				String content = document2.getElementById("content").html();
				String pubtime = document2.getElementById("pubtime").html();
				if(!Util.isEmpty(pubtime)){
					pdate = Util.getDate(pubtime, "yyyy年MM月dd日 HH:mm:ss");
				}
				String source = document2.getElementById("source").html();
				if(!Util.isEmpty(source)){
					source = source.substring(source.indexOf("：")+1);
				}
				Content content2 = new Content();
				content2.setTitle(title);
				content2.setContent(content);
				content2.setPublishDate(pdate);
				content2.setComeForm(source);
				cdao.save(content2);
				
				
				++i;
				System.out.println(i+"=============================================");
				System.out.println(title);
				System.out.println(pubtime);
				System.out.println(pdate);
				System.out.println(source);
				System.out.println(Jsoup.clean(content, Whitelist.simpleText()));
			} catch (Exception e) {
				continue;
			}
		}
		//第五步：保存数据库
	}
	
	
	@Test
	public void testSaveSk() throws Exception {
		String url = "http://sike.news.cn/";
		String filterUrl  = "http://sike.news.cn/statics/sike/posts/";
		@SuppressWarnings("resource")
		ApplicationContext cxt = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		IContentDao cdao = null;
		try {
			cdao = cxt.getBean(IContentDao.class);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("-----------------");
		}
		
		//http://www.xinhuanet.com/
		//第一步：导入解析包jsoup.jar java中的javascript+jquery
		//第二步：根据URL获取网页源代码。
		Document document = Jsoup.parse(TmGatherContentUtil.getHtmlResourceByURL(url,"GBK"));//java.net下面api
		//第三步：抓取网页中所有需要的URL
		Elements links = document.getElementsByTag("a");
		Set<String> urls = new HashSet<String>();
		for (Element element : links) {
			String href = element.attr("href");
			if(LtStringUtils.isNotEmpty(href) && href.startsWith(filterUrl)){
				urls.add(href);
				//System.out.println("============"+href);
			}
		}
		int i = 0;
		//第四步：解析匹配出来的URL，讲需要的匹配出来
		for (String string : urls) {
			try {
				Date pdate = null;
				Document document2 = Jsoup.parse(TmGatherContentUtil.getHtmlResourceByURL(string,"utf-8"));
				String title = document2.getElementsByTag("h3").get(0).text();
				String author = document2.getElementsByClass("thename").get(0).select("a").text();
				
				
				String content = document2.getElementsByClass("txt_zw").get(0).html();
				String pubtime = document2.getElementsByClass("thedate2").get(0).html();
				if(!Util.isEmpty(pubtime)){
					pubtime = LtStringUtil.getDateTime(pubtime);
					pdate = Util.getDate(pubtime, "MM/dd HH:mm");
				}
				String source = "新华网思客";
				Content content2 = new Content();
				content2.setTitle(title);
				content2.setContent(content);
				content2.setPublishDate(pdate);
				content2.setComeForm(source);
				content2.setAuthor(author);
				cdao.save(content2);
				
				
				++i;
				System.out.println(i+"=============================================");
				System.out.println(title);
				System.out.println(pubtime);
				System.out.println(pdate);
				System.out.println(source);
				System.out.println(Jsoup.clean(content, Whitelist.simpleText()));
			} catch (Exception e) {
				continue;
			}
		}
		//第五步：保存数据库
	}
	
	@Test
	public void testTag() throws Exception {
		Document document2 = Jsoup.parse(TmGatherContentUtil.getHtmlResourceByURL("http://sike.news.cn/statics/sike/posts/2015/12/219487568.html","utf-8"));
		String pubtime = document2.getElementsByClass("thedate2").get(0).text();
		System.out.println(pubtime);
		if(!Util.isEmpty(pubtime)){
			
			Pattern p=Pattern.compile("\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{1,2}"); 
			Matcher m = p.matcher(pubtime);
			if(m.find()){
				pubtime = m.group();
			}
			System.out.println("=="+pubtime+"==");
			Date date = Util.getDate(pubtime, "MM/dd HH:mm");
			System.out.println(date);
		}
		
		
	}
	
	@Test
	public void testRex() throws Exception {
//		String rex = "/\\d{2}/\\d{2}\\s+\\d{2}:\\d{2}/";
		String rex = "/\\[01]\\d/[0123]\\d\\s{1,2}[012]\\d:[0-6]\\d/";
		String mastr = "发表于  12/21 06:30   ";
		mastr = mastr.replaceAll(rex, "").trim();
		System.out.println(mastr);
	}
	
	@Test
	public void testDate() throws Exception {
		String str = " 12/21 06:30 ";
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd HH:mm");
		try {
			Date res = sdf.parse(str);
			System.out.println(res);
		} catch (ParseException e) {
			System.out.println("解析时间出错!");
		}
	}
	
	//^\d{4}(\-|\/|\.)\d{1,2}\1\d{1,2}$
	@Test
	public void testMatch() throws Exception {
		String rex = "/\\[01]\\d/[0123]\\d\\s{1,2}[012]\\d:[0-6]\\d/";
		String mastr = "发表于  12/21 06:30   ";
		mastr = mastr.replaceAll(rex, "").trim();
		System.out.println(mastr);
		
		Pattern p=Pattern.compile("\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{1,2}"); 
		Matcher m = p.matcher("发表于  12/21 06:30  8分钟");
		
		System.out.println(p.pattern());
		//System.out.println(m.find());
		if(m.find()){
			System.out.println(m.group());
		}
	}
	
}
