
library(e1071)
library(e1071)
library(rpart)
library(neuralnet)
spam_data <- read.csv("https://archive.ics.uci.edu/ml/machine-learning-databases/spambase/spambase.data", header=FALSE)
spam_dataframe <- as.data.frame(spam_data)

# parameters
#vals_Threshold <- c(0,0.0001,0.001,0.01,0.1)
#vals_Threshold
#arr_cost <- c(10,100,200)
#arr_cost
#arr_cp <- c(0.01,0.02,0.1)
#arr_cp
#arr_hidden <- c(1,3,2)

print("Summary of Naive Bayes" )
for (i in 1:5)
{
  cat("Running instance - ", i, "\n")
  maxs <-	apply(spam_dataframe,	MARGIN	=	2,	max)
  mins <- apply(spam_dataframe, MARGIN=2,min)
  instances <- sample(1:nrow(spam_data),size=0.8*nrow(spam_data))
  scaled <- spam_dataframe
  scaled =	 as.data.frame(scale(spam_dataframe,	center	=	TRUE,	scale	=	maxs	- mins))
  trainInstances <- scaled[instances,]
  trainInstances <- na.omit(trainInstances)
  testInstances <- scaled[-instances,]
  testInstances <- na.omit(testInstances)
  col_val <- as.integer(ncol(spam_dataframe))
  actual_values <- testInstances[,as.integer(col_val)]
  fit_NB <- naiveBayes(as.formula(paste0("as.factor(", colnames(spam_dataframe)[col_val], ") ~ .")), data = trainInstances, threshold = 0.1)
  nb_pred <- predict(fit_NB,testInstances,type="class")
  cmpr <- cbind(nb_pred,actual_values)
  correct_predictions <- length(cmpr[nb_pred==actual_values])/2
  total_predictions <- length(cmpr)/2
  nb_accuracy <- (correct_predictions/total_predictions)*100
  cat("accuracy of NB for threshold = ",0.1," is :" , nb_accuracy,"\n")
}


print("Summary for SVM" )
for (i in 1:5)
{
  cat("Running instance - ", i, "\n")

  maxs <-	apply(spam_dataframe,	MARGIN	=	2,	max)
  mins <- apply(spam_dataframe, MARGIN=2,min)
  instances <- sample(1:nrow(spam_data),size=0.8*nrow(spam_data))
  scaled <- spam_dataframe
  scaled =	 as.data.frame(scale(spam_dataframe,	center	=	mins,	scale	=	maxs	- mins))
  trainInstances <- scaled[instances,]
  trainInstances <- na.omit(trainInstances)
  testInstances <- scaled[-instances,]
  testInstances <- na.omit(testInstances)
  col_val <- as.integer(ncol(spam_dataframe))
  actual_values <- testInstances[,as.integer(col_val)]
  n <- names(trainInstances)
  f <- as.formula(paste0("as.factor(", colnames(spam_dataframe)[col_val], ") ~ ."))
  fit_SVM <- svm(f, data=trainInstances, cost	=	10,	gamma	=	1,kernel = "linear")
  svm_pred	<- predict(fit_SVM,	testInstances)
  cmpr <- cbind(svm_pred,actual_values)
  correct_predictions <- length(cmpr[svm_pred==actual_values])/2
  total_predictions <- length(cmpr)/2
  svm_accuracy <- (correct_predictions/total_predictions)*100
  cat("accuracy of SVM for Cost: ", 10, "is =" , svm_accuracy,"\n")
}

print("Summary for Decision Tree ")

