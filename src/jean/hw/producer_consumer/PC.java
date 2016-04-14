
package jean.hw.producer_consumer;

import java.util.*;

public class PC {
    Vector<Integer> pool; // 模拟产品池

    int product = 0; // 每次生产产品的计数
    public static int EMPTY = 0; // 产品池为空的常量
    public static int FULL = 25; // 产品池为满的常量

    public PC()
    {
        pool = new Vector<Integer>();
    }

    public static void main(String[] args)
    {
        // 创建生产者和消费者，并且启动相应的线程
        PC pc = new PC();
        Consumer consumer = new Consumer(pc);
        Producer producer = new Producer(pc);
        consumer.start();
        producer.start();
    }

    public synchronized void consume()
    {
        try {
            // 入口协议
            if (pool.size() == EMPTY)
                this.wait();

            // 模拟消费者的行为
            System.out.println("Consume: " + pool.firstElement().toString());
            pool.removeElementAt(0);

            // 出口协议
            if (pool.size() == FULL - 1)
                this.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void produce()
    {
        try {
            // 入口协议
            if (pool.size() == FULL)
                this.wait();

            // 模拟生产者的行为
            ++product;
            pool.addElement(new Integer(product));
            System.out.println("Produce: " + product);

            // 出口协议
            if (pool.size() == EMPTY + 1)
                this.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}