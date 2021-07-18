package demo.work.work8;

import demo.work.work9.TestPubSub;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRedis {

    @Autowired
    TestLock testLock;
    @Autowired
    TestCount testCount;
    @Autowired
    TestPubSub testPubSub;

    @org.junit.Test
    public void test1(){
        testLock.save();
        testLock.get();
    }

    @org.junit.Test
    public void test2(){

        Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {
                testLock.testLock();
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                testLock.testLock1();
            }
        });

        thread0.start();
        thread1.start();

        try {
            thread0.join();
            thread1.join();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Test
    public void test3(){

        testCount.countDown();
    }

    @Test
    public void test4(){
        testPubSub.receive();

        testPubSub.send();
    }
}
