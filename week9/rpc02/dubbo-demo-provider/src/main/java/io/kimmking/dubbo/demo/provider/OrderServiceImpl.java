package io.kimmking.dubbo.demo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import io.kimmking.dubbo.demo.api.Order;
import io.kimmking.dubbo.demo.api.OrderService;
import io.kimmking.dubbo.demo.provider.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.dromara.hmily.common.exception.HmilyRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@DubboService(version = "2.0.1", tag = "red", weight = 100)
//@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    AccountMapper accountMapper;

    @Override
    @Transactional
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public Boolean rmb2Dollar(int id, int amount){
        int count =  accountMapper.rmb2Dollar(id, amount);
        if (count > 0) {
            return true;
        } else {
            throw new HmilyRuntimeException("账户扣减异常！");
        }
    }
    @Override
    @Transactional
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public Boolean rmb2Dollar2(int id, int amount){
        int count =  accountMapper.rmb2Dollar(id, amount);
        try {
            Thread.sleep(1000*5);
        }catch (Exception e){
            e.printStackTrace();
        }
        //抛异常
        int a = 1/0;

        if (count > 0) {
            return true;
        } else {
            throw new HmilyRuntimeException("账户扣减异常！");
        }
    }

    @Transactional
    public Boolean confirm(int id, int amount){
        log.info("============dubbo tcc 执行确认付款接口===============");
        accountMapper.confirm(id, amount);
        log.info("调用了account confirm ");
        return Boolean.TRUE;
    }

    @Transactional
    public Boolean cancel(int id, int amount){
        log.info("============ dubbo tcc 执行取消付款接口===============");
        accountMapper.cancel(id, amount);
        return Boolean.TRUE;
    }

    @Override
    @Transactional
    @HmilyTCC(confirmMethod = "confirm1", cancelMethod = "cancel1")
    public Boolean dollar2Rmb(int id, int amount){
        int count =  accountMapper.dollar2Rmb(id, amount);
        if (count > 0) {
            return true;
        } else {
            throw new HmilyRuntimeException("账户扣减异常！");
        }
    }

    @Transactional
    public Boolean confirm1(int id, int amount){
        log.info("============dubbo tcc 执行确认付款接口1===============");
        accountMapper.confirm1(id, amount);
        log.info("调用了account confirm1 ");
        return Boolean.TRUE;
    }

    @Transactional
    public Boolean cancel1(int id, int amount){
        log.info("============ dubbo tcc 执行取消付款接口1===============");
        accountMapper.cancel1(id, amount);
        return Boolean.TRUE;
    }

    @Override
    public Order findOrderById(int id) {
        return new Order(id, "Cuijing" + System.currentTimeMillis(), 9.9f);
    }
}
