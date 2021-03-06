/**
 * Class to represent a vertex of a graph
 * 
 *
 */

import java.util.*;

public class Vertex {
    public int name; // name of the vertex
    public boolean seen; // flag to check if the vertex has already been visited
    public Vertex parent; // parent of the vertex
    public int distance; // distance to the vertex from the source vertex
    public List<Edge> Adj, revAdj; // adjacency list; use LinkedList or ArrayList
    public List<Vertex> AdjV ;	// adjacency list for adjacent vertices
    public int degree;// the degree of a vertex. 

    /**
     * Constructor for the vertex
     * 
     * @param n
     *            : int - name of the vertex
     */
    Vertex(int n) {
	name = n;
	degree=0;
	seen = false;
	parent = null;
	Adj = new LinkedList<Edge>();
	revAdj = new ArrayList<Edge>();   /* only for directed graphs */
	AdjV = new ArrayList<Vertex>();		/* implemented to get adjacent vertices to a vertex*/
    }

    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
	return Integer.toString(name);
    }
}