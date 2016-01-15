package jean.hw.printer;

public class User extends Thread {
	int id; // �û���ID
	public static Printer printer; // ����Ĵ�ӡ����Դ
	public static final int BEGIN = 1; // ��ӡ���ֵĿ�ʼ
	public static final int END = 75; // ��ӡ���ֵĽ���

	public User(int id) {
		this.id = id;
		printer = new Printer(BEGIN);
	}

	public void run() {
		try {
			Thread.sleep(1000); // �ȴ������̴߳������
			synchronized (printer) {
				while (printer.num <= END) {
					// ����������Լ�����, ���ӡ����������ȴ�
					if ((printer.num / 5 % 3 + 1) == id) {
						printer.print_number(this); // ��ɴ�ӡ����
						printer.notifyAll();
					} else {
						printer.wait();
					}
					Thread.sleep(200); // ģ������������ʱ
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// ����3���û�,��������Ӧ���߳�
	public static void main(String[] args) {
		new User(1).start();
		new User(2).start();
		new User(3).start();
	}
}