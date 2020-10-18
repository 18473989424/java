
public class ComputerTest {

	public static void main(String[] args) {
		Computer computer = new Computer();

		ComputerFactory f = new ComputerFactory(computer);
		ComputerUser u = new ComputerUser(computer);
		Thread t1 = new Thread(f, "生产者A");
		Thread t2 = new Thread(u, "消费者X");
		Thread t3 = new Thread(f, "生产者B");
		Thread t4 = new Thread(u, "消费者X");
		t2.start();
		t1.start();
		t3.start();
		t4.start();
	}
}
