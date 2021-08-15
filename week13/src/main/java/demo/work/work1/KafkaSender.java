package demo.work.work1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaSender {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String msg) {

        log.info("【++++++++++++++++++ message ：{}】", msg);
        //对 topic =  TestComposeTopic 的发送消息
        kafkaTemplate.send("TestComposeTopic",msg);
    }

}