package ru.specialist.datasync;

import java.util.function.DoubleFunction;

public class Integral {
	
	public static final int STEPS = 1000000;
	
	public static double singleThread(DoubleFunction<Double> f, 
			double a, double b, int steps)
	{
		double h = (b-a)/steps;
		double summa = 0d;
		for(int i=0; i < steps; i++)
		{
			double x = a +i*h+h/2;
			double y = f.apply(x);
			summa += y*h;
		}
		
		return summa;
	}

	public static double singleThread(DoubleFunction<Double> f, double a, double b)
	{
		return  singleThread(f,a,b, STEPS);
	}

}
