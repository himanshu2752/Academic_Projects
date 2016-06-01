import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Random;
/** 
 * @author himanshu
 *	class processor is the parent processor and executing the program
 */
public class Processor 
{
	static Process memProc = null;  //child memory process
	static BufferedReader PipeInp = null;	
	static PrintWriter prntWrtr=null; //pipe
	static int ProgCountR=0,AcclmtR,X,Y,InstReg =0; // registers(ProgCountR=Program Counter;AcclmtR=AcclmtRcumulator)
	static int Timer=0 ; // to implement timer logic
	static int	StckPntr=1000; // stack pointer starting from 1000 and moving up. 
	static String sRead = null;	// reading string/instruction from pipe and then storing it in IR (by parsing in integer)
	static boolean IntrptFlag=true; // to handle interrupts
	
	
	/**
	 * Main class for execution of program
	 *	Instructions are executed using switch case
	 * @param args
	 */
	
	public static void main(String[] args)
	{
		String s = args[1];
		try
		{
			//Initializing memory
			memProc = Runtime.getRuntime().exec("java Memory "+args[0]);
			PipeInp=new BufferedReader(new InputStreamReader(memProc.getInputStream()));
        	prntWrtr = new PrintWriter(new OutputStreamWriter(memProc.getOutputStream()));
         
           
        	//Execution of instructions 
            while(true)// Infinite loop which exits for either exception or when program ends
            {
            	prntWrtr.println(ProgCountR);
        		prntWrtr.flush();
            	
            	try{
            		 sRead = PipeInp.readLine();         
            	}
            	catch(IOException e){System.out.println("memory not AcclmtRcessible");
            	System.exit(0);
            	}
        		try {
        			InstReg = Integer.parseInt(sRead);
        		}
        		catch(NumberFormatException e){e.printStackTrace();System.exit(1);}
        		
            		switch(InstReg)
                	{
                		case 1:
                			ProgCountR++;
                			prntWrtr.println(ProgCountR);   
                        	prntWrtr.flush();
                        	AcclmtR=Integer.parseInt(PipeInp.readLine());
                        	ProgCountR++;
                				break;
                		case 2:
                			ProgCountR++;
                			prntWrtr.println(ProgCountR);   
                        	prntWrtr.flush();
                        	prntWrtr.println(PipeInp.readLine());   
                        	prntWrtr.flush();
                        	AcclmtR=Integer.parseInt(PipeInp.readLine());
                        	ProgCountR++;
                				break;
                		case 3:
                			ProgCountR++;
                			prntWrtr.println(ProgCountR);   
                        	prntWrtr.flush();
                        	prntWrtr.println(PipeInp.readLine());   
                        	prntWrtr.flush();
                        	prntWrtr.println(PipeInp.readLine());   
                        	prntWrtr.flush();
                        	AcclmtR=Integer.parseInt(PipeInp.readLine());
                        	ProgCountR++;
                				break;
                		case 4:
                			ProgCountR++;
                			prntWrtr.println(ProgCountR);   
                        	prntWrtr.flush();
                        	prntWrtr.println(Integer.parseInt(PipeInp.readLine())+X);   
                        	prntWrtr.flush();
                        	AcclmtR=Integer.parseInt(PipeInp.readLine());
                        	ProgCountR++;
                				break;
                		case 5:
                			ProgCountR++;
                			prntWrtr.println(ProgCountR);   
                        	prntWrtr.flush();
                        	prntWrtr.println(Integer.parseInt(PipeInp.readLine())+Y);   
                        	prntWrtr.flush();
                        	AcclmtR=Integer.parseInt(PipeInp.readLine());
                        	ProgCountR++;
                				break;
                		case 6:
                			prntWrtr.println((StckPntr+X));   
                        	prntWrtr.flush();
                        	AcclmtR=Integer.parseInt(PipeInp.readLine());
                        	ProgCountR++;
                				break;
                		case 7:
                			ProgCountR++;
                			prntWrtr.println(ProgCountR);   
                        	prntWrtr.flush();
                        	prntWrtr.println(PipeInp.readLine()+" "+AcclmtR); 
                        	prntWrtr.flush();
                        	ProgCountR++;
                				break;
                		case 8:
                			Random rg = new Random();
                			AcclmtR=rg.nextInt(99)+1; //random numbers
                			ProgCountR++;
                				break;
                		case 9:
                			ProgCountR++;
                			prntWrtr.println(ProgCountR);   
                        	prntWrtr.flush();
                        		if(Integer.parseInt(PipeInp.readLine())==1)
                        			System.out.print(AcclmtR);
                        		else
                        			System.out.print((char)AcclmtR); //Casting Char to print AcclmtRcumultor
                        	ProgCountR++;
                				break;
                		case 10:
                			AcclmtR=AcclmtR+X;
                			ProgCountR++;
                				break;
                		case 11:
                			AcclmtR=AcclmtR+Y;
                			ProgCountR++;
                				break;
                		case 12:
                			AcclmtR=AcclmtR-X;
                			ProgCountR++;
                				break;
                		case 13:
                			AcclmtR=AcclmtR-Y;
                			ProgCountR++;
                				break;
                		case 14:
                			X=AcclmtR;
                			ProgCountR++;
                				break;
                		case 15:
                			AcclmtR=X;
                			ProgCountR++;
                				break;
                		case 16:
                			Y=AcclmtR;
                			ProgCountR++;
                				break;
                		case 17:
                			AcclmtR=Y;
                			ProgCountR++;
                				break;
                		case 18:
                			StckPntr=AcclmtR;
                			ProgCountR++;
                				break;
                		case 19:
                			AcclmtR=StckPntr;
                			ProgCountR++;
                				break;
                		case 20:
                			ProgCountR++;
                			prntWrtr.println(ProgCountR);   
                        	prntWrtr.flush();
                        	ProgCountR=Integer.parseInt(PipeInp.readLine());
                				break;
                		case 21:
                			ProgCountR++;
                			if(AcclmtR==0)
                			{
                					prntWrtr.println(ProgCountR);   
                            		prntWrtr.flush();
                            		ProgCountR=Integer.parseInt(PipeInp.readLine());
                			}
                			else{ProgCountR++;}
                				break;
                		case 22:
                			ProgCountR++;
                			if(AcclmtR!=0)
                			{
                					prntWrtr.println(ProgCountR);   
                            		prntWrtr.flush();
                            		ProgCountR=Integer.parseInt(PipeInp.readLine());
                			}
                			else{ProgCountR++;}
                				break;
                		case 23:
                			ProgCountR++;
                        	if(StckPntr>500)//Checking stack
                        	{	StckPntr--;
                            	prntWrtr.println(StckPntr+" "+(++ProgCountR));   
                            	prntWrtr.flush();
                            	prntWrtr.println(--ProgCountR);   
                            	prntWrtr.flush();
                            	ProgCountR=Integer.parseInt(PipeInp.readLine());
                        	}
                        	else{System.out.println("StAcclmtRk for user is full");memProc.destroy();System.exit(1);}
                   				break;
                		case 24:
                			prntWrtr.println(StckPntr);   
                           	prntWrtr.flush();
                           	ProgCountR=Integer.parseInt(PipeInp.readLine());
                           	StckPntr++;
                				break;
                		case 25:
                			X++;
                			ProgCountR++;
                				break;
                		case 26:
                			X--;
                			ProgCountR++;
                				break;
                		case 27:
                			if(StckPntr>500) // checking if stack is full
                        	{	StckPntr--;
                           		prntWrtr.println(StckPntr+" "+AcclmtR);
                           		prntWrtr.flush();
                           		ProgCountR++;
                        	}
                        	else{System.out.println("stAcclmtRk is full");memProc.destroy();System.exit(1);}
                				break;
                		case 28:
                			prntWrtr.println(StckPntr);   
                			prntWrtr.flush();
                           	AcclmtR=Integer.parseInt(PipeInp.readLine());
                           	StckPntr++;
                           	ProgCountR++;
                				break;
                		case 29://INTERRUPT HANDLING
                				
                			//AND STORE SYSTEM STATE	
                			if(IntrptFlag==true)
                			{
                				prntWrtr.println("1999 "+StckPntr);
                    			prntWrtr.println("1998 "+(++ProgCountR));
                    			prntWrtr.println("1997 "+AcclmtR);
                    			prntWrtr.println("1996 "+X);
                    			prntWrtr.println("1995 "+Y);
                    			ProgCountR=1500;
                    			StckPntr=1995;
                    			IntrptFlag=false;
                			}
                			else{ProgCountR++;}
                				break;
                		case 30:// RESTORE STATE
                			IntrptFlag=true;
                			if(StckPntr<2000)
                			{
                				prntWrtr.println(StckPntr);   
                           		prntWrtr.flush();
                           		Y=Integer.parseInt(PipeInp.readLine());
                           		StckPntr++;
                           		prntWrtr.println(StckPntr);   
                           		prntWrtr.flush();
                           		X=Integer.parseInt(PipeInp.readLine());
                           		StckPntr++;
                           		prntWrtr.println(StckPntr);   
                           		prntWrtr.flush();
                           		AcclmtR=Integer.parseInt(PipeInp.readLine());
                           		StckPntr++;
                				prntWrtr.println(StckPntr);   
                           		prntWrtr.flush();
                           		ProgCountR=Integer.parseInt(PipeInp.readLine());
                           		StckPntr++;
                           		prntWrtr.println(StckPntr);   
                           		prntWrtr.flush();
                           		StckPntr=Integer.parseInt(PipeInp.readLine());
                			}
                			else{System.out.println("StAcclmtRk is Empty");memProc.destroy();System.exit(1);}
                				break;
                		case 50:                				
                			prntWrtr.println("end");
                			System.exit(0);                				
                			}
            		Timer++;// Increment Timer and call timer function
            		timerFunc(Timer, s);            		
            }            
		}
		catch(Exception e){System.exit(0);}
	}
	
	/**
	 * Implementing timer logic
	 * it checks if timer logic for interrupt can be implemented
	 * @param tim:  the timer
	 * @param s its the string args[1] from the main function
	 */
	
	public static void timerFunc(int tim, String s)
	{
		if(tim==Integer.parseInt(s)&& IntrptFlag == true)
		{	
			prntWrtr.println("1999 "+StckPntr);
			prntWrtr.println("1998 "+(ProgCountR));
			prntWrtr.println("1997 "+AcclmtR);
			prntWrtr.println("1996 "+X);
			prntWrtr.println("1995 "+Y);
			ProgCountR=1000;
			StckPntr=1995;
			IntrptFlag=false;
			Timer=0;
		}		
	}
}
