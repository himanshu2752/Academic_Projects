@author Himanshu Parashar, Satwant Singh
Readme
==========================================
The zip folder comtains following files:
PrmtnsCmbntns.java
Timer.java
Readme.txt

The PrmtnsCmbntns contains the following problems:
a) The methods for C(A,n,k) and P(A,n) are implemented.A comaprsion can be drawn between the Take2 and Heap's algorithm for Permute() which shows that for small  values n=1 to n=6 the running times for both remain comparable but as n increases the running time incresases considerably for the Take 2 implementation as comapared to Heap's implementation. Although the we can see that the running time for n=11 both the algorithm have running times in minutes but the heap's algorithm is still 4 minutes faster.
n=8
Heap's:Time: 612 msec.
Memory: 28 MB / 128 MB.
Take 2:Time: 839 msec.
Memory: 19 MB / 128 MB.
---------------------------
n=11
Heap's:Time: 962777 msec.
Memory: 9 MB / 114 MB.
Take 2:Time: 1220271 msec.
Memory: 19 MB / 114 MB.
----------------------------

b)The method kPrmtntnsOfn() is implemented for to get P(A,n,k)ordered sets of cardinality k from a set of size n. The solutions from part a are combined to get the solution.