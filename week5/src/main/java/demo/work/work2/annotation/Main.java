package demo.work.work2.annotation;

import demo.work.work2.annotation.bean.BeanA;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 使用注解方式装配
 */
@ComponentScan("demo.work.work2.annotation.bean")
public class Main {
    public static void main(String[] args) {
        //使用注解方式
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册配置类
        applicationContext.register(Main.class);
        //启动
        applicationContext.refresh();

        BeanA beanA = (BeanA)applicationContext.getBean("beanA");

        System.out.println(beanA.getBeanName());

        beanA.invokeBeanB();

        beanA.invokeBeanC();

        beanA.invokeBeanD();

    }
}
