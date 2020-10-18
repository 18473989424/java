import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableDemo {

	public static void main(String[] args) {

		MyThread myThread = new MyThread();

		FutureTask<String> taskA = new FutureTask<String>(myThread);
		FutureTask<String> taskB = new FutureTask<String>(myThread);
		FutureTask<String> taskC = new FutureTask<String>(myThread);
		Thread threadA = new Thread(taskA, "线程A");
		Thread threadB = new Thread(taskB, "线程B");
		Thread threadC = new Thread(taskC, "线程C");
		threadA.start();
		threadB.start();
		threadC.start();
	}
}

class MyThread implements Callable<String> {
	private boolean flag = false;

	public String call() throws Exception {
		synchronized (this) {
			if (this.flag == false) {
				System.out.println(Thread.currentThread().getName() + " - 抢答成功！");
				this.flag = true;
			} else {
				System.out.println(Thread.currentThread().getName() + " - 抢答失败！");
			}
		}
		return null;
	}
}
