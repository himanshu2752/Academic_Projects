import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class NumShortestPaths {
	
	List<Edge> listEdges = new ArrayList<>();
	long ShortDist  ;

	private static final int Infinity = Integer.MAX_VALUE;
	
	void Initialize (Graph g , Vertex s)
	{
		 //IndexedHeap<Vertex> pq = new IndexedHeap<>(g.verts.size(),Comparator c);
		for (Vertex x : g){
			x.distance = Infinity ;
			x.parent = null ;
			x.seen = false ;
		}
		s.distance = 0 ;
		ShortDist = 0;
	//	Initialize(s);
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
	public static void printResult(Graph g,boolean flag){
		if(flag){
			System.out.println("B-F "+sumOfShrtstPaths(g));
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
		else
			System.out.println("Non-positive cycle in graph. DAC is not applicable");
	}
	
	public class Comp implements Comparator<Vertex>{
		 public int compare(Vertex u, Vertex v)
		 {
			/* if (u.distance > v.distance)
				 return -1 ;
			 else
				 return +1 ;*/
			 return u.distance - v.distance ; 
		 }
	 }
	void dijkstra(Graph g, Vertex s )
	{
		long dist = 0;
		
		Initialize(g, s);
	    /*for ( Vertex v : g)
	    {  v.distance = Infinity; v.seen = false; }
	    
	    s.distance = 0;*/  // start source vertex at distance zero
	    
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
	    /*System.out.println("Dij "+dist);
	    if (g.verts.size() <= 100)
	    {
	    for (Vertex u : g)
	    {
	    	if (u.distance != Infinity)
	    	{
	    		System.out.println(u+" "+ u.distance+" "+u.parent);
	    	}
	    	else 
	    		System.out.println(u+" INF");
	    	
	    }
	    }*/
	         
	}
	
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
          /*for (Vertex i : g )
          {                                       
                i.distance = Infinity ;
                i.parent = null ;
          }
          r.distance = 0;*/
          q.add(s);
			
          while (!q.isEmpty())
          {
            current = q.poll();
            current.seen = true;
            if (current.distance != Infinity)
            	ShortDist = ShortDist+current.distance;
            
            for (Vertex v : current.AdjV)
            {
                 if (v.distance == Infinity)
                 {
                   v.distance = current.distance +1 ;
                   v.parent = current;
                   q.add(v);
                 }
            }
          }                                       
		 }
        
        for (Vertex i : g)
        {
          if (i != null &&i.distance == Infinity)
          {
            System.out.println("Graph is not connected");
          }
        }
        
        /*System.out.println("Bfs "+ShortDist);
        if (g.verts.size() <= 100)
        {
	    for (Vertex u : g)
	    {
	    	if (u.distance != Infinity)
	    	{
	    		System.out.println(u+" "+ u.distance+" "+u.parent);
	    	}
	    	else 
	    		System.out.println(u+" INF");
	    	
	    }
        }*/
        /*Iterator<Vertex> it1 = g.iterator();
        Vertex v1 = it1.next();
        vBfs2 = v1 ;
        while (it1.hasNext())
        {
	           if (v1.distance >= vBfs2.distance)
	                         vBfs2 = v1 ;
	           v1 = it1.next();
        }*/
	}
	
	
	///////////////////////////////////////////////////////////////////////////////
	public void shortestPath(Graph g)
	{
		Graph d = new Graph(g.verts.size());
		dijkstra(g, g.verts.get(1));
		for (Vertex v : g)
		{
			for (Edge e : v.Adj)
			{
				Vertex u = e.otherEnd(v);
				if (v.distance + e.Weight == u.distance)
					d.addDirectedEdge(v.name,u.name , e.Weight);
			}
		}
		
		// For debugging
		/*for (Vertex v : d)
		{
			System.out.println(v+" "+v.degree+" "+v.Adj);
		}*/
		dijkstra(d, d.verts.get(1));
		Vertex s = d.verts.get(1);
		Graph t = d ;
		if (!toplogicalOrder(t))
		{
			System.out.println("Graph has cycle, No solution");
			return ;
		}
		else			
		{
			Vertex r = d.verts.get(1);
			r.NumShortPath = 1 ;
			/*for (Vertex x : d)
			{
				for (Edge e : x.Adj)
				{
					Vertex v = e.otherEnd(x);
					if (!v.seen)
					{
						v.NumShortPath = x.NumShortPath ;
						v.seen = true ;
					}
					else
						v.NumShortPath += x.NumShortPath ;
						
				}
			}*/
		Queue<Vertex> qv = new LinkedList<>();
		for (Vertex x : d)
		{
		//	Vertex tmp = x.parent;
			if (x.parent != null)
			qv.add(x.parent);
			while (!qv.isEmpty())
			{
				Vertex tmp = qv.remove();
				if (tmp.parent != null && tmp.parent != s)
				{
					tmp = tmp.parent;
					qv.add(tmp);
				}
				else if (tmp.parent == s)
				{
						x.NumShortPath = tmp.Adj.size();
	//					break ;
				}
					
			}
		}
		}
		{
			long k = 0;
			for (Vertex v : d)
			{
				k = k+v.NumShortPath;
			//	System.out.println(v+" "+v.degree);
			}
			System.out.println("Num of short"+k);
			if (d.verts.size()<=100)
			{
				for (Vertex v : d)
				{
					if ( v.distance != Infinity)
						System.out.println(v+" "+v.distance+" "+v.NumShortPath);
					else
						System.out.println(v+" "+"INF"+ " 0");
				}
			}
		}
	}
	
	/*public void DAG(Graph g)
	{
		Queue<Vertex> q = new LinkedList<>();
		for (Vertex v : g)
		{
			if (v.degree == 0)
				q.add(v);
		}
	}*/
	
	public boolean toplogicalOrder(Graph g) {
        boolean isGraphDAG=true;
        ArrayDeque<Vertex> stack = new ArrayDeque<>();
        List<Vertex> lst = new ArrayList<>();
        for(Vertex u:g)
        {
                   if(u.degree==0)
                   {
              //                System.out.println(u.degree);
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
                              f.degree--;   //reducing degree of the vertices on other end.
                              if(f.degree==0)
                              stack.add(f);
                   }
                   
        }
        for(Vertex v:g){
        //           System.out.println(v+" "+v.degree);
                   if(v.degree!=0){
                              isGraphDAG=false;
                              break;
                   }
            }
        if(!isGraphDAG)
                   System.out.println("Graph has cycle");
 //       System.out.println(lst);
                   return isGraphDAG;
    
  }
	
	public static void main(String args[])
	 {
		 Dijkstra d = new Dijkstra() ;
		 Scanner sc= new Scanner(System.in);
        Graph g=Graph.readGraph(sc,true);
        Vertex s = g.verts.get(1);
    //     d.dijkstra(g, s);
         d.printResult(g, true);
      //   d.BFS(g, s);
        d.shortestPath(g);
	 }

}
