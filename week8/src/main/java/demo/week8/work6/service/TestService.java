package demo.week8.work6.service;

import demo.week8.work6.mapper.TestMapper;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.RollbackException;
import java.util.HashMap;
import java.util.Map;

@Service
public class TestService {

    @Autowired
    TestMapper testMapper;

    @Transactional(rollbackFor = RollbackException.class)
    @ShardingTransactionType(TransactionType.XA)  // 支持TransactionType.LOCAL, TransactionType.XA, TransactionType.BASE
    public void insert() {

        testMapper.insert(1001, 2);

    }

    @Transactional(rollbackFor = RollbackException.class)
    @ShardingTransactionType(TransactionType.XA)
    public void insert2() {

        testMapper.insert(1002,2);

        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }

        //这里会抛异常,因为之前已经插入
        testMapper.insert(1001,2);

    }

    @Transactional(rollbackFor = RollbackException.class)
    @ShardingTransactionType(TransactionType.XA)
    public void insert3() {

        //ds1,table1
        testMapper.insert(1003,1);
        //ds0,table1
        testMapper.insert(1003,2);
        //ds1,table0
        testMapper.insert(1004,1);
        //ds0,table0
        testMapper.insert(1004,2);

        //结果正常

    }

    @Transactional(rollbackFor = RollbackException.class)
    @ShardingTransactionType(TransactionType.XA)
    public void insert4() {

        //ds1,table1
        testMapper.insert(1005,1);
        //ds0,table1
        testMapper.insert(1005,2);
        //ds1,table0
        testMapper.insert(1006,1);
        //ds0,table0
        testMapper.insert(1006,2);

        //抛异常
        int a = 1/0;

        //全部插入失败

    }
}
