/**
 * @(#)EvaluateTemperature.java
 *
 *
 * @author 
 * @version 1.00 2015/9/4
 */

import java.util.Scanner;


public class EvaluateTemperature {
	
	
	public static void main (String args[])
    	
    {
    	Scanner s=new Scanner(System.in);
    	
    	System.out.println("enter the temperature");
    	
    	int n=s.nextInt();
    	   		
    		
  System.out.println("Enter the unit of measurement : C/F ?");
  
   
    	
    	if (s.next().startsWith("C"))
    	{
    	
    	int 	l = ConvertUnit(n) ;
    	
    	
    	String v = TempDescription(l);
    	System.out.println("Temperature is :" +v);
    	}
    	
    	else
    	{
    	
    		String v = TempDescription(n);
    	System.out.println("Temperature is :" +v);	
    	}
    }
   
    
    public static int ConvertUnit(int a)
    {
    	int f = a*9/5 +32 ;
    	return f;
    }
    
    public static String TempDescription(int k)
    {
    	if(k<0)
    		return "Extremely Cold";
    	if(k>=0&&k<=32)											
    		return "Very cold";
    	if (k>=33&&k<=50)
    		return "Cold";
    	if(k>=51&&k<=70)
    		return "Mild";
    	if(k>=71&&k<=90)
    		return "Warm";
    	if (k>=91&&k<=100)
    		return "Hot";
    	if(k>100)
    		return "very Hot";
    		
    	else 
    		return "Enter a Valid temperature";
    }
    
}

