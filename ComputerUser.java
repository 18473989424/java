
public class ComputerUser implements Runnable {
	Computer computer;

	public ComputerUser(Computer computer) {
		this.computer = computer;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			synchronized (computer) {
				System.out.println(Thread.currentThread().getName() + "--------------现在是购买，newCom:" + Computer.newCom);

				if (Computer.newCom == 1) {
					System.out.println("购买一台电脑！" + "---------->");
					Computer.num--;
					Computer.newCom--;
					computer.notify();
				} else {
					try {
						System.out.println("没库存，将会关闭购买线程");
						computer.wait(500);
						System.out.println("开启了购买线程");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
