package demo.work.work2.java.bean;



/**
 * @author xxl
 */
public class BeanA {

    private BeanB beanB;

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
