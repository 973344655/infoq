package demo.work.work10.hikari;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DemoService implements CommandLineRunner {

    @Autowired DemoMapper demoMapper;

    @Override
    public void run(String... args) throws Exception {
        Map<String,Object> map = demoMapper.getUserById("1");

        for(Map.Entry entry: map.entrySet()){
            System.out.println(entry.getValue());
        }
    }
}
