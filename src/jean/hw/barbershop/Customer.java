package jean.hw.barbershop;

public class Customer extends Thread {
	BarberShop bshop;
	int id;

	public Customer(BarberShop bs, int id) {
		this.bshop = bs;
		this.id = id;
	}

	public void run() {
		try {
			while (true) {
				Thread.sleep(5000);
				bshop.getHaircut(this);
			}
		} catch (InterruptedException e) {
		}
	}

}
