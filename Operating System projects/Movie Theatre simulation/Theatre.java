import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
/**
 * 
 * @author himanshu
 *	Simulation of Movie Theatre by threading and using semaphores
 *	csw = Concession stand worker
 *	tt = ticket taker
 */
public class Theatre {
	
	/**
	 * 
	 * @author himanshu
	 * Class for movies having seats and movie name
	 */
	public class movies{
		int seats;
		String movie;
		public movies(String movi, int numSeat)
		{
			this.movie = movi;
			this.seats = numSeat;
			// TODO Auto-generated constructor stub
		}		
	}
	// Array list of movies (having number of seats and movie name)
	static ArrayList<movies> movieList ;
	
	/**
	 * Change below attributes to change number of customers and number of Box office agents
	 */
	private static final int noCustomers = 50;
	private static final int noAgents = 2;
	
	
	Theatre[] finished ; // Semaphore array for all customers
	
	/**
	 * Queues for agent, concession worker and ticket taker
	 */
	public Queue<String> agentQ = new LinkedList<>();
	public Queue<String> cswQ = new LinkedList<>();
	public Queue<Integer> ttQ = new LinkedList<>();
	
	// Flag to show if available
	 boolean availblMsg ;
	 
	/**
	 *  Semaphore for each customer.
	 * Agents and Concession workers use this to tell they have finished with the customer
	 */ 
	Semaphore cusServ ;
	
  /**
   * Semaphores for mutual exclusion for boa, csw and tt
   */
	Semaphore agentQmutex = new Semaphore(1);
	Semaphore ttQmutex = new Semaphore(1);
	Semaphore cswQmutex = new Semaphore(1);
	Semaphore moviesMutex = new Semaphore(1);// mutual exclusion for movies and available seat
	
	
	/**
	 * other semaphores to check for customers in ready queue
	 */	
	Semaphore agentCheck = new Semaphore(0);
	Semaphore cswCheck = new Semaphore(0);
	Semaphore ttCheck = new Semaphore(0);
	
	/*
	 * constructor for theatre class
	 */
	public Theatre(Semaphore serv, boolean msg)
	{
		this.availblMsg = msg ;
		this.cusServ = serv;
	}
	
	/*public Theatre(String mov, int seat) {
		movies m = new movies(mov,seat);
		// TODO Auto-generated constructor stub
	}*/
	
	/*
	 * method to add movies to movieList when we read file
	 */
	public void addMovies(String mov, int seat){
		movies m = new movies(mov,seat);
		movieList.add(m);
	}
	public Theatre(){
		
	}

	
	
	/**
	 * 
	 * @author himanshu
	 * Customer class
	 */
	public class customer extends Thread{
		int cusId ;
		int cusMovieId;		
		String snack;
		
		public customer(int id) {
			this.cusId = id;
			
		}
		
		Random rnd = new Random(); // random generator
		
		/**
		 * method to randomly choose movies
		 */
		private void chooseMovie()
		{
			// to generate random movieId
			cusMovieId = rnd.nextInt(movieList.size());
			System.out.println("Customer "+cusId+" created,"+" buying ticket to "+movieList.get(cusMovieId).movie);
			
		}
		
		// run method
		public void run()
		{
			try {
				chooseMovie();
				agentQmutex.acquire();
				// adding customer to agent queue
				agentQ.add(cusId+","+cusMovieId);
				// after checking that customer is available signaling agent
				agentCheck.release();
				agentQmutex.release();
				finished[cusId].cusServ.acquire();
				
				// first check if movie is available
				if (finished[cusId].availblMsg == true)
				{
					ttQmutex.acquire();
					//queue to ticket taker
					ttQ.add(cusId);
					System.out.println("Customer "+cusId+" in line to see ticket taker");
					// customer is available for ticket taker
					ttCheck.release();
					ttQmutex.release();
					finished[cusId].cusServ.acquire();
					
					// after randomly checking whether to go to concession worker or not go to concession stand worker
					if (cswVisit()== true)
					{
						// randomly select the snacks
						int selectSnack = rnd.nextInt(3);
						if (selectSnack == 0)
							snack = " SOda";
						else if (selectSnack == 1)
							snack = "PopCorn";
						else
							snack = "Popcorn and Soda";
						
						cswQmutex.acquire();
						cswQ.add(cusId+","+snack);
						System.out.println("Customer "+cusId+" in line to buy "+snack);
						cswCheck.release();
						cswQmutex.release();
						finished[cusId].cusServ.acquire();
					}
					System.out.println("Customer " + cusId+ " enters theatre to see " + movieList.get(cusMovieId).movie);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				// handle exception
			}
		}
		
		// chance to visit to concession stand would be 50 percent
		private boolean cswVisit(){
			int t = rnd.nextInt(2);
			if ( t== 0)
				return false;
			else
				return true;
		}
	}
	
	// Ticket Taker Class
	
	public class TicketCollector extends Thread{
		
