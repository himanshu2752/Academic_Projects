import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Memory {
	static int[] memory=new int[2000]; //allocating memory 
	static BufferedReader inpInst=null;
	static BufferedReader inpFile=null;
	static String cmd=null;
	/**
	 * Function to take and check input file
	 * String s as input which is the file name eg Sample1.txt
	 * This function calls another read function which reads the instructions of the file and initialize memory
	 * @param s
	 */
	
	public static void Initialize(String s)
	{
		try 
		{
			FileReader k = new FileReader(s);
			inpFile = new BufferedReader(k); //read user program or instructions
		}
		catch (FileNotFoundException e) {System.out.println("File reading error");
		System.exit(1);}
		
		Read(inpFile);
	}
	
	/**
	 * takes input as file which is input
	 * read the file line by line and handle exceptions for spaces
	 * allocate/initialize memory with user program
	 * @param br
	 */
	public static void Read(BufferedReader br)
	{
		try
		{
			String line=null;
			String chars[];
			int i=0;
			while((line= br.readLine()) != null)
			{
				if (line.isEmpty())
					continue;
				else
				{
				try
				{
					chars = line.split(" ");
					if (chars[0].isEmpty())
						continue;
					else
						memory[i++]=Integer.parseInt(chars[0]); //inputing in memory
				}
				catch(NumberFormatException e)
				{
					i=Integer.parseInt(line.substring(1)); //handling '.' case 
				}
				}
			}
		} 
		catch (IOException e) {System.out.println("Error in memory initiation");System.exit(1);}
		
	}
	
	/**
	 * This function interacts with Processor and handle pipe
	 */
	public static void fetch()
	{
		inpInst=new BufferedReader(new InputStreamReader(System.in)); //pipe reader
		while(true)
		{
			//reading from pipe
			try{cmd=inpInst.readLine();}
			catch (IOException e) {System.out.println("Error in memory read");}
			if(cmd.equals("end")) 
				System.exit(0);
			if(cmd!=null)
			{
				try
				{
					System.out.println(memory[Integer.parseInt(cmd)]);	//write in pipe	(fetch instruction)					
				}
				catch(NumberFormatException e)
				{
					memory[Integer.parseInt(cmd.split(" ")[0])]=Integer.parseInt(cmd.split(" ")[1]); //store instruction
				}
			}
			else
			{
				System.exit(0);
			}
		}
	}
	
	/**
	 * Main function 
	 * it is calling all the memory functions
	 * @param args_1
	 */
	public static void main(String[] args_1) 
	{
		Initialize(args_1[0]);
			fetch();
		//FETCHING INSTRUCTION START
		
		/*inpInst=new BufferedReader(new InputStreamReader(System.in)); //pipe reader
		while(true)
		{
			try{cmd=inpInst.readLine();} //try reading program counter from pipe
			catch (IOException e) {System.out.println("Error in memory read");}
			if(cmd.equals("end")) //execute instruction no. 50 to destroy itself
				System.exit(0);
			if(cmd!=null)
			{
				try
				{
					System.out.println(memory[Integer.parseInt(cmd)]);	//write in pipe	(fetch instruction)					
				}
				catch(NumberFormatException e)
				{
					memory[Integer.parseInt(cmd.split(" ")[0])]=Integer.parseInt(cmd.split(" ")[1]); //store instruction
				}
			}
		}*/
		
		//FETCHING INSTRUCTION END
	}
}
