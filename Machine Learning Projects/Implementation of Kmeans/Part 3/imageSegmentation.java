
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
/**
 * 
 * @author himanshu & Vivek
 * @utd 
 */
public class imageSegmentation {

	public final static int k = 5;
	
	public static void main(String[] args)
	{
		imageSegmentation classObj = new imageSegmentation();
		double[][] distanceMatrix ;
		int[] points;
		int[] tempArr;
		int x=0,y = 0;
		String name = "";
		ArrayList<BufferedImage> bImages = new ArrayList<>();
		try {
			
			File file1 = new File("image3.jpg");
			File file2 = new File("image4.jpg");
			File file3 = new File("image5.jpg");
			
			BufferedImage bi1 = ImageIO.read(file1);
			bImages.add(bi1);
			BufferedImage bi2 = ImageIO.read(file2);
			bImages.add(bi2);
			BufferedImage bi3 = ImageIO.read(file3);
			bImages.add(bi3);
			
			for (int i = 0 ; i< bImages.size();i++)
			{
				int temp = 0 ;
				BufferedImage bi = bImages.get(i);
				x = bi.getWidth();
				y = bi.getHeight();
				int[] arr = new int[x*y];
				BufferedImage Bimage = new BufferedImage(x, y, bi.getType());
				Graphics2D graphic = Bimage.createGraphics();
				graphic.drawImage(Bimage, x, y, null);
				
				for (int j = 1 ; j<= x ; j++)
				{
					for ( int l = 1 ; l <= y ; l++)
					{						
						arr[temp] = bi.getRGB(j-1, l-1);
								temp++;
					}
				}
				points = new int[k];
				 tempArr = new int[k];
				distanceMatrix = new double[arr.length][2];
				classObj.kMeans_algorithm(arr,distanceMatrix,points,tempArr);				
				temp = 0;
				for (int k = 0; k < x; k++)
				{
					for (int l = 0; l < y; l++) 
					{
						Bimage.setRGB(k, l, arr[temp]);
						temp++;
					}
				}
				if (i == 0)
					 name = file1.getName();
				else if ( i == 1)
					name = file2.getName();
				else if ( i == 2)
					name = file3.getName();
				else
					name = "newImage";
				
				ImageIO.write(Bimage, "jpg", new File("clusteredImages/"+name+".jpg"));
			}
			
			File output = new File("clusteredImages");
			 output.mkdir();			
			
		} 
		catch (Exception e) {			
			e.printStackTrace();		
		}
	}
	
	public void kMeans_algorithm(int[]ColorArr,double[][] distanceMatrix,int[] pointsArr,int[] tempArray) {		

		// Taking random k points and adding in the array
		Random r = new Random();
		int id = r.nextInt(ColorArr.length-1)+1;
		for (int i = 0; i < k; i++) {			
			pointsArr[i] = ColorArr[id-i];
		}
		
		for (int i = 0; i < distanceMatrix.length; i++) {
			distanceMatrix[i][0] = Double.MAX_VALUE;
			distanceMatrix[i][1] = Double.MIN_VALUE;
		}
		
		while (true)
		{
			int counter = 0;
			for (int i = 0; i < ColorArr.length; i++)
			{
				int point = ColorArr[i];

				for (int j = 0; j < k; j++)
				{
					int kpoint = pointsArr[j];					
					double distance = getDistance(point, kpoint);
					
					if ( distance <= distanceMatrix[i][0])
					{
						distanceMatrix[i][0] = distance;
						distanceMatrix[i][1] = j;
					}
					else
					{
						continue;
					}
				}
			}
			
			for (int j = 0; j < k; j++) 
			{			
				int sumRed =0 ,sumBlue = 0,sumGreen=0,total = 0;
				
				for (int n = 0; n < ColorArr.length; n++) {
					if (distanceMatrix[n][1] == j)
					{
						Color c = new Color(ColorArr[n]);
						sumRed = sumRed + c.getRed();
						sumBlue = sumBlue + c.getBlue();
						sumGreen = sumGreen + c.getGreen();
						total = total + 1;
					}
				}

				tempArray[j] = pointsArr[j];
				if (total != 0) {
					int redRatio = sumRed/total;
					int blueRatio = sumBlue/total ;
					int greenRatio = sumGreen/total;
					Color color = new Color(redRatio, blueRatio, greenRatio);
					pointsArr[j] = color.getRGB();
				}
			}		
			
			for (int j = 0; j < k; j++) 
			{
				if (pointsArr[j] == tempArray[j])
				{
					counter++;
				}
			}

			if (counter == k) 
			{
				break;
			}
		}

		for (int i = 0; i < ColorArr.length; i++) 
		{
			for (int j = 0; j < k; j++)
			{
				if (distanceMatrix[i][1] == j)
				{
					ColorArr[i] = pointsArr[j];
					break;
				}
			}
		}

	}

	private double getDistance(int point1, int point2) {
		double dist = 0;
		int red1,blue1,green1,red2,blue2,green2 ;
		Color color1 = new Color(point1);
		Color color2 = new Color(point2);

		 red1 = color1.getRed();
		 red2 = color2.getRed();
		 blue1 = color1.getBlue();
		 blue2 = color2.getBlue();
		 green1 = color1.getGreen();	
		 green2 = color2.getGreen();

		dist = Math.sqrt(Math.pow((red1 - red2), 2) + Math.pow((blue1 -blue2), 2) + Math.pow((green1-green2), 2));
				
		return dist;
	}

}
