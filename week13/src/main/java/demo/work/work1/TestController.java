package demo.work.work1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {
    @Autowired KafkaSender kafkaSender;

    @RequestMapping("/test")
    @ResponseBody
    public Object test(String msg){

        kafkaSender.send(msg);
        return "ok";
    }
}
