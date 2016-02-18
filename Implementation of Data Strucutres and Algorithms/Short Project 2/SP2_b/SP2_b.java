import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author himanshu
 *	class for Short Project 2 b part
 */
public class SP2_b  {
	// Value which will be initially assigned to the distance for all nodes
	//which tells us that node has not been reached yet.
	private static final int Infinity = Integer.MAX_VALUE;	 
	/**
	 * 
	 * @param g = graph which is read by readGraph method in Graph class
	 * @return = diameter of the tree (graph in our case) or -1 if graph is not a tree.
	 */
	public int BreadthFirst(Graph g )
	{
		boolean isTree = true ;
		boolean hasCycle = true ;
		for (Vertex v : g)
		{
			if (v.degree >1 )
				isTree = false ;
			if (v.degree == 0)
				hasCycle =false ;
		}
		if (!isTree || hasCycle)
			return -1;
		
		for (Vertex v : g)
		{
			if (v != null && v.revAdj.size()>1)
			{
				System.out.println("Graph is not a tree");
				return -1 ;
			}
			
		}
		int diameter =0 ;
		Vertex v = g.verts.get(1);
		return diameter = bfs1(g,v);
	}
/**
 * Method to do first Breadth First search
 * @param g = Graph g and     
 * @param r = source or root vertex as random vertex r which is in our case is vertex at index 1 (in list of vertices) in the graph g
 * @return	= diameter
 */
	private int bfs1(Graph g,Vertex r )
	{
		Vertex vBfs2 = r ;
		Queue<Vertex> q = new LinkedList<>();
		Vertex current = r ;
		if (g.numNodes == 0)
			System.out.println("Input is not a graph");
			
		else
		{
			for (Vertex i : g )
			{			
				i.distance = Infinity ;
				i.parent = null ;
			
			}
			r.distance = 0;
			q.add(r);
		
			while (!q.isEmpty())
			{
				current = q.poll();
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
				return -1 ;
			}
		}
		Iterator<Vertex> it1 = g.iterator();
		Vertex v1 = it1.next();
		vBfs2 = v1 ;
		while (it1.hasNext())
		{
			if (v1.distance >= vBfs2.distance)
				vBfs2 = v1 ;
			v1 = it1.next();
		
		}
		
		return bfs2 (g,vBfs2);
		
	}
/**
 * Second breadth first search
 * @param g = graph
 * @param v2= root Vertex v2 (which has maximum distance in BFS1)
 * @return = diameter of the tree
 */
	public int bfs2 (Graph g ,Vertex v2)
	{
		Vertex vDia = v2 ;
		Queue<Vertex> q = new LinkedList<>();
		Vertex current = v2 ;
		
			
			for (Vertex i : g)
			{				
				i.distance = Infinity ;
				i.parent = null ;
				
			}
			v2.distance = 0;
			q.add(v2);
		
			while (!q.isEmpty())
			{
				current = q.poll();
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
		
			Iterator<Vertex> it2 = g.iterator();
			Vertex v = it2.next();
			vDia = v ;
			while (it2.hasNext())
			{				
				if (v.distance >= vDia.distance)
					vDia = v;
				 v =it2.next() ;
			}
	
		return vDia.distance ;
		
	}
	
	public static void main (String args[])
	{
		SP2_b sp = new SP2_b();			//Object of the class
		Scanner s = new Scanner(System.in);
		
		System.out.println(sp.BreadthFirst(Graph.readGraph(s,false)));	// Printing the output(diameter) of BFS 
		
	}

}
