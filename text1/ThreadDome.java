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
		System.out.println(Thread.currentThread().getName()+"运行了！");
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
		Thread thread1 = new Thread(futureTaskA,"线程A");
		Thread thread2 = new Thread(futureTaskB,"线程B");
		Thread thread3 = new Thread(futureTaskC,"线程C");
		/*
		thread1.start();
		thread2.start();
		thread3.start();
		*/
		
		System.out.println("------------分割线--------------");
		
		long startTime = System.currentTimeMillis();
		
		System.out.println("第一步：要开始做饭了");
		
		System.out.println("第二步：准备做饭工具食材");
		
		//开始网上购买厨具，两到三天
		//购买厨具
		OnlineShopping onlineShopping = new OnlineShopping();
		FutureTask<KitChen> kitchen = new FutureTask<>(onlineShopping);
		Thread threadKitchen = new Thread(kitchen);
		threadKitchen.start();
		//购买食材
		Shopping shopping = new Shopping();
		FutureTask<Food> food = new FutureTask<>(shopping);
		Thread threadFood = new Thread(food);
		threadFood.start();
		
		//每半天执行 查看一次
		while(true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(kitchen.isDone() && food.isDone()) {
				System.out.println("第三步：厨具食材都到手了，可以展现厨艺了");
				System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
				break;
			}else {
				if(kitchen.isDone()) {
					System.out.println("厨具到手了");
				}else if(food.isDone()){
					System.out.println("食材到手了");
				}
				System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
			}
		}
		}
	
}	

// 网上购买厨具
class OnlineShopping implements Callable<KitChen> {

	@Override
	public KitChen call() throws Exception {
		double random = Math.random();
		long time = Math.round(random*1000) + 2000;
		Thread.sleep(time);
		System.out.println("等待卖家发货：" + time + "毫秒");
		// 第三步：成功收货
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

// 厨具
class KitChen {

}

// 食材
class Food {

}
