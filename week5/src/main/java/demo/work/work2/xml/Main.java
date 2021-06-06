package demo.work.work2.xml;

import demo.work.work2.xml.bean.BeanA;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 使用xml方式装配
 */
public class Main {

    public static void main(String[] args) {
        //使用xml方式
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:Spring.xml");


        BeanA beanA = (BeanA) applicationContext.getBean("beanA");

        beanA.method();
        beanA.invokeBeanB();
    }
}
