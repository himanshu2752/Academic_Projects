import org.apache.spark.SparkContext;
import org.apache.spark.SparkConf;
import org.apache.spark.mllib.clustering.{ KMeans, KMeansModel }
import org.apache.spark.mllib.linalg.Vectors

object hw3Q1 {
  
  val data = sc.textFile("itemusermat")

  def main(args: Array[String]) {

    val parsedData = data.map(d => Vectors.dense(d.split(' ').drop(1).map(_.toDouble))).cache()
    
    val clusters = KMeans.train(parsedData, 10, 100)

    val predict = data.map { line => (line.split(' ')(0), clusters.predict(Vectors.dense(line.split(' ').drop(1).map(_.toDouble)))) }

    val movie = sc.textFile("movies.dat")
    val moviesData = movie.map(line => (line.split("::"))).map(p => (p(0), (p(1) + " , " + p(2))))

    val joinedResult = predict.join(moviesData)
    val groupedData = joinedResult.map(p => (p._2._1, (p._1, p._2._2))).groupByKey()

    val result = groupedData.map(p => (p._1, p._2.toList))

    val output = result.map(p => (p._1, p._2.take(5)))
    println("clusterNo , First 5 Movies list in the cluster")
    output.foreach(p => println("cluster: " + p._1 + " , " + p._2.mkString("")))
  }
}
hw3Q1.main(Array())