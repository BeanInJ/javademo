import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

class MyList {
    public static void main(String[] args) {
        //        List ：先进先出、有序集合、允许重复、有索引
        //        LIst接口里面包含的方法：
        //                - add(索引，“元素”) ，返回空
        //                - get(索引)，返回获取的元素
        //                - remove(索引)，返回被移除元素
        //                - set(索引，“替换后的元素”)，返回被替换元素
        //
        //                注意:索引越界异常
        //
        //        List接口常用实现类：ArrayList、LinkedList、Vector
    }
}



class MyArrayList{
    public static void main(String[] args) {
        // ArrayList 接口大小可变的数组（特点：查询快、增删慢、异步实现）
        // 它的底层就是一个数组。增加一个元素：原理就是先填加一个数组（长度是原数组长度+1），然后把原来的数组拷贝过来。
        // 关于ArrayList的操作见Test_4_ArrayList
    }
}

class MyLinkedList{
    // LinkedList是list集合的一个链表实现（特点：查询慢、增删快）
    // 如果a、b、c按顺序分别存入ArrayList、LinkedList，那么他们的答应结果分别是[a, b, c]、[c, b, a]

    // 它的方法：
    //    增：
    //    add(E e)：在链表后添加一个元素；   通用方法
    //    addFirst(E e)：在链表头部插入一个元素；  特有方法
    //    addLast(E e)：在链表尾部添加一个元素；  特有方法
    //    push(E e)：与addFirst方法一致
    //    offer(E e)：在链表尾部插入一个元素                                                                                                                                                  add(int index, E element)：在指定位置插入一个元素。
    //    offerFirst(E e)：JDK1.6版本之后，在头部添加； 特有方法                                                                                                         offerLast(E e)：JDK1.6版本之后，在尾部添加； 特有方法
    //
    //    删除：
    //    remove() ：移除链表中第一个元素;    通用方法
    //    remove(E e)：移除指定元素；   通用方法
    //    removeFirst(E e)：删除头，获取元素并删除；  特有方法
    //    removeLast(E e)：删除尾；  特有方法
    //    pollFirst()：删除头；  特有方法
    //    pollLast()：删除尾；  特有方法
    //    pop()：和removeFirst方法一致，删除头。
    //    poll()：查询并移除第一个元素     特有方法
    //
    //    查：
    //    get(int index)：按照下标获取元素；  通用方法
    //    getFirst()：获取第一个元素；  特有方法
    //    getLast()：获取最后一个元素； 特有方法
    //    peek()：获取第一个元素，但是不移除；  特有方法
    //    peekFirst()：获取第一个元素，但是不移除；
    //    peekLast()：获取最后一个元素，但是不移除；
    //    pollFirst()：查询并删除头；  特有方法
    //    pollLast()：删除尾；  特有方法
    //    poll()：查询并移除第一个元素     特有方法

    // 常用的方法：
    // push(E e)：在链表头部插入一个元素
    // pop()： 获取头元素并删除，头结点为空抛出异常
    // poll(): 获取头元素并删除，头结点为空返回null
    // addLast(E e)：在链表尾部添加一个元素；
    // pollLast()：删除尾；

    public static void main(String[] args) {
        LinkedList<String> l = new LinkedList<>();
        // 添加
        l.add("a");          // 往尾部加
        l.push("b");      // 往头部加
        l.addFirst("c");  // 往头部加
        // 获取
        l.getFirst();        // 获取第一个
        l.getLast();         // 获取最后一个
        l.get(0);            // 获取索引为0的

        l.pop();             // 删除头元素
        l.poll();            // 删除头元素
        l.pollLast();        // 删除尾元素

        boolean e = l.isEmpty();   // 判断是否为空
        l.clear();                 // 清空

    }

}

class MyVector{
    public static void main(String[] args) {
        // Vector 同步的可变长度数组
        // 现在基本上被ArrayList取代了
    }
}