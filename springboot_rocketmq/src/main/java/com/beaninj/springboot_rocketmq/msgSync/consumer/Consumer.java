package com.beaninj.springboot_rocketmq.msgSync.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/*
      接收消息
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        // 1、创建消费者Consumer，制定消费者组名
        DefaultMQPushConsumer c = new DefaultMQPushConsumer("group1");
        // 2、指定nameserver地址
        // c.setNamesrvAddr("192.168.1.11:9876;192.168.1.12:9876");
        c.setNamesrvAddr("127.0.0.1:9876");
        // 3、订阅主题Topic和Tag
        c.subscribe("base","*");
        // 设置消费模式
        c.setMessageModel(MessageModel.BROADCASTING);      // 广播模式

        // 4、设置回调函数、处理消息 (监听消息)
        c.registerMessageListener(new MessageListenerConcurrently() {
            // 接收消息内容
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                // msgs里面传过来的是byte数组，这里我们转换一下
                for (MessageExt msg:msgs) {
                    System.out.println( new String(msg.getBody()) );
                }
                // 返回消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 5、启动消费者consumer
        c.start();
    }
}
