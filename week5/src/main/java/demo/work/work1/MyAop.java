package demo.work.work1;

import java.lang.annotation.*;

/**
 * 注解，用于动态代理demo
 * 作用在方法上
 * @author xxl
 */
//让子类继承这个注解
@Inherited
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAop {

    String value() default "";

}
