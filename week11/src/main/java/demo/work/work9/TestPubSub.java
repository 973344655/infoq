package demo.work.work9;

import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestPubSub {

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    StringRedisTemplate redisTemplate;

    public void receive(){
        RTopic topic = redissonClient.getTopic("topic1");

        topic.addListener(String.class, new MessageListener<String>() {
            @Override
            public void onMessage(CharSequence charSequence, String s) {
                //处理业务逻辑
                System.out.println("收到消息: " + s);
            }
        });
    }

    public void send(){
        RTopic topic = redissonClient.getTopic("topic1");

        //发送业务信息
        topic.publish("msg1");
        topic.publish("msg2");
    }
}
