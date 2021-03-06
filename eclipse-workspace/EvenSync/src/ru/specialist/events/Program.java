package ru.specialist.events;

import static java.lang.System.out;

public class Program {

	public static void main(String[] args) {
		Thread t1,t2;
		
		Object sync = new Object();
		
		
		(t1 = new Thread(()-> {
			for(int i=1; i <= 100; i++)
			{
				//if (i==50) main.interrupt();
				out.printf("%s : %d\n",Thread.currentThread().getName(), i);
				if(i==50)
				{
					synchronized(sync)
					{
						sync.notify();
					}
				}
			}
		}, "A")).start();
		
		(t2 = new Thread(()-> {
			
			try {
				synchronized(sync)
				{
					sync.wait();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i=1; i <= 100; i++)
				out.printf("%s : %d\n",Thread.currentThread().getName(), i);	
		}, "B")).start();

	}

}
