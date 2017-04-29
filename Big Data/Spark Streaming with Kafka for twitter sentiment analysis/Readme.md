### PartB: Sentiment Analysis of tweets using Apache Spark Streaming, Kafka

Steps I followed
	Collecting tweets in real-time using twitter app and kafka & zookeeper:  to download the tweets from twitter streaming API and push them to kafka queue, I have created a python script appListner.py. The script has my twitter authentication tokens (keys). Before running the script, I first installed some python libraries like
	
	kafka-python==0.9.4
	oauthlib==1.0.3
	requests==2.8.1
	requests-oauthlib==0.5.0
	six==1.10.0
	tweepy==3.3.0
	configparser==3.3.0.post2
	matplotlib==1.5.0
	
	And then start zookeeper service - then Start kafka service - then Create a topic named twitterstream in kafka. After this I ran my python script appListner.py 
	
	To analyze the sentiment of tweets I wrote a algorithm in python which compares the words in tweets with the positive and negative word i downloaded (which are "positive.txt" and "negative.txt" files)