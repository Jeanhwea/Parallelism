package jean.hw.barbershop;

public class Barber extends Thread {
    BarberShop bshop;

    public Barber(BarberShop bs)
    {
        this.bshop = bs;
    }

    public void run()
    {
        try {
            while (true) {
                bshop.giveHaircut(this);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}