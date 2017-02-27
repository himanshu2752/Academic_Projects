1. Developed on eclipse with jdk version jdk1.8.0_102

2. following are the imports -
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

3. We have 3 java files - 
-kMeans_algorithm.java
-point_class.java
-cluster_class.java

4. How to run the code -
- Put all the sourcecode(java files) in same folder along with input file
- After going in that folder from command line 
	- javac kMeans_algorithm.java
	- javac point_class.java
	- javac cluster_class.java
- then to run the program - java kMeans_algorithm  <value of k or number of clusters> <"inputfile.txt"> <"name_of_output_file.txt"> 
for example for 3 number of clusters - java kMeans_algorithm 3 "test_data.txt" "output.txt"

5. Outfile will be generated in the same folder. I have printed output on console also.