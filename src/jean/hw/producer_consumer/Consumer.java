package jean.hw.producer_consumer;

public class Consumer extends Thread {
	private PC pc;

	public Consumer(PC pc) { super(); this.pc = pc; }

	public void run() {
		while (true) {
			try {
				pc.consume();       // ���ü���������ѷ���
				Thread.sleep(1000); // ģ��������������������ʱ��
			} catch (InterruptedException e) {}
		}
	}

}

