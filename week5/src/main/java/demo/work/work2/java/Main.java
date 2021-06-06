package demo.work.work2.java;


import demo.work.work2.java.bean.BeanA;
import demo.work.work2.java.bean.BeanB;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * 使用Java方式装配
 * @author xxl
 */
@ComponentScan("demo.work.work2.java")
public class Main {

    public static void main(String[] args) {
        //使用注解方式
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册配置类
        applicationContext.register(Main.class);
        //启动
        applicationContext.refresh();

        BeanA beanA = (BeanA)applicationContext.getBean("beanA");

        beanA.method();
        beanA.invokeBeanB();
    }

    @Bean
    public BeanA beanA(BeanB beanB){
        return new BeanA(beanB);
    }

    @Bean
    public BeanB beanB(){
        return new BeanB();
    }
}
