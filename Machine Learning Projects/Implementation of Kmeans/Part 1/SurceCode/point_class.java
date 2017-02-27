
public class point_class {

	 private double x = 0;
	    private double y = 0;
	    private int clusterID = 0;
	    private int id = 0;

	    public point_class(double x, double y , int id)
	    {
	        this.setX(x);
	        this.setY(y);
	       this.setID(id);
	    }
	    public void setID(int i)
	    {
	    	this.id = i ;
	    }
	    public int getID()
	    {
	    	return this.id;
	    }
	    
	    public void setX(double x) {
	        this.x = x;
	    }
	    
	    public double getX()  {
	        return this.x;
	    }
	    
	    public void setY(double y) {
	        this.y = y;
	    }
	    
	    public double getY() {
	        return this.y;
	    }
	    
	    public void setCluster(int clus) {
	        this.clusterID = clus;
	    }
	    
	    public int getCluster() {
	        return this.clusterID;
	    }	    
	   
	    public static double distance(point_class p, point_class centroid) {
	        return Math.sqrt(Math.pow((centroid.getY() - p.getY()), 2) + Math.pow((centroid.getX() - p.getX()), 2));
	    }	 
	}
