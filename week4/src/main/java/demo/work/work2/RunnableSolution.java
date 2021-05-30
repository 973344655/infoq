package demo.work.work2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * 使用Runnable对静态变量赋值的方式
 * @author xxl
 */
public class CallableSolution {

    private static volatile int result = 0;

    private Object object = new Object();


    public static void main(String[] args) throws Exception{
        long start=System.currentTimeMillis();
        new CallableSolution().method5();
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

    }

    /**
     * 使用join
     * @throws Exception
     */
    private void method0() throws Exception{
        Thread thread = new Thread(() -> { result = Fibonacci.sum();});
        thread.start();
        //等待子线程执行完
        thread.join();

        System.out.println(result);
    }

    /**
     * 主线程直接sleep
     * @throws Exception
     */
    private void method1() throws Exception{
        Thread thread = new Thread(() -> { result = Fibonacci.sum();});
        thread.start();
        //等待子线程执行完
        Thread.sleep(1000);

        System.out.println(result);
    }

    /**
     * 使用wait/notify
     * @throws Exception
     */
    private void method2() throws Exception{
        Thread thread = new Thread(() -> {
            result = Fibonacci.sum();
            synchronized (object){
                object.notifyAll();
            }
        });

        thread.start();

        //等待子线程执行完
        synchronized (object){
            object.wait();
        }

        System.out.println(result);
    }

    /**
     * 使用CountDownLatch阻塞主线程
     * @throws Exception
     */
    private void method3() throws Exception{
        CountDownLatch latch = new CountDownLatch(1);
        Thread thread = new Thread(() -> {
            result = Fibonacci.sum();
            //执行完
            latch.countDown();
        });
        thread.start();
        //挂起主线程
        latch.await();

        System.out.println(result);
    }

    /**
     * 使用CyclicBarrier等待子线程执行完
     * @throws Exception
     */
    private void method4() throws Exception{
        CyclicBarrier barrier = new CyclicBarrier(1,
                //回调
                () -> System.out.println(result));

        Thread thread = new Thread(() -> {
            try {
                result = Fibonacci.sum();
                //挂起当前线程
                barrier.await();

            }catch (Exception e){
                e.printStackTrace();
            }

        });
        thread.start();


    }

    /**
     * 使用Semaphore阻塞
     * @throws Exception
     */
    private void method5() throws Exception{

        Semaphore semaphore = new Semaphore(3);

        Thread thread = new Thread(() -> {
            try {
                semaphore.acquire(1);
                result = Fibonacci.sum();
                //释放资源，使阻塞的主线程能继续往下执行
                semaphore.release(2);

            }catch (Exception e){
                e.printStackTrace();
            }

        });
        thread.start();
        semaphore.acquire(1);
        //获取不到3个，这里阻塞，等待子线程释放资源
        semaphore.acquire(3);
        System.out.println(result);
        semaphore.release(3);
    }
}
