//val reviewPath = "/hxp151330/Assignment1/review.csv"
//val businessPath = "/hxp151330/Assignment1/business.csv"

val business = sc.textFile("business.csv")
val review = sc.textFile("review.csv")

val filteredBusiness = business.filter(line => line contains "Stanford, CA")
val splitBusiness = filteredBusiness.map(line => line.split("::"))
val businesses = splitBusiness.map(line => (line(0), ""))
businesses.toDF("businessId", "values")

val splitReview = review.map(line => line.split("::"))
val reviews = splitReview.map(line => (line(2),line(1) + "\t" + line(3)))
reviews.toDF("businessId", "user_ratings")
val joinedData = businesses.join(reviews)
val result = joinedData.map(_._2._2).distinct

result.foreach(println)
