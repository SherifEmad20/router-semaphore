/*	Name: Sherif Emad Taha
 *  ID: 20196028
 * 	
 * 	Name: Youssef El-Sayed Mahmoud
 * 	ID: 20196062
 * 	
 * 	Name: Muhamed Essam El-Den
 * 	ID: 20196047
 */

import java.util.*;
import java.io.*;

public class Network {

	public static void main(String[] args) throws FileNotFoundException {

		int connectionNum, devicesNum = 0;
		String dName, dType = "";
		ArrayList<Device> numDevices = new ArrayList<Device>();
		
		//Redirecting console to logs.txt file
		File file = new File("logs.txt");
		FileOutputStream fos = new FileOutputStream(file);
		PrintStream ps = new PrintStream(fos);

		
		Router router = new Router();
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter number of connections: ");
		connectionNum = scanner.nextInt();

		router.setConnectionNo(connectionNum);

		System.out.print("Enter number of devices: ");
		devicesNum = scanner.nextInt();

		for (int i = 0; i < devicesNum; i++) {

			Device device = new Device(router);
			System.out.print("Enter device number (" + (i + 1) + ") name: ");
			dName = scanner.next();

			device.setDname(dName);

			System.out.print("Enter device number (" + (i + 1) + ") type: ");
			dType = scanner.next();

			device.setDtype(dType);

			numDevices.add(device);
		}

		for (int i = 0; i < devicesNum; i++) {
			numDevices.get(i).start();
		}

		//sets console to the logs file.
		System.out.println("Successful connection.");
		System.out.println("Connections added to logs.");
		System.setOut(ps);

	}
}

class Semaphore {

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

class Device extends Thread {

	private String Dname;
	private String Dtype;
	private Router r = new Router();

	public Device(Router router) {
		Dname = "";
		Dtype = "";
		r = router;
	}

	public String getDname() {
		return Dname;
	}

	public void setDname(String dname) {
		Dname = dname;
	}

	public String getDtype() {
		return Dtype;
	}

	public void setDtype(String dtype) {
		Dtype = dtype;
	}

	public void connect() throws InterruptedException {
		r.occupy(this);
	}

	public void login() throws InterruptedException {
		r.log(this);
	}

	public void onlineActivity() throws InterruptedException {
		r.online(this);
	}

	public void logout() throws InterruptedException {
		r.release(this);
	}

	public void run() {
		try {

			connect();
			login();
			onlineActivity();
			logout();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}

class Router {
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
