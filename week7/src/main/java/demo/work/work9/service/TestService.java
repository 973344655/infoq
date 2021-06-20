package demo.work.work9.service;

import demo.work.work9.mapper.db1.TestMapper1;
import demo.work.work9.mapper.db2.TestMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    TestMapper1 testMapper1;

    @Autowired
    TestMapper2 testMapper2;


    public void test(){

        System.out.println(testMapper1.getOrders().size());

        System.out.println(testMapper2.getOrders().size());
    }



}
