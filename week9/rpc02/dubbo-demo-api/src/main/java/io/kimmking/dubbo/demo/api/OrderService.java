package io.kimmking.dubbo.demo.api;

public interface OrderService {

    Order findOrderById(int id);

    Boolean rmb2Dollar(int id, int amount);
    Boolean rmb2Dollar2(int id, int amount);

    Boolean dollar2Rmb(int id, int amount);

}
