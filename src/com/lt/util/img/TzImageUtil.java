package com.lt.util.img;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.lt.util.StringUtils;


public class TzImageUtil {

	
	/*
	 * 百度搜索一下：
	 * java 图片处理 裁剪，水印
	 * java 图片 等比例缩放
	 * 
	 * 图片处理相关知识点：
	 * 1:裁剪
	 * 2：图片变灰色(黑白照片)
	 * 3:等比例缩放
	 * 4:文字水印
	 * 5:图片水印
	 * 6:压缩图片
	 * */
	
	public static void pressText(String text,String imageSrc,String targetPath,
			int fontSize,
			String font,
			Color color,
			int x,int y,int arrow){
		pressText(text,imageSrc,targetPath,new Font(font,Font.PLAIN,fontSize),color,x,y,arrow);
	}
	
	/**
	 * 
	 * @param text
	 * @param imageSrc
	 * @param targetPath
	 * @param font
	 * @param color
	 * @param x
	 * @param y 
	 * @arrow 
	 * 	arrow==1右下角
		arrow==2右上角
		arrow==3左上角
		arrow==4左下角
		arrow==5左下中心
	 * 返回类型：void
	 * @exception 
	 * @since  1.0.0
	 */
	public static void pressText(String text,String imageSrc,String targetPath,
			Font font,
			Color color,
			int x,int y,int arrow){
		try {
			//图片文件
			File file = new File(imageSrc);
			//图片处理类
			Image image = ImageIO.read(file);
			//获取图片的宽度和高度
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			//建立图片的缓冲流
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			//拿到图片处理的画笔
			Graphics graphics = bufferedImage.getGraphics();
			//设定绘制文字的区域
			graphics.drawImage(image, 0,0,width,height,null);
			//设置文字大小，样式，颜色
			graphics.setColor(color);//黑色
			graphics.setFont(font);
			//绘制文字
			if(arrow==1)graphics.drawString(text, width -x- font.getSize()*text.length(), height-font.getSize()-y);//右下角
			if(arrow==2)graphics.drawString(text, width -x- font.getSize()*text.length(), font.getSize()+y);//右上角
			if(arrow==3)graphics.drawString(text, x, font.getSize()+y);//左上角
			if(arrow==4)graphics.drawString(text, x, height - font.getSize()-y);//左下角
			if(arrow==5)graphics.drawString(text, (width-font.getSize()*text.length())/2, height - font.getSize()-y);//左下中心			
			graphics.dispose();
			//输出图片--图片另存在到哪里去
			FileOutputStream out = new FileOutputStream(targetPath);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(bufferedImage);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	
	/**
	 * 根据图片地址，获取图片的宽度和高度
	 * @param imgPath
	 * @return 
	 * 返回类型：HashMap<String,Object>
	 * @exception 
	 * @since  1.0.0
	 */
	public static HashMap<String, Object> getImageWH(String imgPath){
		try {
			//图片文件
			File file = new File(imgPath);
			//图片处理类
			Image image = ImageIO.read(file);
			//获取图片的宽度和高度
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			HashMap<String, Object> map = new HashMap<String,Object>();
			map.put("width",width);
			map.put("height",height);
			map.put("size", file.length());
			map.put("name", file.getName());
			map.put("sizeString", StringUtils.countFileSize(file.length()));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
     * 创建图片缩略图(等比缩放)
     * @param src源图片文件完整路径
     * @param dist 目标图片文件完整路径
     * @param width 缩放的宽度
     * @param height 缩放的高度
     */
    public static void createThumbnail(String src, String dist, float width, float height) {
        try {
            File srcfile = new File(src);
            if (!srcfile.exists()) return;
            BufferedImage image = ImageIO.read(srcfile);
            // 获得缩放的比例
            double ratio = 1.0;
            // 判断如果高、宽都不大于设定值，则不处理
            if (image.getHeight() > height || image.getWidth() > width) {
                if (image.getHeight() > image.getWidth()) {
                    ratio = height / image.getHeight();
                } else {
                    ratio = width / image.getWidth();
                }
            }
            
            // 计算新的图面宽度和高度
            int newWidth = (int) (image.getWidth() * ratio);
            int newHeight = (int) (image.getHeight() * ratio);

            BufferedImage bfImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            bfImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0,
                    null);

            FileOutputStream os = new FileOutputStream(dist);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
            encoder.encode(bfImage);
            os.close();
        } catch (Exception e) {
            System.out.println("创建缩略图发生异常" + e.getMessage());
        }
    }
    
    /**
     * 创建图片缩略图(等比缩放)
     * @param src源图片文件完整路径
     * @param dist 目标图片文件完整路径
     * @param width 缩放的宽度
     * @param height 缩放的高度
     */
    public static void createThumbnailNo(String src, String dist, int width, int height) {
        try {
            File srcfile = new File(src);
            if (!srcfile.exists()) return;
            BufferedImage image = ImageIO.read(srcfile);
            BufferedImage bfImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bfImage.getGraphics().drawImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0,null);

            FileOutputStream os = new FileOutputStream(dist);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
            encoder.encode(bfImage);
            os.close();
        } catch (Exception e) {
            System.out.println("创建缩略图发生异常" + e.getMessage());
        }
    }
    
    /**
     * 
     * @param filePath
     * @param suffix
     * @return 
     * 返回类型：String
     * @exception 
     * @since  1.0.0
     */
    public static String getNewName(String filePath,String suffix){
    	if(StringUtils.isNotEmpty(filePath)){
    		filePath= filePath.replace(".", suffix+".");
    		return filePath;
    	}else{
    		return "";
    	}
    }
	
	
	
	public static void main(String[] args) {
		
		System.out.println("===start====");
		createThumbnailNo("D:/img/043.jpg","D:/img/1/043.jpg",200,150);
		System.out.println("====end======");
//		pressText("我是潭州学院keke老师!",
//			"D:/KeKe画布学习-Canvas/NodeJs/a.jpg",
//			"D:/KeKe画布学习-Canvas/NodeJs/aaaaa.jpg",
//			24,
//			"微软雅黑",
//			Color.red,
//			10, 
//			10,1);
		
		//作业其他四个方向和图片水印和图片裁剪
		
//		System.out.println(getImageWH("D:/KeKe画布学习-Canvas/NodeJs/a.jpg"));
//		System.out.println(getImageWH("D:/KeKe画布学习-Canvas/NodeJs/b.jpg"));
//		createThumbnail("D:/KeKe画布学习-Canvas/NodeJs/a.jpg","D:/KeKe画布学习-Canvas/NodeJs/cccc.jpg",480,320);
		
		//System.out.println(getNewName("d:/bb/cc/aaaa.jpg","_120x120"));
		//System.out.println(getNewName("d:/bb/cc/aaaa.jpg","_60x60"));
		//System.out.println(getNewName("d:/bb/cc/aaaa.jpg","_48x48"));
		
		
		//Nodejs---它可以直接在js中连接mysql或者mongodb这样数据库

		//如果安装和使用node.js
		//运行机制，如何通过Nodejs搭建一个web服务器
		//怎么通过页面去请求Nodejs中的函数
		//如果达到：get /post 怎么传递参数,有怎么返回参数 url地址--
		//解决静态资源访问的问题
		//连接数据库 (mongodb)
		
		
		
		
		
		
		//backone.js
		//d3.js 制图，统计图， 
		//canvas 有关系
		
	}
	
	
	
	
}
