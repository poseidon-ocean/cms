package com.lt.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

/**
 * 提供日期转化，格式化及数据类型转化的方法。
 * 
 *
 */
public class Util {
	public static DecimalFormat dfThree = new DecimalFormat("0.000");
	public static DecimalFormat dfFour = new DecimalFormat("0.0000");
	public static Double formatDouble(Double val,DecimalFormat df){
		return Double.parseDouble(df.format(val));
	}
	/**
	 * 获取日间戳
	 * @return 格式为MMddHHmmss的时间戳
	 */
	public static String getTimeStamp(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssss");
		return sdf.format(Util.getCurrentTradeDate());
	}
	/**
	 * 把字符串根据指定的格式转成日期类型
	 * @param str		日期字符串
	 * @param format	格式化串
	 * @return			日期对象
	 */
	public static Date getDate(String str,String format){
		if(str==null||"".equals(str))
			return null;
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			System.out.println("解析时间出错!");
		}
		return null;
	}
	/**
	 * 获取当前天，时分秒为0
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date getCurDate(){
		Calendar cal=Util.getCurrentTradeCalendar();
		cal.setTime(Util.getCurrentTradeDate());
		cal.set(Calendar.HOUR_OF_DAY, 0);  
		cal.set(Calendar.MINUTE, 0);  
		cal.set(Calendar.SECOND, 0);  
		cal.set(Calendar.MILLISECOND, 0);  		
		return cal.getTime();
	}
	/**
	 * 把日期类型根据指定的格式转成字符串类型
	 * @param date		日期对象
	 * @param format	格式化串
	 * @return			格式化后的字符串
	 */
	public static String getDateStr(Date date,String format){
		if(date==null)
			return "";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.format(date);
	}
	/**
	 * 计算频率时间
	 * @param startDate	开始时间	
	 * @param frequency	周期
	 * @param addNum	调整周期数
	 * @return			调整后的日期
	 */
	public static Date getFrequencyDate(Date startDate,String frequency,int addNum){
		Calendar cal=Util.getCurrentTradeCalendar();
		cal.setTime(startDate);
		if("Y".equals(frequency)){
			cal.add(Calendar.YEAR, addNum*1);
		}else if("HY".equals(frequency)){
			cal.add(Calendar.MONTH, addNum*6);
		}else if("Q".equals(frequency)){
			cal.add(Calendar.MONTH, addNum*3);
		}else if("M".equals(frequency)){
			cal.add(Calendar.MONTH, addNum*1);
		}else if("W".equals(frequency)){			
			cal.add(Calendar.WEEK_OF_YEAR, addNum*1);			
		}else if("2W".equals(frequency)){			
			cal.add(Calendar.WEEK_OF_YEAR, addNum*2);			
		}else if("D".equals(frequency)){			
			cal.add(Calendar.DAY_OF_YEAR, addNum*1);			
		}else{
			cal.add(Calendar.DAY_OF_YEAR, addNum*1);
		}
		return cal.getTime();
	}
	/**
	 * 取CompoundAverageFlowEndDate,以CompoundAverageFrequency循环
	 * @param startDate		开始时间
	 * @param endDate		结束时间
	 * @param compoundFy	浮利周期
	 * @return				日期
	 */
	public static Date getCompoundAverageFlowEndDate(Date startDate,Date endDate,String compoundFy){
		Date compoundAverageFlowEndDate=null;
		while(startDate.getTime()<endDate.getTime()){
			compoundAverageFlowEndDate=getFrequencyDate(startDate, compoundFy, 1);
			if(compoundAverageFlowEndDate.getTime()>endDate.getTime()){
				//compoundAverageFlowEndDate=startDate;
				break;
			}
			startDate=compoundAverageFlowEndDate;
		}
		return compoundAverageFlowEndDate;
	}
	/**
	 * 倒着取CompoundAverageFlowEndDate,以CompoundAverageFrequency循环
	 * @param startDate		开始时间
	 * @param endDate		结束时间
	 * @param compoundFy	浮利周期
	 * @return				日期
	 */
	public static Date getCompoundAverageFlowStartDate(Date startDate,Date endDate,String compoundFy){
		Date compoundAverageFlowStartDate=null;
		while(startDate.getTime()<endDate.getTime()){
			compoundAverageFlowStartDate=getFrequencyDate(endDate, compoundFy, -1);
			if(compoundAverageFlowStartDate.getTime()<startDate.getTime()){
				compoundAverageFlowStartDate=endDate;
				break;
			}
			endDate=compoundAverageFlowStartDate;
		}
		return compoundAverageFlowStartDate;
	}
	/**
	 * 比较那个周期大
	 * @param fy1	周期1
	 * @param fy2	周期2
	 * @return		周期1比周期2大返回true,否则返回false
	 */
	public static int compireFrequency(String fy1,String fy2){
		int fyNum1=getFyNum(fy1);
		int fyNum2=getFyNum(fy2);
		return fyNum1-fyNum2;
	}
	/**
	 * 取日期所在周期内的开始日期
	 * @param date		日期
	 * @return			周期内的开始日期
	 */
	public static Date getStartDate(Date date,String p){		
		Calendar cal = Util.getCurrentTradeCalendar();
		cal.setTime(date);
		if("Y".equals(p)){
			cal.set(Calendar.DAY_OF_YEAR,cal.getActualMinimum(Calendar.DAY_OF_YEAR)); //年第一天
		}else if("M".equals(p)||"H".equals(p)||"M".equals(p)){
			cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH)); //月第一天
		}else if("W".equals(p)){
			cal.set(Calendar.DAY_OF_WEEK,cal.getActualMinimum(Calendar.DAY_OF_WEEK)+1);//周第一天
		}
		return cal.getTime();
	}  
	/**
	 * 取日期所在周期内的结束日期
	 * @param date 日期
	 * @return	   日期所在周期内的结束日期
	 */
	public static Date getEndDate(Date date,String p){
		Calendar cal = Util.getCurrentTradeCalendar();
		cal.setTime(date);
		if("D".equals(p)){
			return cal.getTime();
		}else if("W".equals(p)||"2W".equals(p)){
			cal.set(Calendar.DAY_OF_WEEK,cal.getActualMaximum(Calendar.DAY_OF_WEEK));//周最后一天
			cal.add(Calendar.DATE, 1);
		}else if("M".equals(p)||"Q".equals(p)||"HY".equals(p)){
			cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DAY_OF_MONTH)); //月最后一天
		}else if("Y".equals(p)){
			cal.set(Calendar.DAY_OF_YEAR,cal.getActualMaximum(Calendar.DAY_OF_YEAR)); //年最后一天
		}
		return cal.getTime();
	}
	/**
	 * 获取周期对应的数
	 * @param fy  周期
	 * @return	  对应基数
	 */
	public static int getFyNum(String fy){
		if("E".equals(fy))
			return 999999999;//测试
		else if("Y".equals(fy))
			return 365;
		else if("HY".equals(fy))
			return 183;
		else if("Q".equals(fy))
			return 90;
		else if("M".equals(fy))
			return 30;
		else if("2W".equals(fy))
			return 7;
		else if("W".equals(fy))
			return 14;
		else if("D".equals(fy))
			return 1;
		else
			return 1;
	}
	/**
	 * 获取日期leg后的日期
	 * @param date	日期
	 * @param lag	lag天数
	 * @return		lag后的日期
	 */
	public static Date getLagDate(Date date,int lag){
		if(date==null)
			return null;
		Calendar cal=Util.getCurrentTradeCalendar();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, lag);
		return cal.getTime();			
	}		
	/**
	 * 获取Basis所对应的年化天的基数
	 * @param basis	basis值
	 * @return		basis对应的基数
	 */
	public static int getBasisNum(String basis){
		if("M".equals(basis))
			return 360;
		else if("5".equals(basis)||"F".equals(basis))
			return 365;
		else if("C".equals(basis))
			return 360;
		else
			return getDaysBetween(getStartDate(Util.getCurrentTradeDate(),"Y"),getEndDate(Util.getCurrentTradeDate(),"Y"));
	}
	/**
	 * 计算两个时间相隔多少天
	 * @param startDate	开始日期
	 * @param endDate	结束日期
	 * @return			相隔天数
	 */
	public static int getDaysBetween(Date startDate, Date endDate) {  
        Calendar fromCalendar = Util.getCurrentTradeCalendar();  
        fromCalendar.setTime(startDate);  
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        fromCalendar.set(Calendar.MINUTE, 0);  
        fromCalendar.set(Calendar.SECOND, 0);  
        fromCalendar.set(Calendar.MILLISECOND, 0);  
  
        Calendar toCalendar = Util.getCurrentTradeCalendar();  
        toCalendar.setTime(endDate);  
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        toCalendar.set(Calendar.MINUTE, 0);  
        toCalendar.set(Calendar.SECOND, 0);  
        toCalendar.set(Calendar.MILLISECOND, 0);         
        long btNum=(toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);
        //System.out.println("btNum:"+btNum);
        return new Long(btNum).intValue();  
    }
	/**
	 * 计算两个时间相隔多少天 风控使用
	 * @param startDate	开始日期
	 * @param endDate	结束日期
	 * @return			相隔天数
	 */
	public static int getDaysBetween_rick(Date startDate, Date endDate) {  
        
        long btNum=(endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
        //System.out.println("btNum:"+btNum);
        return new Long(btNum).intValue();  
    }
	/**
	 * 计算两个30/360模式下时间相隔多少天
	 * @param startDate	开始日期
	 * @param endDate	结束日期
	 * @return			相隔天数
	 */
	public static int get30DaysBetween(Date startDate, Date endDate) {  
        Calendar fromCalendar = Util.getCurrentTradeCalendar();  
        fromCalendar.setTime(startDate);  
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        fromCalendar.set(Calendar.MINUTE, 0);  
        fromCalendar.set(Calendar.SECOND, 0);  
        fromCalendar.set(Calendar.MILLISECOND, 0);  
  
        Calendar toCalendar = Util.getCurrentTradeCalendar();  
        toCalendar.setTime(endDate);  
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        toCalendar.set(Calendar.MINUTE, 0);  
        toCalendar.set(Calendar.SECOND, 0);  
        toCalendar.set(Calendar.MILLISECOND, 0);         
        int btNum=360*(toCalendar.get(Calendar.YEAR)-fromCalendar.get(Calendar.YEAR))
        			+30*(toCalendar.get(Calendar.MONTH)-fromCalendar.get(Calendar.MONTH))
        			+(toCalendar.get(Calendar.DATE)-fromCalendar.get(Calendar.DATE));
        return btNum;  
    }
	/**
	 * 判断一个时间是否在前两个时间之间
	 * @param startDate	开始时间
	 * @param endDate	结束时间
	 * @param curDate	中间时间
	 * @return			中间时间在前两个之间返回true,否则返回失败
	 */
	public static boolean isInBetween(Date startDate,Date endDate,Date curDate){
		return curDate.getTime()>startDate.getTime()&&curDate.getTime()<=endDate.getTime();
	}
	 /**
	 * 判断当前日期是星期几
	 * @param date 日期
	 * @return     日期对象的星期数
	 */
	public static int getDayOfWeek(Date date) {
		Calendar c = Util.getCurrentTradeCalendar();
		c.setTime(date);
		int dayForWeek = c.get(Calendar.DAY_OF_WEEK);
		if (dayForWeek == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek += -1;
		}
		return dayForWeek;
	}
	/**
	 * 获取非""字符串的对象，只供ubsi传输BigDecimal Long型时的String
	 * @param str	字符串
	 * @return		字符串对象
	 */
	public static String getString(String str){
		if(str==null||"".equals(str))
			return null;
		return str;
	}
	/**
	 * 把各种对象转成String类型
	 * @param obj	泛化对象
	 * @return		返回字符串
	 */
	public static String getString(Object obj){
		if(obj==null)
			return null;
		if(obj instanceof BigDecimal)
			return ((BigDecimal)obj).toPlainString();
		else
			return obj.toString();
	}
	/**
	 * 字符串转成Long型
	 * @param str	字符串
	 * @return		Long对象
	 */
	public static Long getLong(String str){
		if(str==null||"".equals(str))
			return null;
		return Long.valueOf(str);
	}
	/**
	 * 字符串转成Integer
	 * @param str	字符串
	 * @return		Integer对象
	 */
	public static Integer getInteger(String str){
		if(str==null||"".equals(str))
			return null;
		return Integer.valueOf(str);
	}
	/**
	 * 字符串转成Double对象
	 * @param str	字符串
	 * @return		Double对象
	 */
	public static Double getDouble(String str){
		if(str==null||"".equals(str))
			return new Double(0);
		return new Double(str);
	}
	public static Double getDouble(Object obj){
		if(obj==null)
			return 0d;
		else{
			return Double.parseDouble(obj.toString());
		}
	}
	/**
	 * 字符串转成Float对象
	 * @param str	字符串
	 * @return		Float对象
	 */
	public static Float getFloat(String str){
		if(str==null||"".equals(str))
			return new Float(0);
		return new Float(str);
	}
	/**
	 * 转换数据库的Timestamp时间为Date
	 * @param date	sql日期
	 * @return		util日期
	 */
	public static Date getDate(Object date){
		if(date==null||!(date instanceof Date))
			return null;
		return new Date(((Date)date).getTime());
	}
	/**
	 * 字符串转成BigDecimal对象
	 * @param str	字符串
	 * @return		BigDecimal对象
	 */
	public static BigDecimal getBigDecimal(String str){
		if(str==null||"".equals(str))
			return new BigDecimal(0); 
		return new BigDecimal(str);
	}
	public static BigDecimal getBigDecimal(Object obj){
		if(obj==null)
			return new BigDecimal(0); 
		return new BigDecimal(obj.toString());
	}
	
	/**
     * 对double数据进行取精度.
     * @param value 		double数据.
     * @param scale 		精度位数(保留的小数位数).
     * @param roundingMode  精度取值方式.
     * @return 				精度计算后的数据.
     */
    public static double round(double value, int scale, int roundingMode) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(scale, roundingMode);
        double d = bd.doubleValue();
        bd = null;
        return d;
    }
    public static String numberFormat(Double num,String format){
    	if(num==null)
    		num=0d;
    	DecimalFormat df=new DecimalFormat(format); 
    	return df.format(num);
    }
    /**
     * 获取日期所在的月份
     * @param date	日期
     * @return		月份
     */
    public static int getMonthOfYear(Date date){
    	Calendar cal=Util.getCurrentTradeCalendar();
		cal.setTime(date);
		return cal.get(Calendar.MONTH)+1;
    }
	/**
	 * 返回指定日期的年份（四位）
	 * 
	 * @return int
	 */
	public static int getYear(Date date) {
		Calendar actualDate = Util.getCurrentTradeCalendar();
		actualDate.setTime(date);
		return actualDate.get(Calendar.YEAR);
	}

	/**
	 * 返回指定日期的月份（两位）
	 * 
	 * @return int
	 */
	public static int getMonth(Date date) {
		Calendar actualMonth = Util.getCurrentTradeCalendar();
		actualMonth.setTime(date);
		return actualMonth.get(Calendar.MONTH) + 1;
	}

	/**
	 * 返回指定日期的天（两位）
	 * 
	 * @return int
	 */
	public static int getDay(Date date) {
		Calendar actualMonth = Util.getCurrentTradeCalendar();
		actualMonth.setTime(date);
		return actualMonth.get(Calendar.DAY_OF_MONTH);
	}    
	/**
	 * 后面的step个月
	 */
	public static Date nextStepMonth(Date date,int step) {
		Calendar temp = Util.getCurrentTradeCalendar();
		temp.setTimeInMillis(date.getTime());
		temp.add(Calendar.MONTH, step);
		return new Date(temp.getTimeInMillis());
	}
	/**
	 * 后面的step个天
	 */
	public static Date nextStepDay(Date date,int step) {
		Calendar temp = Util.getCurrentTradeCalendar();
		temp.setTimeInMillis(date.getTime());
		temp.add(Calendar.DATE, step);
		return new Date(temp.getTimeInMillis());
	}
	/**
	 * 后面的step个年
	 */
	public static Date nextStepYear(Date date,int step) {
		Calendar temp = Util.getCurrentTradeCalendar();
		temp.setTimeInMillis(date.getTime());
		temp.add(Calendar.YEAR, step);
		return new Date(temp.getTimeInMillis());
	}	
    /**
     * 判断字符串是否为空
     * @param str	字符串
     * @return		为空返回true,否则返回flase
     */
    public static boolean isEmpty(String str){
    	if(str==null||"".equals(str))
    		return true;
    	return false;
    }
    /**
     * 判断字符串是否为空
     * @param str	字符串
     * @return		为空返回true,否则返回flase
     */
    public static boolean isEmpty(Object str){
    	if(str==null||"".equals((String)str))
    		return true;
    	return false;
    }
    /**
     * 集合数据转整型数组
     * @param intObjs	集合
     * @return			整型数组
     */
    public static int[] getInts(TreeSet<Integer> intObjs){
    	int[] ints=new int[intObjs.size()];
    	int i=0;
    	for(Integer num:intObjs){
    		ints[i]=num;
    		i++;
    	}
    	return ints;
    }
    /**
	 * 计算两个日期相关多少年
	 * @param date1	日期1
	 * @param date2	日期2
	 * @return		相关年数
	 * @throws ParseException
	 */
	public static double getYearSpace(Date date1, Date date2){
		int result = 0;
		Calendar c1 = Util.getCurrentTradeCalendar();
		Calendar c2 = Util.getCurrentTradeCalendar();
		c1.setTime(date1);
		c2.setTime(date2);
		if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
			result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		} else {
			result = 12 * (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR))
					+ c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		}
		return Math.abs(result/12d);
	}
	/**
	 * 获取6位的字符串，如果不够六位前面加0补齐
	 * @param str
	 * @return
	 */
	public static String getSixStr(String str){		
		for(int i=str.length();i<6;i++){
			str="0"+str;
		}
		return str;
	}
	/**
	 * 取两个日期的年的倍数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static String getScop(Date startDate,Date endDate){
		int days=Util.getDaysBetween(startDate, endDate);
		double years=days/365d;
		Double yd=Util.round(years, 2, BigDecimal.ROUND_HALF_UP);
		return yd.toString();
	}
	
	/**
	 * 获取当前营业日 日期
	 * @return
	 */
	public static Date getCurrentTradeDate(){
		
		return new Date();
	}
	
	/**
	 * 
	 * 业  务: 根据生日获取年龄 <br>
	 * 时  间: 2015-5-7 下午02:48:43
	 * 作者: hjt
	 *
	 * @param strDate
	 * @return
	 */
	public static String getAge(String strDate){
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyyMMdd");
		Date mydate = null;
		try {
			mydate = myFormatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date date = new Date();		
		Long day = date.getTime() - mydate.getTime();
		day = (day/(24*60*60*1000) + 1)/365;
		
		return day.toString();
		
	}
	
    public static void main(String[] args) {
    	
	}
    
    public static Calendar getCurrentTradeCalendar(){
		Calendar c = Calendar.getInstance();
		c.setTime(getCurrentTradeDate());
		return c;
	}

}
