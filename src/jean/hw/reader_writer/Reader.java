package jean.hw.reader_writer;

public class Reader extends Thread {
	RW rw;

	Reader(RW rw) {
		this.rw = rw;
	}

	public void run() {
		try {
			while (true) {
				Thread.sleep(1000);
				rw.read();
			}
		} catch (InterruptedException e) {
		}
	}
}
