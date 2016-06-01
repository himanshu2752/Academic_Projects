import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.net.Socket;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
class ClientData{
	String name;
	boolean isKnown;
	boolean isCnnctd;
	List<String> messages ;
	public ClientData(String str) {
		name=str;
		isKnown=false;
		isCnnctd=false;
		messages= new LinkedList<>();
	}
	public boolean isCnnctd() {
		return isCnnctd;
	}
	public void setCnnctd(boolean isCnnctd) {
		this.isCnnctd = isCnnctd;
	}
	public boolean isKnown() {
		return isKnown;
	}
	public void setKnown(boolean isKnown) {
		this.isKnown = isKnown;
	}
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
public class Server 
{
   public static TreeMap<String,ClientData> userList;
   ServerSocket server = null;
   public Server() {
	   userList = new TreeMap<>();
   }
   
   public void listensocket(int port)
   {
      try
      {
	 server = new ServerSocket(port); 
	 System.out.println("Server running on port " + port + 
	                     "," + " use ctrl-C to end");
      } 
      catch (IOException e) 
      {
	 System.out.println("Error creating socket");
	 System.exit(-1);
      }
      while(true)
      {
         ClientWorker w;
         try
         {
            w = new ClientWorker(server.accept());
            Thread t = new Thread(w);
            t.start();
		 } 
		 catch (IOException e) 
		 {
		    System.out.println("Accept failed");
		    System.exit(-1);
         }
      }
   }

   protected void finalize()
   {
      try
      {
         server.close();
      } 
      catch (IOException e) 
      {
         System.out.println("Could not close socket");
         System.exit(-1);
      }
   }

   public static void main(String[] args)
   {
      if (args.length != 1)
      {
         System.out.println("Usage: java SocketThrdServer port");
	 System.exit(1);
      }

      Server server = new Server();
      int port = Integer.valueOf(args[0]);
      server.listensocket(port);
   }
}
class ClientWorker implements Runnable 
{
   private Socket client;
   
   ClientWorker(Socket client) 
   {
      this.client = client;
   }

