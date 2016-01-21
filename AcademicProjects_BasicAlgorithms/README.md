# Academic_Projects in Alogrithm analysis and data structures
####Data structures and algorithm projects at UTD completed by Himanshu Parashar(UTD_ID:-2021278364)

####--------PROJECT-1------------JAVA Generics,Constructors,2D arrays.

- EvaluateTemperature.java
It has a class named "EvaluateTemperature" which has a main method in which scanner is taking input as an integer representing a temperature and a string of either C or F for Celsius or Fahrenheit. If Celsius(C) then it is converting it to Fahrenheit(F). Using following table to evaluate temperature and print its description.
    Fahrenheit ranges
#######< 0   Extremely cold
#######0-32   Very cold
#######33-50  Cold
#######51-70  Mild
#######71-90  WarmS
#######91-100 Hot
#######> 100  Very hot

- ScoresTest.java
It has class named "ScoresTest" which has a main method in which scanner is taking input the name and 5 quiz scores for each of 10 different people storing the names in a 1D-array, and the scores in a 2D-array. A method named average is calculating the average score of the student. Name of each student and his/her average score is printed as output.

- Square.java
It has class named "Square" which is storing the length of one side and has two constructors, one that has no parameters and sets the side length to one. The other constructor is taking one parameter and sets the side length to the parameter value. A method named "getArea" returns the area of the square.

- TestSquare.java
It has class named "TestSquare" that creates two squares, one with each constructor from "square" class, and prints the area of each.

- MyFour.java
It has a generic class named "MyFour" using type parameter of "T". It has four fields, item1, item2, item3, item4, all of type T. Its constructor receives values for setting all four items. It has a method "allEqual" that returns true if all four items are equal according to their "equals" method. It has a method called "shiftLeft" that shifts all items up one position, and putting the first item's value into the last item. It has a "toString" method that returns/prints a String of the items in this format: (item1, item2, item3, item4).
"main" method of this class has a MyFour object of type String, passing it four identical strings. It prints the object, then calls and prints the results of "allEqual". Also another object of four different Integers. It again prints this second object and prints the results of "allEqual".  Then "shiftLeft" method is called which shift the items left and then items are printed again to check the same.


####--------PROJECT-2------------LINKED LISTS

- LinkLists.java
It has a class named "LinkLists" which will create three linked lists. In main method BufferedReader is reading the input.txt(txt file) file which defines the action to be performed on linked lists "A" - ADD, "P" - Print the linked lists, "C" - CANCEL, "R" - Remove. These actions are the following methods:-

 - ADD- It will add the node to the shortest list, or to the first list if they are all equal in size. Lists are kept in sorted order, smallest at the front, so an add operation is inserting to the correct position.

 - CANCEL- cancel operation is removing an item from the lists if found, or report an error if not. A cancel operation only removes one matching item in the case of duplicates.

 - Remove- remove operation is removing the smallest item from the lists. It determines which list has the smallest item and that item is removed. If all lists are empty, it prints an error message.


####--------PROJECT-3------------BINARY TREE

- GenBinTree.java
It has a class named "GenBinTree" which has a Node class storing a value of generic type <T>. Tree created in this project is not a search tree(no ordering of nodes by value) instead its just a generic binary tree. Following methods are created to perform a specific function :-

 - ADD- This operation works by receiving a string for example :-
#######myTree.add("20");
myTree.add("L","50");
myTree.add("R","10");
myTree.add("LR","40");
So 20 will be the root node , 50 will be root's left child, 10 will be root's right child, 40 will be right child of root's left node.

 - find- This operation returns true if the value passed is in the tree.

 - remove- This operation removes the node having the passed value, but only if it is a leave.

 - swap- This operation swaps children of node having the passed value.

 - mirror- This operation Makes the tree a mirror image of the original tree.

 - RotateRight- This operation performs a single right rotation on the node having the passed value.

 - RotateLeft- This operation performs a single left rotation on the node having the passed value.

 - NumberOfNodes- Recursively traverses the tree and returns the count of nodes.

 - printTreeInorder- Prints node values using an inorder traversal.

All above methods operate on object making the call (none are static). Also all public methods are called in main, private methods for each operation are called in their public method.


####--------PROJECT-4------------HASHING/HASH TABLE

- DupLL.java
It is a original program file provided, its purpose is to read a file of phone numbers and report duplicates. Its main method opens a file of 5003 phone numbers (ph.txt) in which one number has one duplicate and another number has two duplicates. It uses Linkedlist with very poor performing algorithm to find duplicates and same duplicates are reported more than once.

- DupHT.java
It has class name DupHT with a main method to read the txt file of phone numbers, putting each number(string) in a list and then adding them in a hashtable. Object of MyHashTable class is created in this class to access the methods of MyHashTable. Number of duplicates and duplicate phone numbers are printed.

- MyHashTable.java
It has class named MyHashTable which is creating a hash table for the string values received. Algorithm for new program uses quadratic probing. txt file containing the phone numbers is read line by line and added to the hash table by first converting the string into number then getting hashvalue for it. The hashfunction uses that hashvalue. I have taken default table size of 5003 and then increasing it as my hashtable grows (if it gets half full i am doubling the hash table).Hashfunction takes mod of the phone number with 5003 when size of array is <= 5003 ,1007 when size of array is <= 10007 and mod with array size when size of array becomes greater than 10007.
I am increasing the array size by finding the prime using a function of my own (it may not be efficient for larger values). I am finding duplicate by storing the number of times the duplicate of a particular phone number in a element of new array table where index is the hash of that duplicate phone number therefore we can know each duplicate number as its hash will be the index in new array and number of times its duplicating is the element of that index.
Hence the phonenumber whose hash (as index) in new table has its element not null is duplicate phone number.

#######Original program(DupLL) is taking 863835 millisecond or 14.39 minutes to complete . New program(DupHT) is taking 350 millisecond to complete.


####--------PROJECT-5------------ SORT Algorithms

- Sort.java- Class Sort has static methods of sorting algorithms- insertion sort named "insertionSort" and quick sort named "quicksort" referenced from the book "Data Structures and Algorithm Analysis in Java" by Mark Allen Weiss.

- BucketSort.java- It has class named "BucketSort" which has a sort method created by me to sort the list according to bucket sort in which temporary array is created who's index is the element of the original list to be sorted and it is incremented each time it occurs traversing throught the original list. And then sorted list is obtained by taking the lower index of temporary list first.

- SortMain.java- It has method "Random" which is generating the random numbers(shuffeling the integers in the list) for a list of type integers. Number of items/integers in the list can be changed by changing the value of "NUM_ITEMS". It also has main method which is sorting the list by insertion sort and quick sort by methods "insertionSort" and "quicksort" in Sort class and by bucket sort by method "BucketSort" in the class BucketSort.

#######A graph is represented in the "Graph.xlsx" to compare running times of all the sort methods used in the program.


####--------PROJECT-6------------Graphs and Kruskals Algorithm

Program is using Kruskal's algorithm to find a minimum spanning tree of a graph. Graph is provided as a file named "assn9_data.csv". Data in the file is in the form of adjacency list. The file has different cities and their distance from each city as adjancy list.

- DisjSets.java - Referenced from the book "Data Structures and Algorithm Analysis in Java" by Mark Allen Weiss (autors code).

- Kruskals.java -  Output of each edge of minimum spanning tree are the names of the two cities and the distance between them. It also prints the sum of all the distances in the tree

