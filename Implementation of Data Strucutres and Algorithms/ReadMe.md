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


### Group Projects

###### Project 1
- SinglyLinkedList.java
Recursive and nonrecursive functions for the following tasks:
   (i) reverse the order of elements of the SinglyLinkedList class
   (ii) print the elements of the SinglyLinkedList class, in reverse order.