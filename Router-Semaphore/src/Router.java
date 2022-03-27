import java.util.ArrayList;

public class Router {
	private int connectionNo = 0;
	private Semaphore s;
	private ArrayList<Device> connectedDevices = new ArrayList<Device>();

	public void setConnectionNo(int cNumber) {
		connectionNo = cNumber;
		s = new Semaphore(cNumber);
	}

	public int getConnectionNo() {
		return connectionNo;
	}

	public void occupy(Device d) throws InterruptedException {

		s.Waiting(d);
		connectedDevices.add(d);
		String s = "";

		System.out.println("Connection " + (connectedDevices.lastIndexOf(d) + 1) + ": " + d.getDname() + " occupied");

		d.sleep(10);
	}

	public void online(Device d) throws InterruptedException {

		System.out.println("Connection " + (connectedDevices.lastIndexOf(d) + 1) + ": " + d.getDname()
				+ " preform online activity");

		d.sleep(10);

	}

	public void log(Device d) throws InterruptedException {

		System.out.println("Connection " + (connectedDevices.lastIndexOf(d) + 1) + ": " + d.getDname() + " login");

		d.sleep(10);

	}

	public void release(Device d) throws InterruptedException {
		connectedDevices.remove(d);
		s.Signal(d);
		d.sleep(10);

	}
}