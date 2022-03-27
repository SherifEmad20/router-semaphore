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
