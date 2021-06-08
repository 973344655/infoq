package demo.work.work1.jdk;

import demo.work.work1.MyAop;

/**
 * @author xxl
 */
public class DemoProxy implements MyProxy{

    @MyAop
    @Override
    public void did() {
        System.out.println("处理一些业务逻辑");
    }

    @MyAop(" -- 被 aop 修改")
    @Override
    public String did1() {
        return ("处理一些业务逻辑");
    }
}
