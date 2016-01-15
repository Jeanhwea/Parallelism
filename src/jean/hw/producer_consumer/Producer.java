package jean.hw.producer_consumer;

public class Producer extends Thread{
	private PC pc;

	public Producer(PC pc) { super(); this.pc = pc; }

	public void run() {
		while (true) {
			try {
				pc.produce();      // ���ü��������������
				Thread.sleep(200); // ģ��������������������ʱ��
			} catch (InterruptedException e) {} 
		}
	}

}
