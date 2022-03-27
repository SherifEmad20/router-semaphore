public class Semaphore {

	private int value = 0;

	public Semaphore() {
		value = 0;
	}

	public Semaphore(int intial) {
		value = intial;
	}

	public synchronized void Waiting(Device d) throws InterruptedException {
		value--;

		if (value < 0) {
				System.out.println(d.getDname() + "(" + d.getDtype() + ")" + " Arrived and waiting");

				wait();

				d.sleep(10);
		}
		d.sleep(10);
		System.out.println("(" + d.getDname() + ")" + "(" + d.getDtype() + ")" + " Arrived");
	}

	public synchronized void Signal(Device d) throws InterruptedException {
		value++;

		if (value <= 0) {
			notify();
		}
		
		System.out.println(d.getDname() + " Loged out");
		d.sleep(10);
	}

}
