package com.github.maodua.wrench.pager.annotation;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Pager {
    int page() default 1;
    int pageSize() default 10;
    int minSize() default 1;
    int maxSize() default 500;

}
