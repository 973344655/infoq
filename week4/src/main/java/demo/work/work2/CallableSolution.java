package demo.work.work2;

import java.util.concurrent.*;

/**
 * 使用Callable得到返回值
 * @author xxl
 */
public class RunnableSolution {

    public static void main(String[] args) throws Exception {
        long start=System.currentTimeMillis();
        new RunnableSolution().method2();
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    /**
     * 直接实现Callable
     * @throws Exception
     */
    private void method0() throws Exception{
        //直接运行callable
        Callable callable = () -> {return Fibonacci.sum();};
        System.out.println(callable.call());
    }

    /**
     * 使用FutureTask
     * @throws Exception
     */
    private void method1() throws Exception{
        FutureTask task = new FutureTask(()->Fibonacci.sum());
        Thread thread = new Thread(task);
        thread.start();
        System.out.println(task.get());
    }

    /**
     * 使用Future获取返回值
     * @throws Exception
     */
    private void method2() throws Exception{

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future future = executorService.submit(()->{
            return Fibonacci.sum();
        });
        executorService.shutdown();

        System.out.println(future.get());
    }
}
