package jean.hw.barbershop;

public class BarberShop {
	public static final int EMPTY_CHAIR = 0;
	public static final int FULL_CHAIR = 2;
	private static int waiting = EMPTY_CHAIR; // ��ϯ�ϵȴ�������,��ʼ��Ϊ��
	private static Object mutexWaiting = new Object(); // ��ϯ�ϵȴ����з��ʵĻ�����
	public static Object semaBarberReady = new Object(); // ��ʦ�Ƿ�׼���������ź���

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
					// ����еȴ�����λ��������ϯ�ϵȴ���
					waiting++;
				} else {
					// ���û�еȴ�����λ�����뿪����
					System.out.println("�˿� " + customer.id + ": �뿪����");
					return;
				}
			}

			// �ȴ���ʦ�������ź���
			synchronized (semaBarberReady) {
				semaBarberReady.wait();
				System.out.println("�˿� " + customer.id + ": ��ʼ��");
			}

		} catch (InterruptedException e) { }
	}

	public void giveHaircut(Barber barber) {
		synchronized (mutexWaiting) {
			if (waiting > EMPTY_CHAIR) {
				// ����еȴ������ˣ���ȡ��һ���˿�ʼ������
				waiting--;
			} else {
				// ���û�еȴ������ˣ�����ʦ��ʼ˯��
				System.out.println("��ʦ: ��ʼ˯��");
				return;
			}
		}

		// ����ϣ��ͷ���ʦ�������ź���
		synchronized (semaBarberReady) {
			System.out.println("��ʦ: ����һ���˵�ͷ��");
			semaBarberReady.notify();
		}

	}
}
