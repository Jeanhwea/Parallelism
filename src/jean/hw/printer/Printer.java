package jean.hw.printer;

public class Printer {
	public int num; // ��ǰ��Ҫ��ӡ������

	public Printer(int init_num) { num = init_num; }

	// ������ӡ�������,����num����
	void print_number(User us) {
		for (int i = 0; i < 5; i++)
			System.out.format("�߳�  %d:%d\n", us.id, num++);
	}
}
