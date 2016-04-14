package jean.hw.producer_consumer;

public class Consumer extends Thread {
    private PC pc;

    public Consumer(PC pc) {
        super();
        this.pc = pc;
    }

    public void run() {
        while (true) {
            try {
                pc.consume(); // 调用监控器中消费方法
                Thread.sleep(1000); // 模拟消费者做其它工作的时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}