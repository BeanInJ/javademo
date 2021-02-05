package com.beaninj.springboot_rocketmq.msgbatch;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

// 批量消息,生产者
public class Producer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer p = new DefaultMQProducer("group1");
        p.setNamesrvAddr("192.168.1.11:9876;192.168.1.12:9876");
        p.start();

        ArrayList<Message> msgs = new ArrayList<>();

        Message msg1 = new Message("ms","Tag1",("hi rocketmq "+1).getBytes());
        Message msg2 = new Message("ms","Tag1",("hi rocketmq "+2).getBytes());
        Message msg3 = new Message("ms","Tag1",("hi rocketmq "+3).getBytes());

        msgs.add(msg1);
        msgs.add(msg2);
        msgs.add(msg3);

        SendResult sr = p.send(msgs);
        String msgId = sr.getMsgId();                      // 获取消息id
        SendStatus msgStatus = sr.getSendStatus();         // 获取消息状态
        int queueId = sr.getMessageQueue().getQueueId();   // 获取queueId
        System.out.println("消息id:"+msgId+"。 消息状态："+msgStatus+"。 队列id"+queueId);
        TimeUnit.SECONDS.sleep(1);                      // 让线程停1秒

        p.shutdown();
    }
}
