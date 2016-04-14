package jean.hw.reader_writer;

public class RW {
    private int data = 0;
    private int nread = 0;

    private synchronized void startRead() {
        nread++;
    }

    private synchronized void endRead() {
        nread--;
        if (nread == 0)
            notify();
    }

    public void read() {
        startRead();
        System.out.println("read+" + data);
        endRead();
    }

    public synchronized void write() {
        while (nread > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                return;
            }
        }
        data++;
        System.out.println("wirte+" + data);
        notify();
    }

    public static void main(String[] args) {
        RW rw = new RW();
        new Reader(rw).start();
        new Writer(rw).start();
    }
}