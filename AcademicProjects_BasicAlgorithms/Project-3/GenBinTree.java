
package project3;
import java.util.*;
public class GenBinTree<T extends Comparable<? super T>>
{
	 private Node<T> root ;
	 private T val ;
	 private Node<T> nd ;
	 int count =0;
	private static class Node<T> 			//////////Node Class //////////////////////
	{
		T data ;
		Node<T> left ;
		Node<T> right ;
		
		Node (T d, Node<T> l , Node<T> r)
		{
			data =d ;
			left = l;
			right = r;
		}
	
	}
	public T get()
	{
		return val;
	}
	public void set(T val)
	{
		this.val = val;
	}
	
	public Node<T> getN()
	{
		return nd;
	}
	public void setN(Node<T> nd)
	{
		this.nd = nd;
	}
	
	public Node<T>  ADD (T a)
	{	 
		
		 root = new Node<T>( a,null,null);
		 return root ;
	}

	public Node<T> ADD(String s ,T t)
	{
		GenBinTree<T> gbt = new GenBinTree<>();
		int n = s.length();
		String[] st = new String[n];
		st = s.split("(?!^)");
		
		if (root == null)
		{
			root = gbt.ADD(t);
			return root ;
		}
		
		else if (root!= null)	
		{
			Node<T> k = root ;
			for (int i=0 ; i<n ; i++)
			{	
				
				if (st[i].compareTo("L")==0 && k.left == null && k == root)
				{
				root = gbt.InsertL(t, root);
				k = root.left ;
				}
				else if (st[i].compareTo("L")==0 && k.left == null && i == n-1)
				{
					k.left = gbt.InsertL(t,k.left);
				}
				else if (st[i].compareTo("L") == 0 && k.left != null )
				{
			
						k = k.left ;	
					if (k.left == null && i ==n-1)
					{
						k.left = gbt.InsertL(t, k.left);
					
					}
				}
				else if (st[i].compareTo("R")==0 && k.right == null && k == root)
				{
					root = gbt.InsertR(t, root);
					
				}
				else if (st[i].compareTo("R")==0 && k.right==null && i == n-1)
				{
					k.right = gbt.InsertL(t, k.right);
				}
				else if (st[i].compareTo("R") ==0 && k.right!= null)
				{
		
						k = k.right ;
					if (k.right == null && i == n-1)
					{
						k.right = gbt.InsertR(t, k.right);
				
					}
				}
				else 
				{
					System.out.println("Invalid Input given " +s);
					break ;
				}
				
			}
			
		}return root ;
 	}
	
	public boolean find(T findval)
	{
		find(root , findval);
		if (findval.equals(get()))
		{
			return true;
		}
		else return false ;
	}
	
	public Node<T> findNd (T x)
	{
		findNd(root ,x);
		if (getN() == null)
			return null ;
		if (x.equals(getN().data))
			return getN();
		else
			return null ;
	}
	
	public void remove(T r)
	{
		remove1(root ,r);
		if (find(r) == true)
			System.out.println(r+" Node is not a leaf or not present in Tree hence can not be removed");
	}
	
	public void swap(T s)
	{
		swap1(s);
	}
	public void mirror ()
	{
		mirrorOfTree();
	}
	
	public void RotateRight(T rr)
	{
		RotateR(root ,rr);
	}
	
	public void RotateLeft(T rl)
	{
		RotateL(root , rl);
	}
	
	
	public void NumberOfNodes()
	{
		countTree(root);
		System.out.println("number of nodes in the tree are :"+count);
	}
	
	public void printTreeInorder()
	{
		printTree(root);
	}
	
	
	private Node<T> InsertL(T x , Node<T> n )
	{
		
		if (n == null)
		return new Node<T>(x,null,null);
		
		else 
		n.left = InsertL(x , n.left);
		
		return n ;
	}
	
	private Node<T> InsertR(T x , Node<T> n)
	{
		if (n == null)
			return new Node<T>(x,null,null);
		
		else
			n.right = InsertR(x,n.right);
		return n ;
	}
	
	
	private void find(Node<T> n , T t)
	{
				
		if (n != null)
		{
		if (t.compareTo(n.data) == 0)
			set(n.data);
		
			find(n.left , t);
			find(n.right , t);			
		}					
	}
	
	
	
	private void findNd(Node<T> n , T t)
	{
		if (n != null)
		{
		if (t.compareTo(n.data) == 0)
			setN(n);
		
			findNd(n.left , t);
			findNd(n.right , t);			
		}	
	}
	
	
	private Node<T> remove1(Node<T> r , T t)
	{
		if (r == null)
		{
			return r ;
		}
		int x = t.compareTo(r.data);
		
		if (x == 0 && r.left == null && r.right == null)
		{
			System.out.println(t+ " Node removed from tree");
			return null;			
		}
		
		r.left = remove1(r.left ,t);
		r.right = remove1(r.right ,t);
		
		return r ;
	}
	
	
	
