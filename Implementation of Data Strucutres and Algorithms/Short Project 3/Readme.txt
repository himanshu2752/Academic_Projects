Author:G32
Readme
===================
Files included: QuickSort.java
		DualPivotQuickSort.java
		MergeSort.java
		Sp3_a.java
		Timer.java
		Shuffle.java

a.	SP3_a.java
	Fibonacci Numbers implemented using the linear recursive algorithm and the matrix form algorithm. The difference between their running tme becomes significant for very large values of n and for small values the difference remain small.
	For n=100		For n=10000		For n=100000000
	linear:0 ms		linear:1 ms		linear:3154ms
	matrix:0 ms		matrix:0 ms		matrix:0 ms

b.	QuickSort.java and DualPivotQuickSort.java
	Implemented QuickSort using Single pivot and Dual pivot partition. The comparison shows that the running time of the quicksort depends majorily on the selection of the pivot.We have taken the pivot to be the median of the first and the last element for the single pivot quicksort and first and the last elmenet of the subarrays of the dual pivot quicksort.Based on this pivot the dual pivot has almost same running time as the single pivot quicksort.The case of duplicate elements are handled in the code itself.
	Running Time:
	For n=67108864
	QuicSort:
	Time: 41690 msec.
	Memory: 1346 MB / 1805 MB.
	DualPivotQuickSort:
	Time: 42120 msec.
	Memory: 1345 MB / 1806 MB.

c.	MegerSort.java and DualPivotQuickSort.java
	The MergeSort and dual pivot quicksort are implemented.Our analysis shows that for small inputs running time is almost same for both sorts and for large inputs mergesort has better running time.The case of duplicate elements are handled in the code itself.
	Runninf times for sorts:
	For n=67108864					
	MergeSort:					
	Time: 11908 msec.	
	Memory: 1613 MB / 1886 MB.
	DualPivotQuickSort:
	Time: 43020 msec.
	Memory: 1345 MB / 1806 MB.

References:
===================
Dual-Pivot Quicksort algorithm,Vladimir Yaroslavskiy
http://algs4.cs.princeton.edu/23quicksort
