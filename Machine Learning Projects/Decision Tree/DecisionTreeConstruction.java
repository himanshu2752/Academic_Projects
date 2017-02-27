import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//TreeNode class that stores various types of data which are used during Decision Tree Construction
class TreeNode {
	int val = -1;
	TreeNode parent;
	TreeNode left;
	TreeNode right;
	boolean leaf = false;
	int finalValue = -1;
	int leftIndex[];
	int rightIndex[];
}
//Decision Tree class
public class DecisionTreeConstruction {
	private static int count = 0;

	
	//convert to log base 2 of a real number
	private static double calculateLog(double fraction) {
		return Math.log10(fraction) / Math.log10(2);
	}


	//compares sign of all indices with oppSign
	public static boolean signCheck(int[] indexList, int[][] values, int features,int oppSign) {
		for (int i : indexList) {
			if (values[i][features] == oppSign)
				return false;
		}
		return true;
	}

	
	// This function will return maximum value of the target attribute
	public static int findMax(TreeNode root, int[][] values, int features) {
		int oneCount = 0, zeroCount = 0;
		if (root.parent == null)
			for (int i = 0; i < values.length; i++)			
				if (values[i][features] == 1)				
					oneCount++;
				 else
					zeroCount++;			
		else{
			if (root.leftIndex != null) {
				for (int i : root.leftIndex) {
					if (values[i][features] == 1) {
						oneCount++;
					} else {
						zeroCount++;
					}
				}
			}

			if (root.rightIndex != null) {
				for (int i : root.rightIndex) {
					if (values[i][features] == 1) {
						oneCount++;
					} else {
						zeroCount++;
					}
				}
			}
		}
		if(zeroCount>oneCount)
			return 0;
		else
			return 1;
	}
	
	// This function will return the maximum Value of the Target_Attribute among the the examples present at the TreeNode of decision tree.
	private static int findMaxValueAtGivenNode(TreeNode root, int[][] values, int features) {
		int oneCount = 0;
		int zeroCount = 0;
		if (root.leftIndex != null) {
			for (int i : root.leftIndex) {
				if (values[i][features] == 1) {
					oneCount++;
				} else {
					zeroCount++;
				}
			}
		}

		if (root.rightIndex != null) {
			for (int i : root.rightIndex) {
				if (values[i][features] == 1) {
					oneCount++;
				} else {
					zeroCount++;
				}
			}
		}
		if(zeroCount>oneCount)
			return 0;
		else
			return 1;
	}

	// checking whether all attributes are processed 
	public static boolean allAttributesVisited(int[] done) {
		for (int i : done) {
			if (i == 0)
				return false;
		}
		return true;
	}

	//constructing a decision tree by selecting node randomly
	public static TreeNode randomDecisionTree(TreeNode root, int[][] values, int[] done, int features, int[] indexList,TreeNode parent) {
		if (root == null)
		{
			root = new TreeNode();
			if (indexList == null || indexList.length == 0||features==1||allAttributesVisited(done)) {
				root.val = findMax(root, values, features);
				root.leaf = true;
				return root;
			}
			if (signCheck(indexList, values, features,0)) {
				root.val = 1;
				root.leaf = true;
				return root;
			}
			if (signCheck(indexList, values, features,1)) {
				root.val = 0;
				root.leaf = true;
				return root;
			}
			
		}
		root = findAttributeByRandom(root, values, done, features, indexList);
		root.parent = parent;
		if (root.finalValue != -1)
			done[root.finalValue] = 1;
		int leftDone[] = new int[done.length];
		int rightDone[] = new int[done.length];
		for (int j = 0; j < done.length; j++)
		{
			leftDone[j] = done[j];
			rightDone[j] = done[j];
		}

		root.left = randomDecisionTree(root.left, values, leftDone, features, root.leftIndex, root);
		root.right = randomDecisionTree(root.right, values, rightDone, features, root.rightIndex, root);
		return root;
	}
	
//  Construction of decision Tree based on the Training data and returns the TreeNode at the root.
	public static TreeNode constructDecisionTree(TreeNode root, int[][] values, int[] done, int features, int[] indexList,TreeNode parent) {
		if (root == null)
		{
			root = new TreeNode();
			if (indexList == null || indexList.length == 0||features == 1 || allAttributesVisited(done)) {
				root.val = findMax(root, values, features);
				root.leaf = true;
				return root;
			}
			if (signCheck(indexList, values, features,0)) {
				root.val = 1;
				root.leaf = true;
				return root;
			}
			if (signCheck(indexList, values, features,1)) {
				root.val = 0;
				root.leaf = true;
				return root;
			}
			
		}
		root = findAttribute(root, values, done, features, indexList);
		root.parent = parent;
		if (root.finalValue != -1)
			done[root.finalValue] = 1;
		int leftDone[] = new int[done.length];
		int rightDone[] = new int[done.length];
		for (int j = 0; j < done.length; j++)
		{
			leftDone[j] = done[j];
			rightDone[j] = done[j];
		}

		root.left = constructDecisionTree(root.left, values, leftDone, features, root.leftIndex, root);
		root.right = constructDecisionTree(root.right, values, rightDone, features, root.rightIndex, root);
		return root;
	}
	

