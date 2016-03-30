
import java.util.Comparator;
/**
 * IndexedHeap class
 * @Edited by himanshu and Satwant 
 * Group G32
 * @param  of type T extends Index
 */
public class IndexedHeap<T extends Index> extends BinaryHeap<T> {
    /** Build a priority queue with a given array q */
    IndexedHeap(T[] q, Comparator<T> comp) {
	super(q, comp);
	for(int i=1;i<pq.length;i++){
		pq[i].putIndex(i);
		}
    }

    /** Create an empty priority queue of given maximum size */
    IndexedHeap(int n, Comparator<T> comp) {
	super(n, comp);
    }

    /** restore heap order property after the priority of x has decreased */
    void decreaseKey(T x) {
	percolateUp(x.getIndex());
    }
    /**
     * Overriding assign function of BinaryHeap class
     */
    public void assign(int i,T element)
    {
    	pq[i]=element;
    	element.putIndex(i);
    }
   
}
