package ru.specialist.datasync;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Sync
{
	private volatile int counter = 0;

	public int getCounter() {
		return counter;
	}
	
	public synchronized void increment()
	{
		counter++;
	}
	
}

/*class Sync
{
	public volatile int counter = 0;
}*/

public class Program {

	//static int counter = 0;
	public static void main(String[] args) throws InterruptedException {
		Thread t1,t2;
		
		List<Integer> nums = Collections.synchronizedList(new ArrayList<Integer>());
		
		//Object sync = new Object();
		Sync sync = new Sync();
		
		(t1 =new Thread(()-> {
			for(int i=1; i <= 10000000; i++)
			{
				//if (i==50) main.interrupt();
				//out.printf("%s : %d\n",Thread.currentThread().getName(), i);
				//counter++;
				//synchronized(sync)
				{
					//counter = counter + 1;
					sync.increment();
					nums.add(i);
				}
			}
		}, "A")).start();
		
		(t2 = new Thread(()-> {
			for(int i=1; i <= 10000000; i++)
				//out.printf("%s : %d\n",Thread.currentThread().getName(), i);
				//synchronized(sync)
				{
					//counter = counter + 1;
					sync.increment();
					nums.add(i);
				}
		}, "B")).start();
		
		t1.join();
		t2.join();
		
		out.println(sync.getCounter());
		out.println(nums.size());
		
		long l1 = System.currentTimeMillis();
		double r1 = Integral.singleThread(Math::sin, 0, Math.PI/2);
		long l2 = System.currentTimeMillis();
		out.printf("%f \n", r1);
		
		out.printf("%d \n", l2-l1);
		
	}

}
