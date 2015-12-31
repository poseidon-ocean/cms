package ltssh;


import java.util.Date;

import org.junit.Test;

import com.lt.util.TmDateUtil;

public class TestDate {

	@Test
	public void testTime() throws Exception {
		String strTime = "2014-12-12 12:12:12";
		Date date = TmDateUtil.dateToString(strTime);
		
		System.out.println(date);
		
		System.out.println(TmDateUtil.getTimeFormat(strTime));
	}
}
