package demo.work.work1.cglib;



public class Main {
    public static void main(String[] args) {

        DemoProxy proxy = new CglibProxy<DemoProxy>().getProxy(new DemoProxy());
        proxy.did();

    }
}
