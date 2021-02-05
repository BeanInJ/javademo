package com.beaninj.springboot_rocketmq.msgQueue;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

// 顺序消息
public class Consumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer c = new DefaultMQPushConsumer("group1");
        c.setNamesrvAddr("192.168.1.11:9876;192.168.1.12:9876");
        c.subscribe("orderTopic","*");
        // 注册监听器
        c.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt messageExt : list) {
                    System.out.println("线程名称："+Thread.currentThread().getName());
                    System.out.println("消息内容："+new String(messageExt.getBody()));

                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        c.start();
    }
}
