
Insall all the below packages
1. Packages required = grid,mass,rpart,e1071,class,neuralnet,nnet

--- How to Run ----
2.Open classifiers_for_choosing_best_parameters.r file.
3.Put the url link (of the dataset you want to analyze, we used spam.data)  in 
spam_data <- read.csv("URL",header=False) 
4.Run the R code.
5.All the results will be displayed in the terminal, which can used to analyse for further studies.

Please note - since we are using loops to change our parameters and for each parameter we running the classifiers 5 times, so to run the whole code it takes time. Specially for Neural Net and SVM

There are two R files - one where we are taking different attributes by for loop and some parameters we changed manually each time. Another file is where we set the best attributes according to our results.


