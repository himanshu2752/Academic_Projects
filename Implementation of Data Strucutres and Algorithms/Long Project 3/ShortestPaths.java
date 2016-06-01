import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
/**
 * 
 * @author himanshu and Satwant
 * @group G32
 * class to implement Long project 3 - to find shortest path by BFS, DAG, Dijkstra's and Bellman-Ford algorithm
 */
public class ShortestPaths {
	public static final int Infinity=Integer.MAX_VALUE;
	String algoType;
	boolean negCycle;
	public ShortestPaths() {
		algoType="";
		negCycle=true;
	}
	
	/**
	 * Method to decide the method for shortest path implementation
	 * @param g input graph by read
	 * @param s source vertex
	 * @return boolean if negative cycle present
	 */
	public boolean fingShrtstPaths(Graph g, Vertex s){
		List<Vertex> topOrdrlst = new LinkedList<>();
		Graph tmp = g;
		topOrdrlst=toplogicalOrder(tmp);
		if(g.hasUniformEdgeWeights()){
			BFS(g, s);
			algoType="BFS";
			negCycle=true;
		}
		else if(topOrdrlst.size()==g.verts.size()-1){
			shrtstPathDAG(g, s,topOrdrlst);
			algoType="DAG";
		}
		else if(!g.hasNegativeWeights()){
			dijkstra(g, s);
			algoType="Dij";
		}
		else{
			negCycle=shortestPathBellmanFord(g, s);
			algoType="B-F";
		}
		return negCycle;
	}
	
	/**
	 * Helper function to initialize values
	 * @param g input graph
	 * @param s source vertex
	 */
	public void Initialize(Graph g,Vertex s){
    	for(Vertex v:g){
    		v.distance=Infinity;
    		v.parent=null;
    		v.seen=false;
    		v.count=0;
    	}
    	s.distance=0;
    }
	
	/**
	 * Breadth first search 
	 * @param g
	 * @param s
	 */
	void BFS(Graph g, Vertex s)
	{
		Queue<Vertex> q = new LinkedList<>();
        Vertex current = s ;
        if (g.numNodes == 0){
       	 System.out.println("Input is not a Graph");
        }
        else
        {
        	Initialize(g, s);
          q.add(s);
			
          while (!q.isEmpty())
          {
            current = q.poll();
            current.seen = true;
            if (current.distance != Infinity)
	//    		wMST = wMST+current.distance;
            
            for (Vertex v : current.AdjV)
            {
                 if (v.distance == Infinity)
                 {
                   v.distance = current.distance +g.getuniformWeight() ;
                   v.parent = current;
                   q.add(v);
                 }
            }
          }                                       
		 }
        
	}
	/**
     * Method for ordering the nodes of the graph topologically using DFS 
     * @param Graph g
     * @return Stack<Vertex> or null if graph not DAG
     */
    public static List<Vertex> toplogicalOrder(Graph g) {
    	boolean isGraphDAG=true;
    	ArrayDeque<Vertex> stack = new ArrayDeque<>();
    	List<Vertex> lst = new ArrayList<>();
    	for(Vertex u:g)
    	{
    		if(u.degree==0)
    		{
    			stack.add(u);
    		}
    			
    	}
    	while(!stack.isEmpty())
    	{
    		Vertex v= stack.remove();
    		lst.add(v);
    		Vertex f;
    		for(Edge e:v.Adj)
    		{
    			f=e.otherEnd(v);
    			f.degree--;	//reducing degree of the vertices on other end.
    			if(f.degree==0)
        			stack.add(f);
    		}
    		
    	}
    	/*for(Vertex v:g){
    		if(v.degree!=0){
    			isGraphDAG=false;
    			break;
    		}
	    }*/
    	/*if(!isGraphDAG)
    		System.out.println("Graph has cycle");
    	System.out.println(lst);*/
		return lst;
       
     }
    
    /**
     * Helper function to relax nodes
     * @param u  vertex/node
     * @param v  vertex/node
     * @param e edge
     * @return relaxed flag
     */
     public boolean relax(Vertex u,Vertex v,Edge e){
		boolean flag=false;
		if(v.distance>(u.distance+e.Weight) && u.distance!=Infinity){
			v.distance=u.distance+e.Weight;
			v.parent=u;
			flag=true; 
		}
		return flag;
	 }
     
