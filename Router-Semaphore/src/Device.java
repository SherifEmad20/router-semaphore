import java.util.ArrayList;

public class Device extends Thread {

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

