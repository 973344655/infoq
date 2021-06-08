package demo.work.work1.cglib;


import demo.work.work1.MyAop;

@MyAop
public class DemoProxy {

    public void did(){
        System.out.println("处理一些业务逻辑");
    }
}