	//finding the attribute randomly to construct decision tree
	private static TreeNode findAttributeByRandom(TreeNode root, int[][] values, int[] done, int features,int[] indexList) {
		
		int LeftIndex[] = null;
		int RightIndex[] = null;

		List<Integer> attributes = new ArrayList<Integer>();

		for(int i=0;i<done.length;i++)
			if(done[i]==0)
				attributes.add(i);
		

		
		Random generator = new Random();//randomly selects the number from the list of attributes
		int randomIndex = generator.nextInt(attributes.size());
		int Index = attributes.get(randomIndex);

				double left = 0;
				double right = 0;
				int[] leftIndex = new int[values.length];
				int[] rightIndex = new int[values.length];
				for (int k = 0; k < indexList.length; k++)
				{
					if (values[indexList[k]][Index] == 1)
					{
						rightIndex[(int) right++] = indexList[k];
					}
					else
					{
						leftIndex[(int) left++] = indexList[k];
					}
				}

					int leftTempArray[] = new int[(int) left];
					for (int index = 0; index < left; index++) {
						leftTempArray[index] = leftIndex[index];
					}
					int rightTempArray[] = new int[(int) right];
					for (int index = 0; index < right; index++) {
						rightTempArray[index] = rightIndex[index];
					}
					LeftIndex = leftTempArray;
					RightIndex = rightTempArray;

		root.finalValue = Index;
		root.leftIndex = LeftIndex;
		root.rightIndex = RightIndex;
		return root;
	}

//  At each level,finding the split attribute using maximum Information Gain
	private static TreeNode findAttribute(TreeNode root, int[][] values, int[] done, int features,int[] indexList) {
		double maxInfoGain = 0;
		int maxLeftIndex[] = null;
		int maxRightIndex[] = null;
		int maxIndex = -1;
		for (int i=0; i < features; i++) {
			if (done[i] == 0){
				double negatives = 0,positives = 0,left = 0,right = 0,leftEntropy = 0,rightEntropy = 0;
				double rightPositives = 0,rightNegatives = 0, leftPositives = 0, leftNegatives = 0;
				double entropy = 0,infoGain = 0;	
				int[] leftIndex = new int[values.length];
				int[] rightIndex = new int[values.length];
				for (int k = 0; k < indexList.length; k++){
					if (values[indexList[k]][features] == 1)
						positives++;
					 else
						negatives++;
					if (values[indexList[k]][i] == 1){
						rightIndex[(int) right++] = indexList[k];
						if (values[indexList[k]][features] == 1)
							rightPositives++;
						 else
							rightNegatives++;
					}
					else{
						leftIndex[(int) left++] = indexList[k];
						if (values[indexList[k]][features] == 1)						
							leftPositives++;
						 else						
							leftNegatives++;
					}
				}

				entropy = (-1 * calculateLog(positives / indexList.length) * ((positives / indexList.length)))
						+ (-1 * calculateLog(negatives / indexList.length) * (negatives / indexList.length));
				leftEntropy = (-1 * calculateLog(leftPositives / (leftPositives + leftNegatives))
						* (leftPositives / (leftPositives + leftNegatives)))
						+ (-1 * calculateLog(leftNegatives / (leftPositives + leftNegatives))
								* (leftNegatives / (leftPositives + leftNegatives)));
				rightEntropy = (-1 * calculateLog(rightPositives / (rightPositives + rightNegatives))
						* (rightPositives / (rightPositives + rightNegatives)))
						+ (-1 * calculateLog(rightNegatives / (rightPositives + rightNegatives))
								* (rightNegatives / (rightPositives + rightNegatives)));
				if (Double.compare(Double.NaN, entropy) == 0)
					entropy = 0;
				if (Double.compare(Double.NaN, leftEntropy) == 0)
					leftEntropy = 0;
				if (Double.compare(Double.NaN, rightEntropy) == 0)
					rightEntropy = 0;

				infoGain = entropy
						- ((left / (left + right) * leftEntropy) + (right / (left + right) * rightEntropy));
				if (infoGain >= maxInfoGain){
					maxInfoGain = infoGain;
					maxIndex = i;
					int leftTempArray[] = new int[(int) left];
					for (int index = 0; index < left; index++)
						leftTempArray[index] = leftIndex[index];					
					int rightTempArray[] = new int[(int) right];
					for (int index = 0; index < right; index++)
						rightTempArray[index] = rightIndex[index];
					maxLeftIndex = leftTempArray;
					maxRightIndex = rightTempArray;
				}
			}
		}
		root.finalValue = maxIndex;
		root.leftIndex = maxLeftIndex;
		root.rightIndex = maxRightIndex;
		return root;
	}

