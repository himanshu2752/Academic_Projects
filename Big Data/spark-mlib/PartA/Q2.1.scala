import org.apache.spark.SparkContext
import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.tree.configuration.Algo._
import org.apache.spark.mllib.tree.impurity.Gini

object hw3Q2_1 {
  
  val data = sc.textFile("glass.data")
  def main(args: Array[String]) {
    val classifyData = data.map { line =>
      val splt = line.split(',')
      LabeledPoint(splt(10).toDouble, Vectors.dense(splt(0).toDouble, splt(1).toDouble, splt(2).toDouble, splt(3).toDouble, splt(4).toDouble, splt(5).toDouble, splt(6).toDouble, splt(7).toDouble, splt(8).toDouble, splt(9).toDouble))
    }

    val splits = classifyData.randomSplit(Array(0.6, 0.4), seed = 11L)
    val train = splits(0)
    val test = splits(1)
    val features = Map[Int, Int]()

    val model = DecisionTree.trainClassifier(train, 8, features, "gini", 5, 100)

    val labelAndPreds = test.map { point =>
      val prediction = model.predict(point.features)
      (point.label, prediction)
    }
    val accuracy = 100.0 * labelAndPreds.filter(r => r._1 == r._2).count.toDouble / test.count
    println("Accuracy of Decision Tree is= " + accuracy + "%")
  }
}
hw3Q2_1.main(Array())