	private	int custId;
		
		public TicketCollector() {
			
			//  Auto-generated constructor stub
			System.out.println(" Ticket taker created");
		}
		
		public void run(){
			for (;;)
			{
				try {
					ttCheck.acquire();
					ttQmutex.acquire();
					custId = ttQ.remove();
					ttQmutex.release();
					try{
						sleep(250);
					}
					catch(Exception e){	e.printStackTrace();					
					}
					
					System.out.println(" Ticket taken from customer"+custId);
					finished[custId].cusServ.release();
					
				} catch (Exception e) {
				
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 
	 * @author himanshu
	 * CLass for box office agent
	 */
	public class BoxOfficeAgent extends Thread{
		
		int agentId ;
		int cusId,movieID;
		
		String Customer;
		public BoxOfficeAgent(int Id) {
			this.agentId = Id;
			System.out.println("Box Office agent "+agentId+" created");
			
		}
		
		boolean avlbleFlag ;
		
		public void run(){
			for(;;)
			{
				try {
					// checking queue
					agentCheck.acquire();
					agentQmutex.acquire();
					//get first customer now
					Customer = agentQ.remove();
					cusId = Integer.parseInt(Customer.split(",")[0]);
					movieID = Integer.parseInt(Customer.split(",")[1]);
					System.out.println("Box office agent "+agentId+" serving customer "+cusId);
					agentQmutex.release();
					moviesMutex.acquire();
					checkAvailbility();
					moviesMutex.release();
					try{
						sleep(1500);
					}
					catch(Exception e){e.printStackTrace();}
					
					if(finished[cusId].availblMsg == true)
					{
						System.out.println("Box office agent "+agentId+" sold ticket for "+movieList.get(movieID).movie+" to customer "+cusId);
					}
					finished[cusId].cusServ.release();
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		private void checkAvailbility(){
			
			if (movieList.get(movieID).seats>0)
			{
				movieList.get(movieID).seats -- ;
				finished[cusId].availblMsg = true;
			}
			else
				finished[cusId].availblMsg = false;
		}
	}
	/**
	 * 
	 * @author himanshu
	 * class for concession stand worker
	 */
	public class ConcessionWorker extends Thread{
		String Snacks,Customer;
		int cusID;
		public ConcessionWorker()
		{
			System.out.println("Concession stand worker created");
		}
		
		public void run(){
			for(;;)
			{
				try {
					// check queue
					cswCheck.acquire();
					cswQmutex.acquire();
					//get next customer
					Customer = cswQ.remove();
					cswQmutex.release();
					cusID=Integer.parseInt(Customer.split(",")[0]);
					Snacks =Customer.split(",")[1];
					System.out.println("Order for "+Snacks+" taken from customer "+cusID);
					
					try{
						sleep(3000);
					}
					catch(Exception e){e.printStackTrace();}
					
					System.out.println(Snacks+" given to customer "+ cusID);
					
					finished[cusID].cusServ.release();
					} catch (Exception e) {
						e.printStackTrace();
					 
				}
			}
		}
	}
	
	public static void main(String[] args){
		
		Theatre th = new Theatre();
		BufferedReader readMovie=null,readFile=null ;
		Theatre.movieList=new ArrayList<movies>();
		
		try{readFile = new BufferedReader(new FileReader(args[0]));}
		catch (FileNotFoundException e) {System.exit(1);}
		try
		{
			String line=null;
			while((line= readFile.readLine()) != null)
			{
				try	{th.addMovies(line.split("\t")[0],Integer.parseInt(line.split("\t")[1]));}
				catch(NumberFormatException e){System.out.println("File not correct");}
			}
		} 
		catch (IOException e) {
			System.out.println("File can not be read");
			System.exit(1);
			}
		
	
		/**
		 * box office agent threads
		 */
		for(int i=0;i<noAgents;i++)
    	{
    		BoxOfficeAgent boa=th.new BoxOfficeAgent(i);
    		boa.start();
    	}
		
		
		
		
		/**
		 * Ticket taker thread
		 */
		TicketCollector tt=th.new TicketCollector();
		tt.start();
		
		
		/**
		 * Concession stand worker thread
		 */
		ConcessionWorker csw=th.new ConcessionWorker();
		csw.start();
		
		
		/**
		 * Customer threads
		 */
		Thread[] custThread=new Thread[noCustomers];
		th.finished=new Theatre[noCustomers];
		for(int i=0;i<noCustomers;i++)
		{                                                                                                            
			custThread[i]=th.new customer(i);
			th.finished[i]=new Theatre(new Semaphore(0),false);
			custThread[i].start();
		}
		
		
		/**
		 * joining threads
		 */
		for(int i=0;i<noCustomers;i++)
    	{
    		try {
				custThread[i].join();
				System.out.println("Joined customer "+i);
			} 
    		catch (InterruptedException e) {e.printStackTrace();}
    	}

		//Exit when all threads join
		
    	System.exit(0);
	}

}
