package com.lt.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LtStringUtil {

	/**
	 * 
	 * 描  述: 匹配  日期格式   12/21 06:30
	 * 例如  ：发表于  12/21 06:30   
	 *  <br>
	 * 时  间: 2015年12月24日 上午11:27:00
	 * 作者: poseidon<br>
	 * @param mastr
	 */
	public static String getDateTime(String mastr){
		String result = "";
		Pattern p=Pattern.compile("\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{1,2}"); 
		Matcher m = p.matcher(mastr);
		if(m.find()){
			result = m.group();
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(getDateTime("发表于  12/21 06:30   "));
	}
}
