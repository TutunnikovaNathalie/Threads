package ru.specialist.join;

import static java.lang.System.out;
public class Program {

	public static void main(String[] args) throws InterruptedException {
		Thread t1, t2;
		Thread main = Thread.currentThread();
		
		(t1 = new Thread(()-> {
			for(int i=1; i <= 100; i++)
			{
				if (i==50) main.interrupt();
				out.printf("%s : %d\n",Thread.currentThread().getName(), i);
			}
		}, "A")).start();
		
		(t2 = new Thread(()-> {
			for(int i=1; i <= 100; i++)
				out.printf("%s : %d\n",Thread.currentThread().getName(), i);	
		}, "B")).start();
		
		//Thread.sleep(1000);
		try
		{
			t1.join();
		} catch(InterruptedException ex)
		{
			out.println("���� �������� ����");
			out.println(main.isInterrupted());
			out.println(Thread.interrupted());
		}
		t2.join();
		
		out.println(Thread.currentThread().getName()); 
		
	}

}