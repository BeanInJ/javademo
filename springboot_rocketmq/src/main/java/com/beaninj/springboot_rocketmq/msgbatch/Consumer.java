package com.beaninj.springboot_rocketmq.msgbatch;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

// 批量消息
public class Consumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer c = new DefaultMQPushConsumer("group1");
        c.setNamesrvAddr("192.168.1.11:9876;192.168.1.12:9876");
        c.subscribe("ms","*");
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
