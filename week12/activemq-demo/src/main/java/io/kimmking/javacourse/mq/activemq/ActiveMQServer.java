package io.kimmking.javacourse.mq.activemq;


import org.apache.activemq.broker.BrokerService;

public class ActiveMQServer {

    public static void main(String[] args) throws Exception{
        // 尝试用java代码启动一个ActiveMQ broker server
        // 然后用前面的测试demo代码，连接这个嵌入式的server
        //新建broker服务对象
        BrokerService brokerService = new BrokerService();
        //设置名称，多个broker的时候采用
        brokerService.setBrokerName("activemq");
        brokerService.setUseJmx(true);
        //设置持久化
        brokerService.setPassiveSlave(false);
        //添加链接
        brokerService.addConnector("tcp://127.0.0.1:61616");
        //启动服务
        brokerService.start();
    }
}
