package com.beaninj.springboot_rocketmq.msgQueue;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

public class Producer {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer p = new DefaultMQProducer("group1");
        p.setNamesrvAddr("192.168.1.11:9876;192.168.1.12:9876");
        p.start();

        List<OrderStep> orderSteps = OrderStep.buildOrders();
        for (int i=0 ; i< orderSteps.size() ; i++) {
            byte[] body = (orderSteps.get(i)+"").getBytes();
            Message msg = new Message("orderTopic", "order", "num:" + i, body);

            // 发送顺序消息,参数1：消息对象，参数2：消息队列的选择器，参数3：业务标识
            SendResult sendResult = p.send(msg, new MessageQueueSelector() {
                // 这里的Object o实际上就是订单id
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    long orderId = (long) o;
                    // 将orderId取模。index = 订单id除以消息队列数量的余数
                    // 这一步相当于在为 ”订单id“ 选择 进哪个队列
                    long index = orderId % list.size();
                    return list.get((int) index);
                }
            }, orderSteps.get(i).getOrderId());

            System.out.println("发送结果："+sendResult);
        }

    }
}