	//  creating and returning copy for the given tree
	public static TreeNode duplicate(TreeNode root) {
		if (root == null)
			return root;

		TreeNode temp = new TreeNode();
		temp.val = root.val;
		temp.leaf = root.leaf;
		temp.leftIndex = root.leftIndex;
		temp.rightIndex = root.rightIndex;
		temp.finalValue = root.finalValue;
		temp.parent = root.parent;
		temp.left = duplicate(root.left); // cloning left child
		temp.right = duplicate(root.right); // cloning right child
		return temp;
	}

	//  Post Pruning on the constructed Tree.
	public static TreeNode postPruneAlgorithm(String pathName, int L, TreeNode root, int[][] values, int features) {
		TreeNode postPrunedTree = new TreeNode();
		int i = 0;
		postPrunedTree = root;
		double maxAccuracy = CalculateAccuracy(pathName, root);

		for (i = 0; i < L; i++)
		{
			TreeNode newRoot = duplicate(root);
			Random randomNumbers = new Random();
			int M = 1 + randomNumbers.nextInt(20); // create random number between 1 and 20
			for (int j = 1; j <= M; j++)
			{
                count =0;
				int noOfNonLeafNodes = nonLeafNodesCount(newRoot);
				if (noOfNonLeafNodes == 0)
					break;
				TreeNode nodeArray[] = new TreeNode[noOfNonLeafNodes];
				buildArray(newRoot, nodeArray);
				int P = randomNumbers.nextInt(noOfNonLeafNodes);
				nodeArray[P] = maxElement(nodeArray[P], values, features);
			}
			double accuracy = CalculateAccuracy(pathName, newRoot);

			if (accuracy > maxAccuracy) {
				postPrunedTree = newRoot;
				maxAccuracy = accuracy;
			}
		}
		return postPrunedTree;
	}

	// Calculation of Accuracy of the Constructed Tree by using Validation Tree.
	/*private static double getAccuracy(String pathName, TreeNode newRoot) {
		int[][] validationSet = constructValidationSet(pathName);
		double count = 0;
		for (int i = 1; i < validationSet.length; i++) {
			count += isCorrectlyClassified(validationSet[i], newRoot);
		}
		return count / validationSet.length;
	}*/

	// verifying whether the Example is correctly classified as per the Constructed Tree.
	private static int isCorrectlyClassified(int[] setValues, TreeNode newRoot) {
		int index = newRoot.finalValue;
		int correctlyClassified = 0;
		TreeNode testNode = newRoot;
		while (testNode.val == -1) {
			if (setValues[index] == 1) {
				testNode = testNode.right;
			} else {
				testNode = testNode.left;
			}
			if (testNode.val == 1 || testNode.val == 0) {
				if (setValues[setValues.length - 1] == testNode.val) {
					correctlyClassified = 1;
					break;
				} else 
					break;
			}
			index = testNode.finalValue;
		}
		return correctlyClassified;
	}
	// creating and returning the leaf TreeNode with val set as majority value of the target_attribute among the examples at that TreeNode.
	private static TreeNode maxElement(TreeNode TreeNode, int[][] values, int features) {
		TreeNode.leaf = true;
		TreeNode.val = findMaxValueAtGivenNode(TreeNode, values, features);
		TreeNode.left = null;
		TreeNode.right = null;
		return TreeNode;
	}

