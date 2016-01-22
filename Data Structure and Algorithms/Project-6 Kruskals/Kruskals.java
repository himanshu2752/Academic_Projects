import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Kruskals 
{
	// Making a Edge class which has two vertices as strings and distance between them as integer ///
	public static class Edge implements Comparable<Edge>
	{
		int distance ;
		String v1 , v2 ;
		
		Edge(int d , String s1 , String s2)
		{
			this.distance = d ;
			this.v1 = s1 ;
			this.v2 = s2 ;
		}
		//Overiding the compareTo in comparable so that it can compare objects ///
		public int compareTo(Edge e)
		{
			if (this.distance < e.distance)
				return -1 ;
			else if (this.distance > e.distance)
				return 1 ;
			
			else 
				return 0 ;
		}
	}

//	int elements ;
	public void kruskal ()
	{
		ArrayList<Edge> edgelist = new ArrayList<>();
		ArrayList<String> VertexList = new ArrayList<>();
  //  	String fileToParse = "assn9_data.csv";
    	BufferedReader fileReader = null;
        int countVertex = 0;	 // Not needed since we are using ArrayList to store vertices and can get the number from its size ///
        int TotalDistance = 0;
         
        final String DELIMITER = ",";
        try
        {
            String line = "";
            
            fileReader = new BufferedReader(new FileReader("assn9_data.csv")); // Put the CSV File Name here which is to be executed //
             
           
            while ((line = fileReader.readLine()) != null)
            {
                
                String[] tokens = line.split(DELIMITER);
                VertexList.add(tokens[0]);
                	edgelist.add( new Edge(Integer.parseInt(tokens[2]),tokens[0],tokens[1] ));
                	for (int i =3 ; i<tokens.length ; i++)
                	{
                		edgelist.add(new Edge(Integer.parseInt(tokens[i+1]),tokens[ 0],tokens[ i]));
                		i++ ;
                	} 
                	countVertex ++ ;             
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }        

        // Starting Kruskals Algorithm //
		int edgesAccepted = 0 ;
		DisjSets ds = new DisjSets(VertexList.size());
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for (Edge e : edgelist)
		{
			pq.add(e);							// Adding each edge in priority queue //
		}
		
		while (edgesAccepted < VertexList.size()-1 )
		{
			Edge	e = pq.poll();				// Using polling function as a substitute to deleteMin() ///
			 
			 
			if (ds.find(VertexList.indexOf(new String(e.v1))) != ds.find(VertexList.indexOf(new String(e.v2))))
			{
				edgesAccepted ++ ;
				ds.union(ds.find(VertexList.indexOf(new String(e.v1))),ds.find(VertexList.indexOf(new String(e.v2))));
				TotalDistance = TotalDistance + e.distance ;
				System.out.println(e.v1+" , "+e.v2+", distance= "+e.distance);
			}
		
		}
		System.out.println("Total Distance ="+TotalDistance);
		

	}
    public static void main( String [ ] args )
    {
    	
    	    	Kruskals kr = new Kruskals();
    	    	kr.kruskal();   	

    }

}
