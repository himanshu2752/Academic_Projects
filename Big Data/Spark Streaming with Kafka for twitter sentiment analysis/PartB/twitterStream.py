from pyspark.streaming import StreamingContext
from pyspark.streaming.kafka import KafkaUtils
from pyspark import SparkConf, SparkContext

import operator
import numpy as np
import matplotlib.pyplot as plt


def main():
    conf = SparkConf().setMaster("local[2]").setAppName("Streamer")
    sc = SparkContext(conf=conf)

    # Creating a streaming context with batch interval of 10 sec
    ssc = StreamingContext(sc, 10)
    ssc.checkpoint("checkpoint")
    positiveWords = load_wordlist("positive.txt")
    negativeWords = load_wordlist("negative.txt")
    counts = stream(ssc, positiveWords, negativeWords, 100)
    showCounts(counts)

	
    #plotting the counts of positive and negative tweets for each timestep.    
def showCounts(counts):
    
    positiveCounts = []
    negativeCounts = []
    time = []

    for val in counts:
	positiveTuple = val[0]
	positiveCounts.append(positiveTuple[1])
	negativeTuple = val[1]
	negativeCounts.append(negativeTuple[1])

    for i in range(len(counts)):
	time.append(i)

    posLine = plt.plot(time, positiveCounts,'bo-', label='Positive')
    negLine = plt.plot(time, negativeCounts,'go-', label='Negative')
    plt.axis([0, len(counts), 0, max(max(positiveCounts), max(negativeCounts))+50])
    plt.xlabel('Time step')
    plt.ylabel('Word count')
    plt.legend(loc = 'upper left')
    plt.show()

	
def load_wordlist(filename):
    
    words = {}
    f = open(filename, 'rU')
    text = f.read()
    text = text.split('\n')
    for line in text:
        words[line] = 1
    f.close()
    return words



def updateFunction(newValues, runningCount):
    if runningCount is None:
       runningCount = 0
    return sum(newValues, runningCount) 


def sendRecord(record):
    connection = createNewConnection()
    connection.send(record)
    connection.close()


def stream(ssc, positiveWords, negativeWords, duration):
    kstream = KafkaUtils.createDirectStream(
    ssc, topics = ['twitterstream'], kafkaParams = {"metadata.broker.list": 'localhost:9092'})
    tweets = kstream.map(lambda x: x[1].encode("ascii", "ignore"))

    words = tweets.flatMap(lambda line:line.split(" "))
    positive = words.map(lambda word: ('Positive', 1) if word in positiveWords else ('Positive', 0))
    negative = words.map(lambda word: ('Negative', 1) if word in negativeWords else ('Negative', 0))
    allSentiments = positive.union(negative)
    sentimentCounts = allSentiments.reduceByKey(lambda x,y: x+y)
    runningSentimentCounts = sentimentCounts.updateStateByKey(updateFunction)
    runningSentimentCounts.pprint()
    
    
    counts = []
    sentimentCounts.foreachRDD(lambda t, rdd: counts.append(rdd.collect()))
    
    
    ssc.start() 
    ssc.awaitTerminationOrTimeout(duration)
    ssc.stop(stopGraceFully = True)

    return counts


if __name__=="__main__":
    main()