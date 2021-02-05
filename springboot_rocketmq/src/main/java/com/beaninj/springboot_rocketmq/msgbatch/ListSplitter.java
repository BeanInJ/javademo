package com.beaninj.springboot_rocketmq.msgbatch;

import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// 消息大于4MB时，用此类分割
public class ListSplitter implements Iterator<List<Message>> {
    private final int SIZE_LIMIT = 1024*1024*4;
    private final List<Message> messages;
    private int currIndex;

    public ListSplitter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean hasNext() {
        return currIndex < messages.size();
    }

    @Override
    public List<Message> next() {
        int nextIndex = currIndex;
        int totalSize = 0;
        for (; nextIndex<messages.size();nextIndex++){
            Message message = messages.get(nextIndex);
            int tmpSize = message.getTopic().length()+message.getBody().length;
            Map<String,String> properties = message.getProperties();
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                tmpSize += entry.getKey().length()+entry.getValue().length();
            }
            tmpSize= tmpSize+20;        // 增加日志的开销20字节
            if(tmpSize>SIZE_LIMIT){
                // 单个消息超过了最大限制。忽略，否则会阻塞分裂的进程
                if(nextIndex - currIndex == 0){
                    // 假如下一个列表没有元素，则添加这个子列表，然后退出循环，否则只是退出循环
                    nextIndex++;
                }
                break;
            }
            if(tmpSize+totalSize>SIZE_LIMIT){
                break;
            }else {
                totalSize+=tmpSize;
            }
        }
        List<Message> subList = messages.subList(currIndex,nextIndex);
        currIndex = nextIndex;
        return subList;
    }
}

// 消息分类类的使用示例：
class Test{
    public static void main(String[] args) {
        ArrayList<Message> msgs = new ArrayList<>();

        Message msg1 = new Message("ms","Tag1",("hi rocketmq "+1).getBytes());
        Message msg2 = new Message("ms","Tag1",("hi rocketmq "+2).getBytes());
        Message msg3 = new Message("ms","Tag1",("hi rocketmq "+3).getBytes());

        msgs.add(msg1);
        msgs.add(msg2);
        msgs.add(msg3);

        ListSplitter splitter = new ListSplitter(msgs);
        while (splitter.hasNext()){
            try{
                List<Message> listItem = splitter.next();
                // producer来自 DefaultMQProducer producer = new DefaultMQProducer("group1");
//                producer.send(listItem);
            }catch (Exception e){
                e.printStackTrace();
                // 处理异常
            }
        }
    }
}

