# Implementation of Advance Data Structure and algorithms

### Project 0
 Implemented sorting algorithms - Merge Sort and Priority Queue using java generics. And compared running times for
#####input > 1million
- calculated average time of both sorts for inputs - 1million, 5million, 8million, 10million and 100million.
- increased input till "out of memory exception".
- compared and analyzed both sorts

#####Though MergeSort and PriorityQueue both have O(NlogN) running time but from this analysis we were able to see that when input increases above 1million MergeSort running time is slightly better than PriorityQueue. Running time remains almost same for both sorts till input =1million.

### Short Project 1
##### Part d
- SP1_d.java 
	To implement a recursive algorithm  without recursion, by using a stack to simulate recursion. This class has a method to implement merge sort without using recursion. This is implemented by using two stacks (of array types) where MergeSort is a public method in which array to be sorted is passed. mergeSort is a private method which is dividing the array (non recursively using two stacks) and returning sorted array, this method is calling another private method "merge" taking two divided lists (arrays)  as arguments each time the lists are divided using stacks and merging them in sorted order.

This sorting algorithm was tested with test input upto 20 million and is taking almost same time as mergesort(using recursion takes).

##### Part a
- SP1a.java
	Writing functions of generic type which takes two sorted lists as inputs (arguments) and doing set operations. Below are the methods in this class
- union 
	Union of two sets
- intersect
	Intersection of two sets
- difference
	doing set difference (List1 - List2)

Test inputs are taken as linked lists.

### Short Project 2
#### Test inputs can be taken upto 20 million nodes.
##### SP2_a (Part a)
- Edge.java - contains Edge class (to represent Edge) for the graph having two vertices (from and to) and weight of the edge
- Vertex.java - contains vertec class (to represent vertex) for the graph having name(int type), parent, seen(boolean), distance, adjacency list for edges and degree.
- Graph.java - contains graph class (to represent graph) having methods for adding directed and undirected edges , method to read graph, iterator on list of vertices etc
- SP2_a.java -  Implemented two algorithms for ordering the nodes of a DAG (Directed acyclic graph) topologically. Both algoritms returns null if the given graph is not a DAG. following are the methods
	- toplogicalOrder1 - Method for ordering the nodes of the graph topologically
	- toplogicalOrder2 - Method for ordering the nodes of the graph topologically using DFS
	- DFSVisit - Method used recursively for Depth First Search

##### SP2_b (Part b)
- Edge.java - to represent Edge in a graph
- Vertex.java - to represent vertex in a graph
- Graph.java - to represent graph by vertex and Edge
- SP2_b.java -  we take unrooted tree as input (which is graph). Since the tree may not be a binary tree, we will represent it with an adjacency list (i.e., it is a graph that happens to be a tree). we are finding its diameter by 1. Running BFS from an arbitrary node as root. 2. Selecting a node Z with maximum distance from the first BFS. 3. Run a second BFS with Z as root. 4. The diameter of the tree is the maximum distance to any node from Z in BFS 2. Following are the methods in this class-
	- BreadthFirst - this method takes graph as input @param g = graph which is read by readGraph method in Graph class @return = diameter of the tree (graph in our case) or -1 if graph is not a tree. Calls bfs1 method to find diameter
	- bfs1 - method do first breadth first search and after selecting the maximum distance node (let Z), it calls bfs2 (second bfs) with Graph g and vertex Z as input parameters. This method takes graph g and any vertex/node as inputs and returns diameter
	- bfs2 - this method does second breadth first search and returns diameter. Takes Graph and vertex(from first bfs) as input.



### Group Projects

###### Project 1
- SinglyLinkedList.java
Recursive and nonrecursive functions for the following tasks:
   (i) reverse the order of elements of the SinglyLinkedList class
   (ii) print the elements of the SinglyLinkedList class, in reverse order.