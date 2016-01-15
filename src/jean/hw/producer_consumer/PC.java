package jean.hw.producer_consumer;

import java.util.*;

public class PC {
	Vector<Integer> pool;         // ģ���Ʒ��
	
	int product = 0;              // ÿ��������Ʒ�ļ���
	public static int EMPTY = 0;  // ��Ʒ��Ϊ�յĳ���
	public static int FULL  = 25; // ��Ʒ��Ϊ���ĳ���

	public PC() { pool = new Vector<Integer>(); }

	public static void main(String[] args) {
		// ���������ߺ������ߣ�����������Ӧ���߳�
		PC pc = new PC();
		Consumer consumer = new Consumer(pc);
		Producer producer = new Producer(pc);
		consumer.start();
		producer.start();
	}

	public synchronized void consume() {
		try {
			// ���Э��
			if (pool.size() == EMPTY)
				this.wait();
			
			// ģ�������ߵ���Ϊ
			System.out.println("Consume: " + pool.firstElement().toString());
			pool.removeElementAt(0);
			
			// ����Э��
			if (pool.size() == FULL-1)
				this.notify();
		} catch (InterruptedException e) { }
	}

	public synchronized void produce() {
		try {
			// ���Э��
			if (pool.size() == FULL) 
				this.wait();
			
			// ģ�������ߵ���Ϊ
			++product;
			pool.addElement(new Integer(product));
			System.out.println("Produce: " + product);
			
			// ����Э��
			if (pool.size() == EMPTY+1)
				this.notify();
		} catch (InterruptedException e) {}
	}

}
