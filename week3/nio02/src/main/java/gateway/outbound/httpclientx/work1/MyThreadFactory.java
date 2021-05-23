package gateway.outbound.httpclientx.work1;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程工厂
 * @author xxl
 */
public class MyThreadFactory implements ThreadFactory {
    private final String name_prefix = "my-thread-";
    private final AtomicInteger atomic = new AtomicInteger();

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, name_prefix + atomic.getAndIncrement());
        //设置为守护线程，主线程退出后，守护线程自动销毁
        t.setDaemon(true);
        return t;
    }
}
