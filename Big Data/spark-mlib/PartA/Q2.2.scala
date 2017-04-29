import org.apache.spark.mllib.classification.NaiveBayes
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.SparkContext;
import org.apache.spark.SparkConf;

object Hw3Q2_2 {
  
  val myData = sc.textFile("glass.data")

  def main(args: Array[String]) {
    val classifyData = myData.map { line =>
      val splt = line.split(',')
      LabeledPoint(splt(10).toDouble, Vectors.dense(splt(0).toDouble, splt(1).toDouble, splt(2).toDouble, splt(3).toDouble, splt(4).toDouble, splt(5).toDouble, splt(6).toDouble, splt(7).toDouble, splt(8).toDouble, splt(9).toDouble))
    }

    val splits = classifyData.randomSplit(Array(0.6, 0.4), seed = 11L)
    val trainingData = splits(0)
    val testData = splits(1)

    val model = NaiveBayes.train(trainingData, lambda = 1.0)

    val predictionAndLabel = testData.map(p => (model.predict(p.features), p.label))
    val tmp = 100.0 * predictionAndLabel.filter(x => x._1 == x._2).count()
	val NBaccuracy = tmp / testData.count()
    println("Accuracy of Naive Bayes is : " + NBaccuracy + "%")
  }
}
Hw3Q2_2.main(Array())