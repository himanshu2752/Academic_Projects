@author:G32:
Satwant Singh
Himanshu Parashar
==============================
The following files are submitted as part of SP0h short project:
-SP0h_a.java
-SP0h_Using_HashMap.java
-SP0h_UsingSorting.java
-Timer.java
-Shuffle.java

a)-SO0h_a
	Contains a method to find distinct elements in an generic type array and place them at the begining of the same array. It returns the number of distinct elements(k). Hashset and its constant time operations i.e add, contains and remove are used to find the distinct elements. Iterator on hashset is used to place them at 0 to k-1 positions in the array.Running time is comming to be linear
	Example: for 1 million elements and 1000 non-distinct elements the running time is 193 milliseconds, if distinct elements become less(or non distinct elements are more) running time also reduces.

b)-SP0h_Using_HashMap.java
  -SP0h_UsingSorting.java
  The algorithm for finding most frequently appearing element is implemented separately using Hashmap(O(n)) v/s by sorting  and finding the element appearing frequently(O(nlog(n))).The comparison of the two shows that for values of n<1 million the Running time of both are comparable with Hashmap almost having a better runnning time but with further increase of n>1 million the running time of HashMap increase and the other algorithms has much better running time.
  Example
  				Soring and finding v/s HashMap
  n=100000		38 msecs				39 msec
  n=1 million 	151 msecs 				217 msecs	
  n=3 million   413msec					1946 msecs