import java.io.FileOutputStream;
import java.util.Scanner;

class MyRunnable implements Runnable {

	public void run() {
		System.out.println("进入守护线程：" + Thread.currentThread().getName());
		try {
			writeToFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("退出守护线程：" + Thread.currentThread().getName());
	}

	public void writeToFile() throws Exception {
		FileOutputStream fos = new FileOutputStream("D:/abc.text");
		int cont = 0;
		while (cont < 99) {
			fos.write(("\r\nwork" + cont).getBytes());
			System.out.println("运行守护线程：" + Thread.currentThread().getName() + "写入work" + cont);
			cont++;
			Thread.sleep(1000);
		}
	}

}

public class DaemonThread {

	public static void main(String[] args) {

		System.out.println("进入主线程：" + Thread.currentThread().getName());

		MyRunnable myRunnable = new MyRunnable();
		Thread mt = new Thread(myRunnable);
		mt.setDaemon(true);
		mt.start();

		Scanner sc = new Scanner(System.in);
		sc.next();

		System.out.println("退出主线程：" + Thread.currentThread().getName());
	}
}
