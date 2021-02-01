import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Test_12_Iterator {
    public static void main(String[] args) {
        // Iterator 迭代器 它用来将集合里的内容一个一个取出来
        // 它只有三个方法：hasNext() \ next() \ remove()

        // Iterator 是一个接口，它的实现类用Collection接口中的iterator()方法返回。
        Collection<String> c = new ArrayList<String>();   // 向上转型
        c.add("a");
        c.add("b");
        c.add("c");
        Iterator<String> i = c.iterator();
        // hasNext()判断还有没有下一个元素，有返回true
        boolean b = i.hasNext();
        // next()用来取出下一个元素
        String n = i.next();
        // 利用循环取出集合中所有元素
        // while方式
        while(i.hasNext()){
            System.out.println(i.next());
        }
        // for方式
        for(Iterator<String> i1=c.iterator();i1.hasNext();){
            System.out.println(i1.next());
        }
        // remove() 移除当前指向的元素
        i.remove();

        // 增强for循环  for（集合/数组的类型 变量名：集合名/数组名）｛｝
        // 使用增强for循环遍历数组
        int[] a = {1,2,3,4,5};
        for(int i2: a){
            System.out.println(i2);
        }

    }
}