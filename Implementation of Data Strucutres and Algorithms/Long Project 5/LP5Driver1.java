
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LP5Driver1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in;
	boolean VERBOSE = false;

        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else { 
            in = new Scanner(System.in);
        }
	if (args.length > 1) {
	    VERBOSE = true;
	}
        Graph g = Graph.readGraph(in, false);   // read undirected graph from stream "in"
	      Timer time= new Timer();
       MxmCrdnltyMtchng test= new MxmCrdnltyMtchng();
       time.start();
       int result = test.mxmCrdMtchng(g);
       time.end();
       System.out.println(result);
       System.out.println(time);
    }
}