	private void swap1(T s)
	{
		Node<T> s1 = findNd (s);
		if (s1 == null)
		{
			System.out.println(s+ " Node not present in the tree for swapping");
		}
		else if (s1.left == null && s1.right ==null)
		{
			System.out.println(s+ " Node is leaf with no children to swap");
		}
		else if (s1.left != null && s1.right != null)
		{
			System.out.println("Node ["+s1.data+"] swapped with it's children");
			Node<T> tmp1 = s1.left;
			s1.left = s1.right;
			s1.right = tmp1 ;
		}
		else if (s1.left != null && s1.right == null)
		{
			System.out.println("Node ["+s1.data+"] swapped with it's children");
			s1.right = s1.left ;
			s1.left = null ;
		}
		else if (s1.left == null && s1.right != null)
		{
			System.out.println("Node ["+s1.data+"] swapped with it's children");
			s1.left = s1.right;
			s1.right = null;
		}
	}
	private void mirrorOfTree()
	{
		if (root != null )
		{
			if (root.left != null && root.right != null)
			{
				Node<T> tmp2 = root.left ;
				root.left = root.right ;
				root.right = tmp2 ;
			}
			else if (root.left != null && root.right == null)
			{
				root.right = root.left ;
				root.left = null;
			}
			else if (root.left == null && root.right != null)
			{
				root.left = root.right ;
				root.right = null;
			}
		}
		else if (root == null)
		{
			System.out.println("Can not create mirror image as Root Node is null ");
		}
	}
	
		
	private Node<T> RotateR(Node<T> rr ,T t)
	{
		if (rr == null)
		{
				return rr ;
		}		
		
		if (t.compareTo(rr.data) == 0 && rr != null && rr.left != null)
		{
			Node<T> tmp1 = rr ;
			Node<T> tmp2 = rr.left.right ;
			rr = rr.left ;
			rr.right = tmp1 ;
			rr.right.left = tmp2;
			return rr ;
		}
			
		
	rr.left 	=	RotateR(rr.left ,t);
	rr.right	=	RotateR(rr.right ,t);
		
			return rr;		
	}
	
	
	
	private Node<T> RotateL(Node<T> rl ,T t)
	{
		if (rl == null)
			return rl ;
		
		if (t.compareTo(rl.data) == 0 && rl != null && rl.right != null)
		{
			Node<T> tmp1 =rl ;
			Node<T> tmp2 = rl.right.left ;
			rl = rl.right ;
			rl.left = tmp1 ;
			rl.left.right = tmp2;
			return rl ;
		}
		rl.right = RotateL(rl.right ,t);
		rl.left = RotateL(rl.left , t);
		return rl ;
	}


	

	
	private void countTree(Node<T> n)
	{		
		if (n!=null)
		{			
			countTree(n.left);	
			System.out.println(n.data);
			count ++ ;
			countTree(n.right);		
			
		}
		
	}
	
	
	private void printTree(Node<T> n)
	{		
		if (n!=null)
		{
			
	//		System.out.println(n.data); /// PreOrder Traversal // Testing for Rotation //////////
			printTree(n.left);	
			System.out.println(n.data);
			printTree(n.right);			
		}		
	}

	public static void main (String args[])
	{
		GenBinTree<String> myTree = new GenBinTree<>();
		myTree.ADD("100");
		myTree.ADD("L","50");
		myTree.ADD("R","150");
		myTree.ADD("LL","40");
		myTree.ADD("LR","65");
		myTree.ADD("LLR","45");
		myTree.ADD("LLRL" ,"10");
		myTree.ADD("RR" ,"20");
		myTree.ADD("RL","70");
		myTree.ADD("RRL","55");
//		System.out.println(myTree.FindValue("70"));
		myTree.printTreeInorder();
		myTree.RotateLeft("50");
		myTree.RotateRight("40");
		myTree.printTreeInorder();
		myTree.ADD("RRRRL","51"); 		//Testing Invalid Inputs
		myTree.ADD("LLLLL", "51");		//Invalid Inputs
	//	myTree.printTree();
	System.out.println(	myTree.find("10"));
		myTree.remove("10");
	//	myTree.swap("20");
		myTree.printTreeInorder();
	/*	myTree.printTree();
	//	myTree.swap("55");
		myTree.remove("55");
		myTree.swap("55");
		myTree.printTree();
		
		myTree.find("10");
		myTree.printTree();
		myTree.swap("55");*/
		
		
	}
}



