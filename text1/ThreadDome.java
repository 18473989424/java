package dome.text1;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class myThread implements  Callable<String>{

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

class AThread implements Callable<String>{
	@Override
	public String call() throws Exception {
		System.out.println(Thread.currentThread().getName()+"�����ˣ�");
		return null;
	}
}

public class ThreadDome  {
	
	public static void main(String[] args) {
		
		AThread aThreadA = new AThread();
		AThread aThreadB = new AThread();
		AThread aThreadC = new AThread();
		FutureTask<String> futureTaskA = new FutureTask<>(aThreadA);
		FutureTask<String> futureTaskB = new FutureTask<>(aThreadB);
		FutureTask<String> futureTaskC = new FutureTask<>(aThreadC);
		Thread thread1 = new Thread(futureTaskA,"�߳�A");
		Thread thread2 = new Thread(futureTaskB,"�߳�B");
		Thread thread3 = new Thread(futureTaskC,"�߳�C");
		/*
		thread1.start();
		thread2.start();
		thread3.start();
		*/
		
		System.out.println("------------�ָ���--------------");
		
		long startTime = System.currentTimeMillis();
		
		System.out.println("��һ����Ҫ��ʼ������");
		
		System.out.println("�ڶ�����׼����������ʳ��");
		
		//��ʼ���Ϲ�����ߣ���������
		//�������
		OnlineShopping onlineShopping = new OnlineShopping();
		FutureTask<KitChen> kitchen = new FutureTask<>(onlineShopping);
		Thread threadKitchen = new Thread(kitchen);
		threadKitchen.start();
		//����ʳ��
		Shopping shopping = new Shopping();
		FutureTask<Food> food = new FutureTask<>(shopping);
		Thread threadFood = new Thread(food);
		threadFood.start();
		
		//ÿ����ִ�� �鿴һ��
		while(true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(kitchen.isDone() && food.isDone()) {
				System.out.println("������������ʳ�Ķ������ˣ�����չ�ֳ�����");
				System.out.println("�ܹ���ʱ" + (System.currentTimeMillis() - startTime) + "ms");
				break;
			}else {
				if(kitchen.isDone()) {
					System.out.println("���ߵ�����");
				}else if(food.isDone()){
					System.out.println("ʳ�ĵ�����");
				}
				System.out.println("�ܹ���ʱ" + (System.currentTimeMillis() - startTime) + "ms");
			}
		}
		}
	
}	

// ���Ϲ������
class OnlineShopping implements Callable<KitChen> {

	@Override
	public KitChen call() throws Exception {
		double random = Math.random();
		long time = Math.round(random*1000) + 2000;
		Thread.sleep(time);
		System.out.println("�ȴ����ҷ�����" + time + "����");
		// ���������ɹ��ջ�
		return new KitChen();
	}
}

class Shopping implements Callable<Food>{

	@Override
	public Food call() throws Exception {
		Thread.sleep(500);
		return new Food();
	}
	
}

// ����
class KitChen {

}

// ʳ��
class Food {

}
