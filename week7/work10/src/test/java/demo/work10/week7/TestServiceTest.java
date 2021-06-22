package demo.work10.week7;



import demo.Application;
import demo.work10.week7.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@EnableAutoConfiguration
public class TestServiceTest {

    @Autowired
    TestService testService;

    @Test
    public void test(){
        testService.shardingSphere();
    }




}
