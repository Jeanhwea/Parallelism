package jean.hw.printer;

public class User extends Thread {
    int id; // 用户的ID
    public static Printer printer; // 共享的打印机资源
    public static final int BEGIN = 1; // 打印数字的开始
    public static final int END = 75; // 打印数字的结束

    public User(int id) {
        this.id = id;
        printer = new Printer(BEGIN);
    }

    public void run() {
        try {
            Thread.sleep(1000); // 等待所用线程创建完毕
            synchronized (printer) {
                while (printer.num <= END) {
                    // 如果是属于自己的数, 则打印出来，否则等待
                    if ((printer.num / 5 % 3 + 1) == id) {
                        printer.print_number(this); // 完成打印工作
                        printer.notifyAll();
                    } else {
                        printer.wait();
                    }
                    Thread.sleep(200); // 模拟其他工作耗时
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 创建3个用户,并开启相应的线程
    public static void main(String[] args) {
        new User(1).start();
        new User(2).start();
        new User(3).start();
    }
}