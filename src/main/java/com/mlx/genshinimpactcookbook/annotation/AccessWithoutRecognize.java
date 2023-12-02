package com.mlx.genshinimpactcookbook.annotation;

import java.lang.annotation.*;

/**
 * 自定义允许不带token访问controller注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessWithoutRecognize {
}
