package com.beaninj.springboot_rocketmq.msgDelay;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

// 延时消息，消费者
public class Consumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer c = new DefaultMQPushConsumer("group1");
        c.setNamesrvAddr("192.168.1.11:9876;192.168.1.12:9876");
        c.subscribe("base","*");
        // 注册监听器
        c.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list,
                                                            ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt messageExt : list) {
                    // 打印延迟的时间
                    System.out.println("消息id："+messageExt.getMsgId()+ "。"+(System.currentTimeMillis()-messageExt.getStoreTimestamp())+"ms 后收到消息");

                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        } );
        c.start();
    }

}
