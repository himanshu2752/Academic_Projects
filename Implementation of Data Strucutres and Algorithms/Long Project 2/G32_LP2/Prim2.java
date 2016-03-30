import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
/**
 * Prim2 Class
 * @author himanshu and Satwant
 * Group G32
 * Implementation of Prim algorithm using Indexed Heap
 */
public class Prim2 {
	private static final int Infinity = Integer.MAX_VALUE;
	List<Edge> listEdges = new ArrayList<>();
	long wMST ;
	/**
	 * helper function to initialize vertices of the graph g
	 * @param g - graph
	 * @param s - source vertex
	 */
	 void Initialize (Graph g , Vertex s)
		{
	
			for (Vertex x : g){
				x.distance = Infinity ;
				x.parent = null ;
				x.seen = false ;
			}
			s.distance = 0 ;
			wMST = 0;
	
		}
	 /**
	  * 
	  * Comparator class of type Vertex
	  *
	  */
	 public class Comp implements Comparator<Vertex>{
		 /**
		  * Override compare function
		  */
		 public int compare(Vertex u, Vertex v)
		 {
			
			 return u.distance - v.distance ; 
		 }
	 }
	 /**
	  * Prim algorithm using indexed heap
	  * @param g - input Graph 
	  * @return - return weight of MST
	  */
	 long Prim2IndexedHeap (Graph g)
	 {
		Vertex s = g.verts.get(1);
		Initialize(g, s);
		int count = 0 ;
		Vertex[] vArr = new Vertex[g.verts.size()] ;
		for (Vertex x : g)
			 vArr[++count] = x ;
		Comparator<Vertex> c = new Comp();
		//IndexedHeap
		IndexedHeap<Vertex> pq = new IndexedHeap<Vertex>(vArr,c);
		while (!pq.isEmpty())
		{
			Vertex u = pq.remove();
			u.seen = true ;
			wMST = wMST + u.distance ;
			for (Edge e : u.Adj)
			{
				Vertex v = e.otherEnd(u) ;
				if (!v.seen && e.Weight <= v.distance)
				{
					listEdges.add(e);
					v.distance = e.Weight;
					v.parent = u ;
					pq.decreaseKey(v);
					
				}
			}
		}
		for (Vertex v : g){
			if (!v.seen)
			{
				System.out.println("Graph is not connected");
				return -1 ;
			}
		}
		return wMST ;
		
	 }
	 /**
	  * Driver program for Prim2 
	  * @param args
	  */
	 public static void main(String args[])
	 {
		 Prim2 p = new Prim2() ;
		 Scanner sc= new Scanner(System.in);
         Graph g=Graph.readGraph(sc,false);
         System.out.println("Weight of MST = "+p.Prim2IndexedHeap(g));
	 }
}
