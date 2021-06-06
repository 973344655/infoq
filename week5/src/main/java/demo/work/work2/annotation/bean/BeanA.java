package demo.work.work2.annotation.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * @author xxl
 */
@Component("beanA")
@Lazy
@Scope("prototype")
//@RequiredArgsConstructor
public class BeanA implements BeanNameAware,BeanFactoryAware {

    private String beanName;

    /**
     * 构造器注入
     */
    private final BeanB beanB;
    public BeanA(BeanB beanB){
        this.beanB = beanB;
    }

    /**
     * 属性注入
     */
    @Autowired
    private  BeanC beanC;

    /**
     * setter注入
     */
    private BeanD beanD;
    @Autowired
    public void setBeanD(BeanD beanD){
        this.beanD = beanD;
    }



    @PostConstruct
    public void initDid(){
        System.out.println("beanA init");
    }



    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        System.out.println("beanA setBeanName");
    }

    public String getBeanName(){
        return this.beanName ;
    }



    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {


        System.out.println("beanA setBeanFactory");
    }



    @PreDestroy
    public void destroyDid(){
        System.out.println("beanA destroy");
    }


    public void invokeBeanB(){
        beanB.method();
    }

    public void invokeBeanC(){
        beanC.method();
    }
    public void invokeBeanD(){
        beanD.method();
    }
}
