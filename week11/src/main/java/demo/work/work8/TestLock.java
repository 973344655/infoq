package demo.work.work8;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestLock {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedissonClient redissonClient;




    public String save(){
        stringRedisTemplate.opsForValue().set("key","redisson");
        return "save ok";
    }

    public String get(){
        return stringRedisTemplate.opsForValue().get("key");
    }

    public void testLock(){
        System.out.println("***********lock start*******************");
        RLock lock = redissonClient.getLock("lock1");
        lock.lock();
        try {
            System.out.println("***********lock*******************");

            System.out.println(Thread.currentThread().getName());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println("***********unlock*******************");
        }
    }

    public void testLock1(){
        System.out.println("***********lock1 start*******************");

        RLock lock = redissonClient.getLock("lock1");
        lock.lock();
        try {
            System.out.println("***********lock1*******************");
            System.out.println(Thread.currentThread().getName());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println("***********unlock1*******************");
        }
    }
}
