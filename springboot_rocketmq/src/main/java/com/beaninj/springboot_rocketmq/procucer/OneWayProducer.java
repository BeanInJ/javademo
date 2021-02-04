package com.beaninj.springboot_rocketmq.procucer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/*
    单向消息发送
 */
public class OneWayProducer {
    public static void main(String[] args) throws Exception {
        // 1.创建生产者对象，并指定组名“group1”
        DefaultMQProducer p = new DefaultMQProducer("group1");
        // 2.指定nameserver地址
//        p.setNamesrvAddr("192.168.1.11:9876;192.168.1.12:9876");
        p.setNamesrvAddr("127.0.0.1:9876");
        // 3.启动生产者
        p.start();
        // 4.创建消息对象，指定Topic\Tag\消息体
        for (int i = 0; i < 10; i++) {
            Message msg = new Message("base","Tag3",("oneway hi rocketmq "+i).getBytes());
            // 5。发送消息
            p.sendOneway(msg);

            TimeUnit.SECONDS.sleep(1);                      // 让线程停1秒
        }
        // 6.关闭生产者
        p.shutdown();
    }
}
