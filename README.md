# Academic_Projects
Data structures and algorithm projects at UTD completed by Himanshu Parashar(UTD_ID:-2021278364)

--------PROJECT-1------------
-EvaluateTemperature.java
It has a class named "EvaluateTemperature" which has a main method in which scanner is taking input as an integer representing a temperature and a string of either C or F for Celsius or Fahrenheit. If Celsius(C) then it is converting it to Fahrenheit(F). Using following table to evaluate temperature and print its description.
    Fahrenheit ranges
     < 0   Extremely cold
    0-32   Very cold
    33-50  Cold
    51-70  Mild
    71-90  Warm
    91-100 Hot
    > 100  Very hot

-ScoresTest.java
It has class named "ScoresTest" which has a main method in which scanner is taking input the name and 5 quiz scores for each of 10 different people storing the names in a 1D-array, and the scores in a 2D-array. A method named average is calculating the average score of the student. Name of each student and his/her average score is printed as output.

-Square.java
It has class named "Square" which is storing the length of one side and has two constructors, one that has no parameters and sets the side length to one. The other constructor is taking one parameter and sets the side length to the parameter value. A method named "getArea" returns the area of the square.

-TestSquare.java
It has class named "TestSquare" that creates two squares, one with each constructor from "square" class, and prints the area of each.

-MyFour.java
It has a generic class named "MyFour" using type parameter of "T". It has four fields, item1, item2, item3, item4, all of type T. Its constructor receives values for setting all four items. It has a method "allEqual" that returns true if all four items are equal according to their "equals" method. It has a method called "shiftLeft" that shifts all items up one position, and putting the first item's value into the last item. It has a "toString" method that returns/prints a String of the items in this format: (item1, item2, item3, item4).
"main" method of this class has a MyFour object of type String, passing it four identical strings. It prints the object, then calls and prints the results of "allEqual". Also another object of four different Integers. It again prints this second object and prints the results of "allEqual".  Then "shiftLeft" method is called which shift the items left and then items are printed again to check the same.


--------PROJECT2------------
-LinkLists.java
It has a class named "LinkLists" which will create three linked lists. In main method BufferedReader is reading the input(txt file) file which defines the action to be performed on linked lists "A" - ADD, "P" - Print the linked lists, "C" - CANCEL, "R" - Remove. These actions are the following methods:-
ADD- It will add the node to the shortest list, or to the first list if they are all equal in size. Lists are kept in sorted order, smallest at the front, so an add operation is inserting to the correct position.

CANCEL- cancel operation is removing an item from the lists if found, or report an error if not. A cancel operation only removes one matching item in the case of duplicates.

Remove- remove operation is removing the smallest item from the lists. It determines which list has the smallest item and that item is removed. If all lists are empty, it prints an error message.


--------PROJECT3------------