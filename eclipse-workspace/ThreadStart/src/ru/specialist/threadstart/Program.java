package ru.specialist.threadstart;

import static java.lang.System.out;

class MyThread extends Thread
{
	
	
	public MyThread(String name) {
		super(name);
	}

	@Override
	public void run() {
	
		for(int i=1; i <= 100; i++)
			out.printf("%s : %d\n",this.getName(), i);	
	}
}

class MySuperThread implements Runnable
{
	@Override
	public void run() {
	
		for(int i=1; i <= 100; i++)
			out.printf("%s : %d\n","C", i);	
	}
	
}
public class Program {

	public static void main(String[] args) {
		Thread t1 = new MyThread("A");
		Thread t2 = new MyThread("B");
		Thread t3 = new Thread(new MySuperThread());
		Thread t4 = new Thread(
				new Runnable() {
					
					@Override
					public void run() {
					
						for(int i=1; i <= 100; i++)
							out.printf("%s : %d\n","D", i);	
					}
				}
				);
		
		t1.setPriority(Thread.MAX_PRIORITY);
		t1.setDaemon(true);
		t2.setDaemon(true);
		t3.setDaemon(true);
		t4.setDaemon(true);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		(new Thread(
				new Runnable() {
					
					@Override
					public void run() {
					
						for(int i=1; i <= 100; i++)
							out.printf("%s : %d\n","E", i);	
					}
				}
				)).start();
		
	/*	(new Thread(()-> {
			for(int i=1; i <= 100; i++)
				out.printf("%s : %d\n",Thread.currentThread(), i);	
		}, "F")).start();
		
		out.println(Thread.currentThread().getName()); */

	}

}
