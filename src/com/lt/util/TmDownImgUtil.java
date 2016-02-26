package com.lt.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 
 *@Title:网上图片下载
 *@Description:
 *@Author:think
 *@Since:2016年2月26日 上午10:51:20
 *@Version:1.0.0
 */
public class TmDownImgUtil {
	
	/**
	 * 
	 * @Description:（用一句话描述这个方法是做什么的）
	 * @param netImg  下载图片URL
	 * @param targetPath  保存路径
	 * @return 
	 * @ReturnType:boolean
	 * @exception 
	 * @Since:2016年2月26日 上午10:52:21
	 * @Author:think
	 */
	public static boolean downLoadImg(String netImg,String targetPath){
		//URL--java.net-
		try {
			java.net.URL url = new URL(netImg);
			java.net.URLConnection urlConnection = url.openConnection();
			InputStream inputStream = urlConnection.getInputStream();
			File file = new File(targetPath);
			FileOutputStream out = new FileOutputStream(file);
			int i = 0;
			while ((i=inputStream.read()) !=-1) {
				out.write(i);
			}
			out.close();
			inputStream.close();
			System.out.println("网络图片地址:"+netImg+"下载完成");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("网络图片地址:"+netImg+"下载失败");
			return false;
		}
	}
	
	/**
	 * 
	 * @Description:获取源代码
	 * @param link
	 * @param econding
	 * @return 
	 * @ReturnType:String
	 * @exception 
	 * @Since:2016年2月26日 上午10:56:33
	 * @Author:think
	 */
	public static String htmlSource(String link,String econding){
		StringBuilder stringBuilder = new StringBuilder();
		try {
			URL url = new URL(link);
			URLConnection urlConnection = url.openConnection();
			urlConnection.setRequestProperty("User-Agent", "java");
			InputStream inputStream = urlConnection.getInputStream();
			InputStreamReader in = new InputStreamReader(inputStream,econding);
			BufferedReader reader = new BufferedReader(in);
			String line = null;
			while((line = reader.readLine())!=null){
				stringBuilder.append(line+"\r\n");
			}
			reader.close();
			in.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
	
//	
	public static void main(String[] args) {
//		downLoadImg("http://9.url.cn/edu/banner/img/b036349d_yb_758_280.jpg","d://test/1111111111.jpg");
//		downLoadImg("http://9.url.cn/edu/banner/img/226156f4_yb_760_300.jpg","d://test/2222222222.jpg");
		
		String htmlsource = htmlSource("http://www.163.com","utf-8");
		System.out.println(htmlsource);
	}
}
