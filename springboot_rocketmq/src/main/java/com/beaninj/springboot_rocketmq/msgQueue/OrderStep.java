package com.beaninj.springboot_rocketmq.msgQueue;

import java.util.ArrayList;
import java.util.List;

public class OrderStep {
    private long orderId;
    private String desc;

    @Override
    public String toString() {
        return "OrderStep{" +
                "orderId=" + orderId +
                ", desc='" + desc + '\'' +
                '}';
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    // 默认构建三个订单 1001、1002、1002
    // 1001 : 创建、付款、推送、完成
    // 1002 : 创建、付款
    // 1003 ：创建、付款
    public static List<OrderStep> buildOrders(){
        List<OrderStep> orderList = new ArrayList<>();
        OrderStep o = new OrderStep();
        o.setOrderId(1001L);
        o.setDesc("创建");
        orderList.add(o);

        o = new OrderStep();
        o.setOrderId(1002L);
        o.setDesc("创建");
        orderList.add(o);

        o = new OrderStep();
        o.setOrderId(1001L);
        o.setDesc("付款");
        orderList.add(o);

        o = new OrderStep();
        o.setOrderId(1003L);
        o.setDesc("创建");
        orderList.add(o);

        o = new OrderStep();
        o.setOrderId(1002L);
        o.setDesc("付款");
        orderList.add(o);

        o = new OrderStep();
        o.setOrderId(1003L);
        o.setDesc("付款");
        orderList.add(o);

        o = new OrderStep();
        o.setOrderId(1002L);
        o.setDesc("完成");
        orderList.add(o);

        o = new OrderStep();
        o.setOrderId(1001L);
        o.setDesc("推送");
        orderList.add(o);

        o = new OrderStep();
        o.setOrderId(1003L);
        o.setDesc("完成");
        orderList.add(o);

        o = new OrderStep();
        o.setOrderId(1001L);
        o.setDesc("完成");
        orderList.add(o);

        return orderList;
    }
}
