package demo.work;

import demo.work.work1.KafkaSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class KafkaTestController {

    @Autowired
    KafkaSender kafkaSender;

    @Test
    public void testSend(){
        kafkaSender.send("1234");
    }
}
