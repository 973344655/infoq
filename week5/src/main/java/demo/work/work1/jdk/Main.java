package demo.work.work1.jdk;

import java.lang.reflect.Proxy;

/**
 * @author xxl
 */
public class Main {

    public static void main(String[] args) {

        //生成代理对象
        MyProxy proxy = (MyProxy) Proxy.newProxyInstance(MyProxy.class.getClassLoader(),
                new Class[]{MyProxy.class},
                new MyInvocationHandler(new DemoProxy()));

        System.out.println(proxy.did1());

    }
}
