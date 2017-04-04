import java.util.Scanner;

public class solution {

	public static int convert(String s)
	{
		int value = 0 ;
		for (int i = 0; i < s.length(); i++) 
		{
		    if (s.charAt(i) <= ' ') 
		    {	        }
		    else
		    {
		    	value = value * 10 + s.charAt(i) - '0';
		    }
		 }
		return value ;
	}
	public static void main(String[] args)
	{
		
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Enter a number: ");
		String n = reader.nextLine(); // Scans the next token of the input as an int.
		int result = convert(n);
		System.out.println("Integer : " +result);
	}
}
