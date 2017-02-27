import java.io.PrintWriter;
import java.util.ArrayList;

public class cluster_class {
	public ArrayList<point_class> points;
	public point_class centroid;
	public int id;

	public cluster_class(int id) {
		this.id = id;
		this.points = new ArrayList<point_class>();
		this.centroid = null;
	}
	 public ArrayList<point_class> getPoint()
	 {
		 return points;
	 }
	 
	 public void addPoint(point_class point) {
			points.add(point);
		}
	 
		public void setPoints(ArrayList<point_class> points) {
			this.points = points;
		}
	 
		public point_class getCentroid() {
			return centroid;
		}
	 
		public void setCentroid(point_class centroid) {
			this.centroid = centroid;
		}
	 
		public int getId() {
			return id;
		}
		
		public void clear() {
			points.clear();
		}
		public void print(PrintWriter pw)
		{		
			try {
				
				System.out.println("Cluster: "+" K_"+ getId());
				pw.println("Cluster: "+" K_"+ getId());
			//	System.out.println("Centroid: Point" + centroid);
				System.out.println("Points: ");
				pw.print("Points: ");
				for(point_class p : points) {
					System.out.print(p.getID()+",");
					pw.print(p.getID()+",");
				}
				System.out.println("");	
				pw.println("");				
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		
		
}
}