   public void run()
   {
      String line;
	  int exit=0;
      BufferedReader in = null;
      PrintWriter out = null;
      try 
      {
	 in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	 out = new PrintWriter(client.getOutputStream(), true);
      } 
      catch (IOException e) 
      {
	 System.out.println("in or out failed");
	 System.exit(-1);
      }

      try 
      {
	 // Receive text from client
		while(exit!=7){
		line = in.readLine();
		String result="";
		   String str[] = line.split(":");
			 if(str[0].compareTo("n")==0){
				 ClientData exstngClient =Server.userList.get(str[1]);
				 if(exstngClient==null){
					 ClientData newClient = new ClientData(str[1]);
					 newClient.setKnown(true);
					 newClient.setCnnctd(true);
					 Server.userList.put(str[1], newClient);
					 System.out.println("Connection by unknown user "+str[1]);
				 }
				 else{
					 exstngClient.setCnnctd(true);
					 Server.userList.put(str[1], exstngClient);
					 System.out.println("Connection by known user "+str[1]);
				 }
			 }
			 if(str[0].compareTo("c")==0){
				exit=Integer.parseInt(str[1]);
				 switch(exit){
				 	case 1:{
				 		System.out.println(str[2]+" displays all known users.");
				 		int i=1;
				 		for(Map.Entry<String,ClientData> entry : Server.userList.entrySet()) {
				 	  		  String key = entry.getKey();
				 	  		  ClientData value = entry.getValue();
				 	  		  if(value.isKnown){
				 	  			result+=i+"."+value.name;
				 	  			  i++;
				 	  		  }
				 	  		}
				 		if(i==1){
				 			result="No known users.";
				 		}
				 		out.println(result);
						break;
				 	}
				 	case 2:{
				 		System.out.println(str[2]+" displays all connected users.");
				 		int i=1;
				 		for(Map.Entry<String,ClientData> entry : Server.userList.entrySet()) {
				 	  		  String key = entry.getKey();
				 	  		  ClientData value = entry.getValue();
				 	  		  if(value.isCnnctd){
				 	  			  result+=i+"."+value.name+"   ";
				 	  			  i++;
				 	  		  }
				 	  		}
				 		if(i==1){
				 			result="No Connected users.";
				 		}
				 		out.println(result);
						break;
				 	}
					case 3:{
							System.out.println(str[4]+" posts a message for "+str[2]);
							String key=str[2];
							Calendar cal= Calendar.getInstance();
							java.util.Date now =cal.getTime();
							String date= new SimpleDateFormat("MM/dd/yy:HH:mm:ss").format(now);
							ClientData cldt = Server.userList.get(key);
							if(cldt==null){
								result="User does not exist.";
							}
							else{
							List<String> msgs=cldt.getMessages();
							if(msgs.size()<10){
								msgs.add(" "+date+", "+str[3]);
								cldt.setMessages(msgs);
								Server.userList.put(key, cldt);
								result="Message posted to "+key;
							}
							else{
								result="Message limit exceeded";
							}
							}
							out.println(result);
						 	break;	
					}
					case 4:{
							System.out.println(str[3]+" posts messages to connected users.");
							int i=1;
							TreeMap<String,ClientData> cnnctedUsers= new TreeMap<>();
							for(Map.Entry<String,ClientData> entry : Server.userList.entrySet()) {
					 	  		  String key = entry.getKey();
					 	  		  ClientData value = entry.getValue();
					 	  		  if(value.isCnnctd){
					 	  			List<String> msgs=value.getMessages();
									if(msgs.size()<10){
										msgs.add(str[2]);
										value.setMessages(msgs);
										Server.userList.put(key, value);
										result="Message posted to "+key;
									}
									else{
										result="Message limit exceeded for "+key;
									}
					 	  			  cnnctedUsers.put(key, value);
					 	  			  i++;
					 	  		  }
					 	  	}
					 		if(i==1){
					 			result="No connected users.";
					 		}
					 		else{
					 			Server.userList.putAll(cnnctedUsers);
					 		}
					 		out.println(result);
							break;
					}
					case 5:{
						System.out.println(str[3]+" posts messages to known users.");
						int i=1;
						TreeMap<String,ClientData> knwnUsers= new TreeMap<>();
						for(Map.Entry<String,ClientData> entry : Server.userList.entrySet()) {
				 	  		  String key = entry.getKey();
				 	  		  ClientData value = entry.getValue();
				 	  		  if(value.isKnown()){
				 	  			List<String> msgs=value.getMessages();
								if(msgs.size()<10){
									msgs.add(str[2]);
									value.setMessages(msgs);
									Server.userList.put(key, value);
									result="Message posted to "+key;
								}
								else{
									result="Message limit exceeded for "+key;
								}
									knwnUsers.put(key, value);
				 	  			  i++;
				 	  		  }
				 	  	}
				 		if(i==1){
				 			result="No connected users.";
				 		}
				 		else{
				 			Server.userList.putAll(knwnUsers);
				 		}
				 		out.println(result);
						break;
					}
					case 6:{
								System.out.println(str[2]+" gets messages.");
								int i=1;
								String key=str[2];
								ClientData cldt = Server.userList.get(key);
								List<String> msgs=cldt.getMessages();
								for(String m:msgs){
									 result+=i+". From "+key+m+"   ";
					 	  			 i++;
								}
								cldt.setMessages(new LinkedList<>());
								Server.userList.put(key, cldt);
								out.println(result);
								break;
					}
					case 7:{
						System.out.println(str[2]+" exists");
							String key=str[2];
							ClientData cldt =Server.userList.get(key);
							cldt.setCnnctd(false);
							Server.userList.put(key, cldt);
							out.println(result);
							break;
					}
					default :{
						out.println("Wrong Menu Choice");
					}
				 }
			 }
		}
	  } 
      catch (IOException e) 
      {
			 System.out.println("Read failed");
			 System.exit(-1);
      }
      
      try 
      {
    	  client.close();
      } 
      catch (IOException e) 
      {
		 System.out.println("Close failed");
		 System.exit(-1);
      }
   }
}
