package demo.work.work2.xml.bean;

/**
 *
 * @author xxl
 */
public class BeanA {

    private BeanB beanB;

    //使用构造器注入方式
    public BeanA(BeanB beanB){
        this.beanB = beanB;
    }
    public void method(){
        System.out.println("beanA method");
    }

    public void invokeBeanB(){
        beanB.method();
    };


}
