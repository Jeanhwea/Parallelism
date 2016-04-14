package jean.hw.producer_consumer;

public class Producer extends Thread {
    private PC pc;

    public Producer(PC pc)
    {
        super();
        this.pc = pc;
    }

    public void run()
    {
        while (true) {
            try {
                pc.produce(); // 调用监控器中生产方法
                Thread.sleep(200); // 模拟生产者做其它工作的时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}