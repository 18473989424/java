
public class ComputerFactory implements Runnable {
	Computer computer;

	public ComputerFactory(Computer computer) {
		this.computer = computer;
	}

	public void run() {

		for (int i = 0; i < 10; i++) {
			synchronized (computer) {
				System.out.println(Thread.currentThread().getName() + "--------------现在是生产，newCom:" + Computer.newCom);

				if (Computer.newCom == 0) {
					System.out.println("生产一台电脑！" + "---------->");
					Computer.num++;
					Computer.newCom++;
					System.out.println("生产" + Computer.num + "台电脑");
					computer.notify();
				} else {
					try {
						System.out.println("已经生产了" + Computer.newCom + ",将会关闭生产线程");
						computer.wait(500);
						System.out.println("开启了生产线程");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
