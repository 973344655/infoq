package demo.work.week7.service;

import demo.work.week7.annotation.DataSource;
import demo.work.week7.config.DatabaseContextHolder;
import demo.work.week7.mapper.TestMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    TestMapper testMapper;

    public void test(){

        DatabaseContextHolder.setDbType("db2");
        DatabaseContextHolder.clearDbType();
    }

    @DataSource(value = "db2")
    public void userAnnotaion(){
        System.out.println(testMapper.getOrders().size());
    }
}
