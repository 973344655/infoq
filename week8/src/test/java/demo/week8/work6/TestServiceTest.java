package demo.week8.work6;

import demo.Application;
import demo.week8.work6.service.TestService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@EnableAutoConfiguration
public class    TestServiceTest {

    @Autowired
    TestService testService;

    @Test
    public void testInsert(){
       testService.insert();
    }

    @Test
    public void testInsert2(){
        testService.insert2();
    }

    @Test
    public void testInsert3(){
        testService.insert3();
    }

    @Test
    public void testInsert4(){
        testService.insert4();
    }


}
