import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

	Socket sck = null ;
	BufferedReader read = null ;
	PrintWriter write = null ;
	
	public void communicate(){
		
		Scanner s = new Scanner(System.in);
		System.out.println(" Please enter your name ");
		String name = s.nextLine();
		
		// Send data to socket
		write.println("n:"+name);
		while(true){
		System.out.println("1.	Display the names of all known users.");
		System.out.println("2.	Display the names of all currently connected users");
		System.out.println("3.	Send a text message to a particular user.");
		System.out.println("4.	Send a text message to all currently connected users.");
		System.out.println("5.	Send a text message to all known users.");
		System.out.println("6.	Get my messages.");
		System.out.print("7.	Exit.");
		
		System.out.println("Enter your choice:");
		String choice = s.nextLine();
		
		int ch = Integer.parseInt(choice);
		if(ch == 3)
		{
			System.out.println("Enter recipient's name");
			String s1 = s.nextLine();
			System.out.println("Enter a message");
			String s2 = s.nextLine();
			write.flush();
			write.println("c:"+ch+":"+s1+":"+s2+":"+name);
			
		}
		else if (ch == 4 || ch == 5)
		{
			System.out.println("Enter a message");
			String s3 = s.nextLine();
			write.println("c:"+ch+":"+s3+":"+name);
		}
		
		else if (ch ==6)
		{
			write.println("c:"+ch+":"+name);
		}
		else if (ch == 7)
		{
			write.println("c:"+ch+":"+name);
			break;
		}
		else if (ch ==1 || ch ==2)
		{
			write.println("c:"+ch+":"+name);
			
		}
		else {
			System.out.println(ch+" is not a valid choice");
			continue;
			}
		try {
			String servMsg = read.readLine();
			System.out.println(servMsg);
		} catch (IOException e) {
			System.out.println("Read failed");
			e.printStackTrace();
			System.exit(1);
		}
		
		}
	}
	public void socket(String host, int port) throws IOException{
		try {
			sck = new Socket(host,port);
			write = new PrintWriter(sck.getOutputStream(),true);
			read = new BufferedReader(new InputStreamReader(sck.getInputStream()));
		} catch (UnknownHostException e) {
			System.out.println("Host not found");
			System.exit(1);
		
		}
		catch(IOException e){System.out.println("no I/O");System.exit(1);}
	}
	
	public static void main(String[] args) throws IOException{
		
		if(args.length ==2)
		{
			Client clientSockt = new Client();
			String host = args[0];
			int port = Integer.parseInt(args[1]);
			clientSockt.socket(host, port);
			clientSockt.communicate();
		}
		else 
		{
			System.out.println("Enter hostname and port, Usage:  client hostname port");
			 System.exit(1);
		}
	}
}