	// This builds the {index, nodes at the index} map which is used for PostPruning Algorithm.
	private static void buildArray(TreeNode root, TreeNode[] nodeArray) {
		if (root == null || root.leaf) 
			return;		
		nodeArray[count++] = root;
		if (root.left != null) 
			buildArray(root.left, nodeArray);
		if (root.right != null) 
			buildArray(root.right, nodeArray);
	}

	// calculating accuracy of the test data
		private static double CalculateAccuracy(String path, TreeNode root) {
			double accuracy = 0;
			int[][] testingData = LoadTestData(path);
			for (int i = 0; i < testingData.length; i++) {
				accuracy += isCorrectlyClassified(testingData[i], root);
			}
			return accuracy / testingData.length;
		}
	
	// returning number of non-leaf nodes.
	private static int nonLeafNodesCount(TreeNode root) {
		if (root == null || root.leaf)
			return 0;
		else
			return (1 + nonLeafNodesCount(root.left) + nonLeafNodesCount(root.right));
	}

	//find number of nodes in tree
	 private static int size(TreeNode root){
	        if (root == null)
	            return 0;
	        else
	            return(size(root.left)+size(root.right))+1;
	    }

	 // find the sum of all depth of leaf node
	private static int sumOfLeafDepths( TreeNode node, int depth ) {
     if ( node == null )
        return 0;
     else if ( node.left == null && node.right == null) 
        return depth;
     
     else 
        return sumOfLeafDepths(node.left, depth + 1)+ sumOfLeafDepths(node.right, depth + 1);
     
 }

	// get the number of leaf nodes
	private static int GetNoofLeafnodes(TreeNode root)
	{
	        if (root == null) {
	            return 0;
	        }
	        if (root.left == null && root.right == null) {
	            return 1;
	        } else {
	            return GetNoofLeafnodes(root.left) + GetNoofLeafnodes(root.right);
	        }
	}
	
