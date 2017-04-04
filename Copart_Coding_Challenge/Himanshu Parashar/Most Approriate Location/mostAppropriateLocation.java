
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class mostAppropriateLocation {	
	
	 
	 public static void main(String args[]) throws Exception {
		 String cusID = "" ;
		 int zipCode = 0 ;
			
			// Reading Customer FIle and getting its ID and ZIP Code
			 BufferedReader in = new BufferedReader(new FileReader("Customer.txt"));
			 String str ;
			 ArrayList<String> list = new ArrayList<>();
			 while((str = in.readLine()) != null)
			 {
				 list.add(str);			    		   
			 }
			 zipCode = Integer.parseInt(list.get(0));
			 cusID = list.get(1);			 
		
		 Connection conn = null;
		 Statement stmt = null;
		 ResultSet rs = null;
		 // JDBC connection for database where we have table for customer
		 conn = DriverManager.getConnection("jdbc:oracle:thin:@onecore.myds.me:1521:xe", "hxp151330", "hxp151330");
		 
		   
		    stmt = conn.createStatement();
		    String Query1 = "select Zip from copart_cust where CustomerID = '" +cusID +"'" ;
		    rs = stmt.executeQuery(Query1);
		    rs.next();
		    String s = rs.getString("Zip");
		    int UserZip = Integer.parseInt(s);
		    System.out.println(s);
		    String Query2 = "select distinct(zip),address from Copart_L";
		    rs = stmt.executeQuery(Query2);
		    HashMap<Integer, String> hs = new HashMap<>();
		    rs.next();
		    while (rs.next())
		    {
		    	String zips = rs.getString("Zip") ;
		    	if (zips != null)
		    	{
		    	Pattern p = Pattern.compile("[^0-9]");
		    	Matcher m = p.matcher(zips);
		    	Boolean b = m.find();
		    	if (!b)
		    		{
		    			int zip = Integer.parseInt(zips);		    	
		    			String add = rs.getString("address");
		    			hs.put(zip, add);		    	
		    		}		   
		    	}
		    }		   
		
		    int min = Integer.MAX_VALUE ;
		    int key = 0 ;
		     for (Integer x : hs.keySet())
		     {	    	 
		    	int temp = Math.abs(UserZip - x );
		    	 if (min >= temp  )
		    	 {
		    		 key = x ;
		    		 min = temp ;
		    	 }
		     }

		     System.out.println("For customer "+ cusID + " with zip code: " + zipCode+ ", closest copart facility is " + hs.get(key) + ", and zip is :" + key );
		     
		    rs.close();
		    stmt.close();
		    conn.close();
		  }
}