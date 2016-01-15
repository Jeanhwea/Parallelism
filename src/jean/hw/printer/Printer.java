package jean.hw.printer;

public class Printer {
	public int num; // 当前将要打印的数字

	public Printer(int init_num) { num = init_num; }

	// 连续打印五个数字,并将num增加
	void print_number(User us) {
		for (int i = 0; i < 5; i++)
			System.out.format("线程  %d:%d\n", us.id, num++);
	}
}