	// to get the attribute values from the csv file
		private static int[] attributeValues(String csvFile) {
			BufferedReader bufferedReader = null;
			String line = "";
			String cvsSplitBy = " ";
			@SuppressWarnings("unused")
			int features = 0;
			int count = 0;
			int[] featuresAndLength = new int[2];
			try {
				bufferedReader = new BufferedReader(new FileReader(csvFile));
				while ((line = bufferedReader.readLine()) != null) {
					if (count == 0) {
						String[] country = line.split(cvsSplitBy);
						featuresAndLength[0] = country.length;
					}
					count++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}  finally {
				if (bufferedReader != null) {
					try {
						bufferedReader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			featuresAndLength[1] = count;
			return featuresAndLength;
		}
	
		//Constructing and returning validation set of the specified file path.
	/*	private static int[][] constructValidationSet(String pathName) {
			int[] featuresAndLength = attributeValues(pathName);
			String csvFile = pathName;
			int[][] validationSet = new int[featuresAndLength[1]][featuresAndLength[0]];
			BufferedReader bufferedReader = null;
			String line = "";
			String cvsSplitBy = ",";
			try {
				bufferedReader = new BufferedReader(new FileReader(csvFile));
				int i = 0;
				int count = 0;
				while ((line = bufferedReader.readLine()) != null) {
					String[] lineParameters = line.split(cvsSplitBy);
					int j = 0;
					if (count == 0) {
						count++;
						continue;
					} else {
						for (String lineParameter : lineParameters) {
							validationSet[i][j++] = Integer.parseInt(lineParameter);
						}
					}
					i++;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (bufferedReader != null) {
					try {
						bufferedReader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return validationSet;
		}*/
		
		//loading the training data from the csv files
		private static void loadActualValues(String pathName, int[][] values, String[] featureNames, int[] done,int[] indexList, int features) {
			
			BufferedReader bufferedReader = null;
			String line = "";
			String cvsSplitBy = " ";
			for (int k = 0; k < features; k++)
			{
				done[k] = 0;
			}
			
			for (int k = 0; k < values.length; k++)
			{
				indexList[k] = k;
			}
			try {

				bufferedReader = new BufferedReader(new FileReader(pathName));
				int i = 0;
				while ((line = bufferedReader.readLine()) != null) {
					String[] lineParameters = line.split(cvsSplitBy);
					int j = 0;
					if (i == 0)
					{
						for (String lineParameter : lineParameters)
						{
							featureNames[j++] = lineParameter;
						}
					}
					else
					{
						for (String lineParameter : lineParameters)
						{
							values[i][j++] = Integer.parseInt(lineParameter);
						}
					}
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (bufferedReader != null) {
					try {
						bufferedReader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	
	
	// loading test data
		private static int[][] LoadTestData(String path)
		{
			BufferedReader reader = null;
			String line;
			int[] values = attributeValues(path);
			int[][] validationSet = new int[values[1]][values[0]];
			try {
				reader = new BufferedReader(new FileReader(path));
				int i = 0,count=0;
				while ((line = reader.readLine()) != null) {
					String[] param = line.split(" ");
					int j = 0;
					if (count == 0) {
						count++;
						continue;}
					else {
						for (String p : param) {
							validationSet[i][j++] = Integer.parseInt(p);}
					}
					i++;}
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			finally
			{
				if (reader != null)
				{
					try
					{
						reader.close();
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
			return validationSet;
		}
		
		//Print the constructed tree
		private static void printTree(TreeNode root, int printLines, String[] featureNames) {
			int printLinesForThisLoop = printLines;
			if (root.leaf) {
				System.out.println(" " + root.val);
				return;
			}
			for (int i = 0; i < printLinesForThisLoop; i++) {
				System.out.print("| ");
			}
			if (root.left != null && root.left.leaf && root.finalValue !=-1)
				System.out.print(featureNames[root.finalValue] + "= 0 :");
			else
				if(root.finalValue !=-1)
				System.out.println(featureNames[root.finalValue] + "= 0 :");

			printLines++;
			printTree(root.left, printLines, featureNames);
			for (int i = 0; i < printLinesForThisLoop; i++) {
				System.out.print("| ");
			}
			if (root.right != null && root.right.leaf&& root.finalValue !=-1)
				System.out.print(featureNames[root.finalValue] + "= 1 :");
			else
				if(root.finalValue !=-1)
				System.out.println(featureNames[root.finalValue] + "= 1 :");
			printTree(root.right, printLines, featureNames);
		}

		public static void main(String[] args) {

		if (args.length != 5) {
			System.out.println("Please check the command line arguments..");
			return;
		}

		int L = Integer.parseInt(args[0]);
		int[] featuresAndLength = attributeValues(args[1]);

		int[][] values = new int[featuresAndLength[1]][featuresAndLength[0]];
		String[] featureNames = new String[featuresAndLength[0]];
		int[] done = new int[featuresAndLength[0]];
		int[] indexList = new int[values.length];
		loadActualValues(args[1], values, featureNames, done, indexList, featuresAndLength[0]);
		TreeNode root1 = constructDecisionTree(null, values, done, featuresAndLength[0] - 1, indexList, null);
		TreeNode root2 = randomDecisionTree(null, values, done, featuresAndLength[0] - 1, indexList, null);
		TreeNode pruneTree1 = postPruneAlgorithm(args[2], L, root1, values, featuresAndLength[0] - 1);
		TreeNode pruneTree2 = postPruneAlgorithm(args[2], L, root2, values, featuresAndLength[0] - 1);
		System.out.println("Accuracy obtained for Tesing data for decision Tree using id3 algorithm " + CalculateAccuracy(args[3], root1));
        System.out.println("Accuracy obtained for Tesing data for pruned Tree using id3 algorithm " + CalculateAccuracy(args[3], pruneTree1));
        System.out.println("Accuracy obtained for Tesing data for decision Tree using random " + CalculateAccuracy(args[3], root2));
        System.out.println("Accuracy obtained for Tesing data for pruned Tree using random " + CalculateAccuracy(args[3], pruneTree2));
        System.out.println("Number of nodes in decision Tree using id3 algorithm "+ size(root1));
        System.out.println("Number of nodes in decision Tree by random  "+ size(root2));
        double dTreeAvgDepth = (double)sumOfLeafDepths(root1,1)/GetNoofLeafnodes(root1);
        System.out.println("average depth of decision Tree " + dTreeAvgDepth );
        double pTreeAvgDepth = (double)sumOfLeafDepths(root2,1)/GetNoofLeafnodes(root2);
        System.out.println("The average depth of pruned Tree " + pTreeAvgDepth );
		if(Integer.parseInt(args[4])==1)
		{
			System.out.println("\n\n/--------Decision Tree Using ID3--------/ ");
			printTree(pruneTree1, 0, featureNames);
			System.out.println("\n\n/--------Decision Tree Using random-------/ ");
			printTree(pruneTree2, 0, featureNames);
		}
	}	
}
