package com.lt.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD,ElementType.TYPE})
public @interface LtLog {
	public String model() default "";//模块
	public String name() default "";//模块的名称
	public String desc() default "";//模块的描述
	public String author() default "";//作者
	public String time() default "";//创建时间
}
