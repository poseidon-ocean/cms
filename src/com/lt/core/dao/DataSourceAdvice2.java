package com.lt.core.dao;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


@Aspect
public class DataSourceAdvice2{
	// service方法执行之前被调用
	@Before("execution(* com.tz.service..*Service.*(..))")
	public void before() {
		System.out.println("----------------------dddddd");
	}

}

