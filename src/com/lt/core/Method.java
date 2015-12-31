package com.lt.core;

import com.lt.core.interceptor.LtRequestMethod;


@java.lang.annotation.Documented
@java.lang.annotation.Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target(value={java.lang.annotation.ElementType.METHOD})
public @interface Method {
	public String value() default "";
	public abstract LtRequestMethod[] method() default {};
}
