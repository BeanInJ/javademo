package com.beaninj.springboot_rocketmq.msgDelay;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

// 延迟5秒发送消息
public class Producer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer p = new DefaultMQProducer("group1");
        p.setNamesrvAddr("192.168.1.11:9876;192.168.1.12:9876");
        p.start();

        for (int i = 0; i < 10; i++) {
            Message msg = new Message("base","Tag1",("hi rocketmq "+i).getBytes());
            // 延迟五秒发送
            msg.setDelayTimeLevel(2);
            SendResult sr = p.send(msg);
            String msgId = sr.getMsgId();                      // 获取消息id
            SendStatus msgStatus = sr.getSendStatus();         // 获取消息状态
            int queueId = sr.getMessageQueue().getQueueId();   // 获取queueId

            System.out.println("消息id:"+msgId+"。 消息状态："+msgStatus+"。 队列id"+queueId);
            TimeUnit.SECONDS.sleep(1);                      // 让线程停1秒

        }
        p.shutdown();
    }
}
