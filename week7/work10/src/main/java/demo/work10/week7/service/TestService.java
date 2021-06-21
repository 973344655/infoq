package demo.work10.week7.service;

import demo.work10.week7.mapper.TestMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    TestMapper testMapper;

    public void shardingSphere(){
        testMapper.insert();

        System.out.println(testMapper.getOrders().size());
    }
}
