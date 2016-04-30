/**
 * Tm系统平台
 * tz_desk
 * com.tz.util
 * StringUtils.java
 * 创建人:xuchengfei 
 * 时间：2015年9月1日-下午11:45:45 
 * 2015Tm公司-版权所有
 */
package com.lt.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.apache.struts2.json.JSONException;

import sun.misc.BASE64Encoder;

/**
 * 
 * StringUtils
 * 创建人:xuchengfei 
 * 时间：2015年9月1日-下午11:45:45 
 * @version 1.0.0
 * 
 */
public class StringUtils {
	
	/**
	 * 空判断 
	 * 方法名：isEmpty
	 * @param content
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isEmpty(String content){
		return (content==null || content.equals(""))?true:false;
	}
	
	/**
	 * 非空判断
	 * com.tz.util 
	 * 方法名：isNotEmpty
	 * 创建人：xuchengfei 
	 * 时间：2015年9月1日-下午11:48:08 
	 * @param content
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isNotEmpty(String content){
		return !isEmpty(content);
	}
	
	
	public static String base64Encode(byte[] b) {
		if (b == null) {
			return null;
		}
		return new BASE64Encoder().encode(b);
	}
	
	/**
	 * md5加密
	 * 方法名：md5Base64<BR>
	 * 创建人：潭州学院-keke <BR>
	 * 时间：2014年11月28日-上午12:51:12 <BR>
	 * @param str
	 * @return String<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static String md5Base64(String str) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			return base64Encode(md5.digest(str.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 格式化日期类
	 * com.tz.util 
	 * 方法名：formatDate
	 * 创建人：xuchengfei 
	 * 手机号码:15074816437
	 * 时间：2015年9月10日-下午11:01:08 
	 * @param date
	 * @param pattern
	 * @return 
	 * 返回类型：String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String formatDate(Date date,String pattern){
		if(date!=null){
			String dateString = new SimpleDateFormat(pattern).format(date);
			return dateString;
		}else{
			return "";
		}
	}
	
	/**
	 * 后去文件的后缀
	 * 方法名：getExt
	 * @param name 文件名称
	 * @param flag true有点false没点
	 * @return 
	 * 返回类型：String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String getExt(String name,boolean flag){
		if(isNotEmpty(name)){
			String ext  = null;
			if(flag){
				ext = name.substring(name.lastIndexOf("."), name.length());
			}else{
				ext = name.substring(name.lastIndexOf(".")+1, name.length());
			}
			return ext;
		}else{
			return "";
		}
	}
	
	// 为上传文件自动分配文件名称，避免重复
	public static String generateFileName(String fileName,int randomNum,String dataPattern) {
		// 获得当前时间
		DateFormat format = new SimpleDateFormat(dataPattern);
		// 转换为字符串
		String formatDate = format.format(new Date());
		// 随机生成文件编号
		int random = new Random().nextInt(randomNum);
		// 获得文件后缀名称
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		// 组成一个新的文件名称
		return formatDate + random + extension;
	}
	
	
	/**
	 * 根据File文件的长度统计文件的大小
	 * 
	 * @param size
	 *            File的长度 file.lenght()
	 * @return 返回文件大小
	 */
	public static String countFileSize(long fileSize) {
		String fileSizeString = "";
		try {
			DecimalFormat df = new DecimalFormat("#.00");
			long fileS = fileSize;
			if (fileS == 0) {
				fileSizeString = "0KB";
			} else if (fileS < 1024) {
				fileSizeString = df.format((double) fileS) + "B";
			} else if (fileS < 1048576) {
				fileSizeString = df.format((double) fileS / 1024) + "KB";
			} else if (fileS < 1073741824) {
				fileSizeString = df
						.format(((double) fileS / 1024 / 1024) - 0.01)
						+ "MB";
			} else {
				fileSizeString = df.format((double) fileS / 1024 / 1024 / 1024)
						+ "G";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileSizeString;
	}
	
	
	public static String conversionSpecialCharacters(String string) {
		return string.replaceAll("\\\\", "/");
	}

	public static void main(String[] args) throws JSONException {
		
//		System.out.println(formatDate(new Date(),"MM/dd/yyyy"));\
//		String str ="0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
//		String randomStr = "";
//		for (int i = 0; i < 4; i++) {
//			int index = new Random().nextInt(str.length());
//			randomStr +=str.charAt(index);
//		}
//		System.out.println(randomStr);
		
//		Random random = new Random();
//		int[] str ={0,1,2,3};
//	    List list= new ArrayList();
//	    int start = random.nextInt(100);
//	    int end = random.nextInt(100);
//	    int op = random.nextInt(str.length);
//	    list.add(start);
//	    list.add(op);
//	    list.add(end);
//	    
//	    System.out.println(list);
		
		 //有红、白、黑三种球若干个，其中红、白球共25个，白、黑球共31个，红、黑球共28个，求这三种球各多少个？  用循环做   老师给个思路呗
		
//		String name = "aa22222222222.txt";
//		System.out.println(getExt(name, true));
//		System.out.println(getExt(name, false));
//		System.out.println(generateFileName("aaa.txt",10000,"yyyyMMddHHmmss"));
//		System.out.println(countFileSize(102445));
		
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("name", "keke");
		map.put("address", "湖南长沙");
		
		
//		String jsonString = JSONUtil.serialize(map);
//		System.out.println(jsonString);
//		
		
		
		
	}
}
