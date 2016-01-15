package jean.hw.reader_writer;

public class Writer extends Thread {
	RW rw;

	Writer(RW rw) {
		this.rw = rw;
	}

	public void run() {
		try {
			while (true) {
				Thread.sleep(1000);
				rw.write();
			}
		} catch (InterruptedException e) {
		}
	}
}
