package jean.hw.barbershop;

public class BarberShop {
	public static final int EMPTY_CHAIR = 0;
	public static final int FULL_CHAIR = 2;
	private static int waiting = EMPTY_CHAIR; // 坐席上等待的人数,初始化为空
	private static Object mutexWaiting = new Object(); // 坐席上等待队列访问的互斥锁
	public static Object semaBarberReady = new Object(); // 理发师是否准备就绪的信号量

	public static void main(String[] args) {
		BarberShop shop = new BarberShop();
		Barber barber = new Barber(shop);
		barber.start();
		for (int i = 0; i < 5; i++) {
			Customer customer = new Customer(shop, i);
			customer.start();
		}
	}

	public void getHaircut(Customer customer) {
		try {
			synchronized (mutexWaiting) {
				if (waiting < FULL_CHAIR) {
					// 如果有等待的座位，则在坐席上等待理发
					waiting++;
				} else {
					// 如果没有等待的座位，则离开理发店
					System.out.println("顾客 " + customer.id + ": 离开理发店");
					return;
				}
			}

			// 等待理发师就绪的信号量
			synchronized (semaBarberReady) {
				semaBarberReady.wait();
				System.out.println("顾客 " + customer.id + ": 开始理发");
			}

		} catch (InterruptedException e) { }
	}

	public void giveHaircut(Barber barber) {
		synchronized (mutexWaiting) {
			if (waiting > EMPTY_CHAIR) {
				// 如果有等待理发的人，则取出一个人开始进行理发
				waiting--;
			} else {
				// 如果没有等待理发的人，则理发师开始睡觉
				System.out.println("理发师: 开始睡觉");
				return;
			}
		}

		// 理发完毕，释放理发师就绪的信号量
		synchronized (semaBarberReady) {
			System.out.println("理发师: 剪了一个人的头发");
			semaBarberReady.notify();
		}

	}
}