     /**
      * 
      * @param g input graph
      * @param s source vertex
      * @param lst list of vertices in topological order
      */
     public void shrtstPathDAG(Graph g, Vertex s, List<Vertex> lst){
 		List<Vertex> topSortOrder= lst;
 		Initialize(g,s);
 		for(Vertex u:topSortOrder){
 			for(Edge e:u.Adj){
 				relax(u,e.otherEnd(u),e);
 			}
 		}
 	}
     
     /**
      * comparator class
      */
     public class Comp implements Comparator<Vertex>{
    	 
    	 /**
    	  * override compare method
    	  */
		 public int compare(Vertex u, Vertex v)
		 {
			return u.distance - v.distance ; 
		 }
	 }
     
     /**
      * Dijkstra's implementation for shortest path
      * @param g input graph
      * @param s source vertex
      */
     void dijkstra(Graph g, Vertex s )
 	{
 		long dist = 0;
 		
 		Initialize(g, s); 	    
 	    
 	    int count = 0 ;
 	    Vertex[] vArr = new Vertex[g.verts.size()] ;
 		 for (Vertex x : g)
 		 {
 			 if (x == null){continue;}
 			 vArr[++count] = x ;
 		 }
 		 
 		 Comparator<Vertex> c = new Comp();
 	    
 	    IndexedHeap<Vertex> Heap = new IndexedHeap<>(vArr, c);
 	    
 	    while (!Heap.isEmpty())
 	    {
 	    	Vertex u = Heap.remove();
 	    	u.seen = true ;
 	    	if (u.distance != Infinity)
 	    		dist = dist+u.distance;
 	    	
 	    	for (Edge e : u.Adj)
 	    	{
 	    		Vertex v = e.otherEnd(u);
 	    		if ( v.distance> u.distance + e.Weight && !v.seen)
 	    		{
 	    			v.distance = u.distance + e.Weight;
 	    			v.parent = u ;
 	    			Heap.decreaseKey(v);
 	    		}
 	    	}
 	    }
 	         
 	}
     public static int sumOfShrtstPaths(Graph g)
 	{
 		int sum=0;
 		for(Vertex v:g){
 			if(v.distance==Infinity)
 				continue;
 			sum+=v.distance;
 		}
 		return sum;
 	}
 	public void printResult(Graph g,boolean flag){
 		if(flag){
 			System.out.println(algoType+" "+sumOfShrtstPaths(g));
 			if(g.verts.size()<=100){
 				for(Vertex v:g){
 					if(v.distance==Infinity)
 						System.out.println(v+" INF -"+" ");
 					else{
 						if(v.parent!=null)
 							System.out.println(v+" "+v.distance+" "+v.parent+" ");
 						else
 							System.out.println(v+" "+v.distance+" -"+" ");
 					}
 				}
 			}
 		}
 		else{
 			System.out.println("Unable to solve problem. Graph has a negative cycle.");
 		//	findCycle(g, g.verts.get(1));
 		}
 	}
 	
 	/**
 	 * Bellman-Ford algorithm implementation for shortest path
 	 * @param g input graph
 	 * @param s source vertex
 	 * @return boolean if negative cycle exists
 	 */
 	public boolean shortestPathBellmanFord(Graph g, Vertex s){
		Queue<Vertex> vertices = new LinkedList<>();
		Initialize(g, s);
		s.seen=true;
		vertices.add(s);
		
		while(!vertices.isEmpty()){
			Vertex u=vertices.remove();
		
			u.seen=false;
			u.count+=1;
		
			if(u.count>g.verts.size()-1)
				return false;
			for(Edge e:u.Adj){
				Vertex v=e.otherEnd(u);
				if(v.distance>(u.distance+e.Weight)){
					v.distance=u.distance+e.Weight;
					v.parent=u;
					if(!vertices.contains(v)){
						vertices.add(v);
						v.seen=true;
					}
				}
			}
			
		}
				return true;
	}
 	
 	/**
 	 * Driver for Long project 3
 	 * @param args
 	 * @throws FileNotFoundException 
 	 */
 	public static void main(String args[]) throws FileNotFoundException{
 		ShortestPaths shPaths = new ShortestPaths();
 	//	Scanner sc = new Scanner(System.in);
 		Scanner in;
 		if(args.length > 0) {
 		    in = new Scanner(new File(args[0]));
 	        } else {
 		    in = new Scanner(System.in);
 		}
 		Graph g=Graph.readGraph(in, true);
 		boolean flag=shPaths.fingShrtstPaths(g, g.verts.get(1));
 		shPaths.printResult(g, flag);
 		
 	}

}
