package demo.work.work8;

import org.redisson.api.RCountDownLatch;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TestCount {

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    StringRedisTemplate redisTemplate;


//    public void countDown(){
//        RCountDownLatch latch = redissonClient.getCountDownLatch("countDown");
//        latch.trySetCount(10);
//
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        for(int i=0; i<10; i++){
//
//            executorService.submit(() -> {
//                try {
//                    System.out.println(Thread.currentThread().getName());
//                    Thread.sleep(1*1000);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }finally {
//                    latch.countDown();
//                    System.out.println(latch.getCount());
//
//                }
//            });
//
//        }
//
//
//        try {
//            latch.await();
//            System.out.println("**************");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public void countDown(){

        redisTemplate.opsForValue().setIfAbsent("count","20");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(10);
        for(int i=0; i<10; i++){

            executorService.submit( () -> {
                try {
                    //模拟减库存
                    long remain = redisTemplate.opsForValue().increment("count", (long) -1);
                    System.out.println(remain);

                    if(remain < 0){
                        //库存不足的一些操作
                    }

                    Thread.sleep(1*1000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }

            });
        }

        try {
            latch.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("main end !");
    }
}
