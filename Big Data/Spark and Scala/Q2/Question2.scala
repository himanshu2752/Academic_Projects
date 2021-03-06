val reviewData = sc.textFile("review.csv")
val businessData = sc.textFile("business.csv")
val avgRatingData = reviewData.map(line => line.split("::")).map(row=>(row(2),row(3).toDouble)).mapValues(a => (a, 1)).reduceByKey((a, b) => (a._1 + b._1, a._2 + b._2)).mapValues(b => b._1 / b._2)
val top10Rev = avgRatingData.sortBy(-_._2).collect().take(10)
val top10=sc.parallelize(top10Rev)
val businessDetails = businessData.map(line => line.split("::")).map(row=>(row(0),(row(1),row(2)))).distinct()
val joinedData = businessDetails.join(top10)
joinedData.collect.foreach(println)