for (i in 1:5)
{
  cat (" Running Sample -" ,i, "\n")

  maxs <-	apply(spam_dataframe,	MARGIN	=	2,	max)
  mins <- apply(spam_dataframe, MARGIN=2,min)
  instances <- sample(1:nrow(spam_data),size=0.8*nrow(spam_data))
  scaled <- spam_dataframe
  scaled =	 as.data.frame(scale(spam_dataframe,	center	=	TRUE,	scale	=	maxs	- mins))
  trainInstances <- scaled[instances,]
  trainInstances <- na.omit(trainInstances)
  testInstances <- scaled[-instances,]
  testInstances <- na.omit(testInstances)
  col_val <- as.integer(ncol(spam_dataframe))
  actual_values <- testInstances[,as.integer(col_val)]
  n <- names(trainInstances)
  f <- as.formula(paste0("as.factor(", colnames(spam_dataframe)[col_val], ") ~ ."))
  fit_DT <- rpart(f,  data = trainInstances,  method="class",parms	=	list(split	=	"information"),minsplit = 2, minbucket = 1)
  pred <- predict(fit_DT,testInstances, type="class")
  pruneDT <- prune(fit_DT,cp=0.1)
  DT_pred <- predict(pruneDT,testInstances, type="class")
  cmpr <- cbind(DT_pred,actual_values)
  correct_predictions <- length(cmpr[DT_pred==actual_values])/2
  total_predictions <- length(cmpr)/2
  DT_accuracy <- (correct_predictions/total_predictions)*100
  cat("accuracy of DT for cp = ",0.1," is = ",DT_accuracy,"\n")
}

print ("Summary for neural net ")
for (i in 1:5)
{
  cat("Running Sample - ",i,"\n")
  
  instances <- sample(1:nrow(spam_data),size=0.8*nrow(spam_data))
  trainInstances <- spam_dataframe[instances,]
  trainInstances <- na.omit(trainInstances)
  testInstances <- spam_dataframe[-instances,]
  testInstances <- na.omit(testInstances)
  col_val <- as.integer(ncol(spam_dataframe))
  n <- names(trainInstances)
  actual_values <- testInstances[,as.integer(col_val)]
  trainInstances<-sapply(trainInstances,as.numeric)
  testInstances<-sapply(testInstances,as.numeric)
  nu_net <- as.formula(paste(colnames(trainInstances)[as.integer(col_val)],"~", paste(n[!n %in% colnames(trainInstances)[as.integer(col_val)]], collapse = " + ")))
  net<-paste(c(n[!n %in% colnames(trainInstances)[as.integer(col_val)]]))
  nn_fit <- neuralnet(nu_net ,trainInstances, hidden = 1, threshold = 0.1)
  temp_test <- subset(testInstances, select = c(net))
  nn_pred <- compute(nn_fit, temp_test)
  results <- data.frame(actual = testInstances[,as.integer(col_val)], prediction = nn_pred$net.result)
  results$prediction <- round(results$prediction)
  nn_accuracy<-(sum(results[,2]==testInstances[,as.integer(col_val)])/nrow(testInstances))*100
  cat(" accuracy of NN for hidden layers - ",1," is = ",nn_accuracy,"\n")
  
}

print ("Summary for Perceptron ")
for (i in 1:5)
{
  cat (" Running Sample -" ,i, "\n")
  instances <- sample(1:nrow(spam_data),size=0.8*nrow(spam_data))
  trainInstances <- spam_dataframe[instances,]
  trainInstances <- na.omit(trainInstances)
  testInstances <- spam_dataframe[-instances,]
  testInstances <- na.omit(testInstances)
  col_val <- as.integer(ncol(spam_dataframe))
  n <- names(trainInstances)
  actual_values <- testInstances[,as.integer(col_val)]
  trainInstances<-sapply(trainInstances,as.numeric)
  testInstances<-sapply(testInstances,as.numeric)
  nu_net <- as.formula(paste(colnames(trainInstances)[as.integer(col_val)],"~", paste(n[!n %in% colnames(trainInstances)[as.integer(col_val)]], collapse = " + ")))
  net<-paste(c(n[!n %in% colnames(trainInstances)[as.integer(col_val)]]))
  p_fit <- neuralnet(nu_net ,trainInstances, hidden = 0, threshold = 0.0001)
  temp_test <- subset(testInstances, select = c(net))
  p_pred <- compute(p_fit, temp_test)
  results <- data.frame(actual = testInstances[,as.integer(col_val)], prediction = p_pred$net.result)
  results$prediction <- round(results$prediction)
  p_accuracy<-(sum(results[,2]==testInstances[,as.integer(col_val)])/nrow(testInstances))*100
  cat(" accuracy of Perceptron for threshold - ",0.0001," is = ",p_accuracy,"\n")
}

