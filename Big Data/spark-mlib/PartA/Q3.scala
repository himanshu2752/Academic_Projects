import org.apache.spark.mllib.classification.NaiveBayes
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.SparkContext;
import org.apache.spark.SparkConf;
import org.apache.spark.mllib.recommendation.ALS
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel
import org.apache.spark.mllib.recommendation.Rating


object Hw3Q3 {
  
  val data = sc.textFile("ratings.dat")

  def main(args: Array[String]) {
    
val ratings = data.map(_.split("::") match { case Array(userID, movieID, ratings,timestamp) =>
  Rating(userID.toInt, movieID.toInt, ratings.toDouble)
})
val split = ratings.randomSplit(Array(0.6, 0.4), seed = 11L)
val train=split(0)
val test=split(1)
val model = ALS.train(train, 5, 10, 0.01)

val usersProducts = test.map { case Rating(user, product, rate) =>
  (user, product)
}
val predicts =
  model.predict(usersProducts).map { case Rating(user, product, rate) =>
    ((user, product), rate)
  }
val ratesAndPreds = ratings.map { case Rating(user, product, rate) =>
  ((user, product), rate)
}.join(predicts)
val MSE = ratesAndPreds.map { case ((user, product), (r1, r2)) =>
  val err = (r1 - r2)
  err * err
}.mean()
println("Mean Squared Error is = " + MSE) }
}

