1. Developed on eclipse with jdk version jdk1.8.0_102

2. following are the imports -
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;


3. We have 1 java file - 
-imageSegmentation.java

4. How to run the code -
- Put all the three images and sourcecode(java file) in same folder and create a folder named clusteredImages in the same directory
- After going in that folder from command line 
	- javac imageSegmentation.java
	
- then to run the program - java imageSegmentation 

5. processed images with the same name will be generated in the clusteredImages folder.