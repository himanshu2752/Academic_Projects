import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.List;
import java.util.ArrayList;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.io.FileWriter;
import java.io.PrintWriter;

class tweets {
	String tweet_msg, id;
	double distance;
	tweets(String id, String tm) {
		this.id = id;
		tweet_msg = tm;
		this.distance = Double.MAX_VALUE;
	}
	tweets(String id) {
		this.id = id;
		tweet_msg = "";
	}
	public void add(String msg) {
		tweet_msg = msg;
	}
	public double calculateDistance(String other) {
		String[] string1 = tweet_msg.split(" ");
		String[] string2 = other.split(" ");
		Set<String> Tset1 = new TreeSet<>();
		Set<String> Tset2 = new TreeSet<>();
		for(String x : string1)
			Tset1.add(x);
		for(String x: string2)
			Tset2.add(x);
		int common_strings = 0;
		for(String x : Tset1) {
			if(Tset2.contains(x))
				common_strings++;
		}
		if(string2.length>string1.length){
			String[] temp = string2;
			string2 = string1;
			string1 = temp;
		}
		for(int i=0;i<string1.length;i++){
			for(int j=0;j<string2.length;j++){
				if(string1[i]==string2[j])
					common_strings++;
			}
		}
		//union
		Set<String> total_strings = new TreeSet<>();
		total_strings.addAll(Tset1);
		total_strings.addAll(Tset2);
		return (1.0 - (((double) common_strings)/total_strings.size()));//Jaccard distance
	}
}

public class Twitter {

	public static double squared_error(ArrayList<ArrayList<tweets>> clusters_list) {
		double sum_squared_error = 0.0;
		for(int i=0;i<clusters_list.size();i++) {
			ArrayList<tweets> cluster = clusters_list.get(i);
			tweets cluster_tweet = cluster.get(0);
			for(int j=0;j<cluster.size();j++){
				tweets data_tweet = cluster.get(j);
				sum_squared_error += Math.pow(data_tweet.calculateDistance(cluster_tweet.tweet_msg), 2);
		}
		}
		return sum_squared_error;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		JSONParser parser = new JSONParser();
		int k = Integer.parseInt(args[0]);
		File input_clusters_file = new File(args[1]);
		File input_tweetss_File = new File(args[2]);
		File outputFile = new File(args[3]);
		Scanner input_clusters = new Scanner(input_clusters_file);
		ArrayList<ArrayList<tweets>> clusters_list = new ArrayList<>();
		while(input_clusters.hasNextLine()) {
			ArrayList<tweets> temp = new ArrayList<>();
			temp.add(new tweets(input_clusters.nextLine()));
			clusters_list.add(temp);
		}
		Scanner inputtweetss = new Scanner(input_tweetss_File);
		ArrayList<tweets> tweetsList = new ArrayList<>();
		try {
			while(inputtweetss.hasNextLine()) {
				JSONObject obj = (JSONObject) parser.parse(inputtweetss.nextLine());
				tweetsList.add(new tweets(Long.toString((Long) obj.get("id")), (String) obj.get("text")));
			}
		} catch(ParseException e) {
			e.printStackTrace();
		}
		for(tweets t : tweetsList)
			t.tweet_msg = t.tweet_msg.replaceAll("\\n"," ");
		for(ArrayList<tweets> s : clusters_list) {
			if(s.get(0).id.charAt(s.get(0).id.length() - 1) == ',')
				s.get(0).id = s.get(0).id.substring(0,s.get(0).id.length() - 1);
			for(tweets j : tweetsList) {
				if(s.get(0).id.compareTo(j.id) == 0) {
					s.get(0).add(j.tweet_msg);
					break;
				}
			}
		}
		double minimum_Distance, actual_distance;
		ArrayList<tweets> minimum_cluster = new ArrayList<>();
		for(int i = 0; i < 25; i++) {
			for(tweets t : tweetsList) {
				minimum_Distance = Double.MAX_VALUE;
				for(ArrayList<tweets> c : clusters_list) {
					actual_distance = t.calculateDistance(c.get(0).tweet_msg);
					if(actual_distance < minimum_Distance) {
						t.distance = actual_distance;
						minimum_Distance = actual_distance;
						minimum_cluster = c;
					}
				}
				minimum_cluster.add(t);
			}
			if(i == 24)
				break;
			//Update Lists
			ArrayList<ArrayList<tweets>> temporary_clusters = new ArrayList<>();
			tweets temporary_tweet = null;
			for(int j=0;j<clusters_list.size();j++) {
				ArrayList<tweets> clust=clusters_list.get(j);
				minimum_Distance = Integer.MAX_VALUE;
				for(int h=1; h<clust.size(); h++) {
					tweets t = clust.get(h);
					if(t.distance < minimum_Distance) {
						minimum_Distance = t.distance;
						temporary_tweet = t;
					}
				}
				ArrayList<tweets> temporary_list = new ArrayList<>();
				if(temporary_tweet == null){
					temporary_list.add(clust.get(0));

				}
				else
				temporary_list.add(new tweets(temporary_tweet.id, temporary_tweet.tweet_msg));

				temporary_clusters.add(temporary_list);
			}
			clusters_list = temporary_clusters;
		}
		int i = 1;
		FileWriter fw = new FileWriter(outputFile);
		PrintWriter pw = new PrintWriter(fw);
		for(int h=0;h<clusters_list.size();h++) {
			ArrayList<tweets> clust = clusters_list.get(h);
			pw.println("Cluster " + i + ": ");
			for(int j=1;j<clust.size();j++){
				tweets t = clust.get(j);
				pw.print(t.id + ", ");
			}
			pw.println();
			i++;
		}
		pw.println();
		pw.println("SSE : " + squared_error(clusters_list));
		pw.close();
	}
}
