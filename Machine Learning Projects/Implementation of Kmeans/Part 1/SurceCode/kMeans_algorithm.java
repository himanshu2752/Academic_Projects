
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class kMeans_algorithm {

	int numberRows ;
	static int k=3 ;
	static String filename = "";
	List<point_class> centroids = new ArrayList<>(); 
	List<point_class> pointsList ;
	ArrayList<cluster_class> clustersList ;
	ArrayList<point_class> points = new ArrayList<>();
	double max = Double.MAX_VALUE;
	
	public kMeans_algorithm()
	{
		this.clustersList = new ArrayList<>();
		this.pointsList = new ArrayList<>();
				
	}
	
	public static void main(String[] args)
	{
		k = Integer.parseInt(args[0]);
		filename = args[1];
		kMeans_algorithm km = new kMeans_algorithm();
		km.readFile(filename, k);
		km.randomCentroids(k);
		PrintWriter out;
		
		try {
			
			out = new PrintWriter(new FileWriter(args[2]));
			km.initialize(km.points, k,out);
			km.PrintandcalculateSSE(out);	
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}  	
		
	}
	
	public void readFile (String fileName, int k)
	{
		String s1 = null ;
       	numberRows = 1;    		
    	try
    	{
    		BufferedReader br = new BufferedReader(new FileReader(fileName));
			br.readLine();		
    
			while((s1 = br.readLine())!=null)
			{	  numberRows++;
			String[] splt = s1.split("\t");
			double x = Double.parseDouble(splt[1]);
			double y = Double.parseDouble(splt[2]);
			points.add(new point_class(x, y,numberRows-1));
			}
			br.close();
      
    	}
    	catch (IOException e)
    	{	
    		System.out.println("Error Reading File");
			e.printStackTrace();
		}
    	
	}
	public void randomCentroids(int k)
	{
		Random r = new Random();
    	int z = r.nextInt(numberRows -1) + 1;
		for (int i = 0 ; i < k ; i++)
		{		    	
    	centroids.add(points.get(z-i));    	
		}
	}
	
	public void initialize(List<point_class> l, int k, PrintWriter pw)
	{		
			for (int i = 0 ; i <k ; i++)
			{
				cluster_class cl = new cluster_class(i);
				clustersList.add(cl);
				cl.setCentroid(centroids.get(i));		    				
			}
	
			assignCluster();
			plotClusters(pw);
	}	

	private void plotClusters(PrintWriter pw) {
    	for (int i = 0; i <k ; i++) {
    		cluster_class c = clustersList.get(i);
    		c.print(pw);
    	}
    }
	
	public void PrintandcalculateSSE(PrintWriter pw)
	{
        int num_iteration = 0;
        int iteration = 0;
        while(true) {
        	for(cluster_class cluster : clustersList) 
        	{
        		cluster.clear();
        	}
        	//clearClusters();	// clearing cluster each time for new iteration
        	List<point_class> lastCentroids = getCentroids();
        	assignCluster();
        	findCentroids();
          	iteration++;
           	List<point_class> currentCentroids = getCentroids();
        	double distance = 0;
        	for(int i = 0; i < lastCentroids.size(); i++)
        	{
        		distance = distance + point_class.distance(lastCentroids.get(i),currentCentroids.get(i));
        	}
        	System.out.println(" \n");
        	pw.println("");
        	System.out.println("Iteration: " + iteration);
        	pw.println("Iteration: " + iteration);
        	System.out.println("Centroid distances: " + distance); // for testing when distance is zero exit the loop
        	
        	for (int i = 0; i <k ; i++) {
        		cluster_class c = clustersList.get(i);
        		c.print(pw);
        	}
        
        	num_iteration ++ ;       	
        	if(distance == 0 || num_iteration >= 25) { // distance = 0 can be removed if we want to fix the iteration to 25
        		break;
        	}
        	
        }
        double sse =  0 ;
        for (cluster_class c : clustersList)
        {
        	double dist = 0 ;        	
        	for ( point_class p : c.points )
        	{        		
        		dist = dist + Math.pow(point_class.distance(p, c.centroid), 2);
        	}
        	sse = sse + dist ;
        }
        System.out.println("SSE : " + sse);
        pw.println("SSE : " + sse);
    }
    
    private List<point_class> getCentroids() {
    	List<point_class> centroids = new ArrayList<>(k);
    	for(cluster_class cluster : clustersList) {
    		point_class aux = cluster.getCentroid();
    		point_class point = new point_class(aux.getX(),aux.getY(),aux.getID());
    		centroids.add(point);
    	}
    	return centroids;
    }
    
    private void assignCluster() {        
        double min = max; 
        int clusterNum = 0;                 
        double distance = 0.0; 
        
        for(point_class point : points) {
        	min = max;
            for(int i = 0; i < k; i++) {
            	cluster_class c = clustersList.get(i);
                distance = point_class.distance(point, c.getCentroid());
                if(distance <= min){
                    min = distance;
                    clusterNum = i;
                }
            }
            point.setCluster(clusterNum);
            clustersList.get(clusterNum).addPoint(point);
        }
    }
    
    private void findCentroids()
    {
    	
        for(int i= 0 ; i<clustersList.size() ; i++) 
        {
        	cluster_class cl = clustersList.get(i);
            double sumofX = 0;
            double sumofY = 0;
            ArrayList<point_class> list = cl.getPoint();
            int n_points = list.size();            
            
            for(point_class point : list)
            {
            	sumofX = sumofX + point.getX();
            	sumofY = sumofY + point.getY();
            }
            point_class centroid = cl.getCentroid();
            if(n_points > 0) 
            {
            	double newX = sumofX / n_points;
            	double newY = sumofY / n_points;
                centroid.setX(newX);
                centroid.setY(newY);
            }
        }
    }
	
}