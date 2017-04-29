import json
from kafka import SimpleProducer, KafkaClient
import tweepy
import configparser

""" A class to read the twitter stream and push it to Kafka"""
class twitterListner(tweepy.StreamListener):
    

    def __init__(self, api):
        self.api = api
        super(tweepy.StreamListener, self).__init__()
        client = KafkaClient("localhost:9092")
        self.producer = SimpleProducer(client, async = True,
                          batch_send_every_n = 1000,
                          batch_send_every_t = 10)
							
	
    
	# Asynchronously pushing data to kafka queue
    def on_status(self, status):
       
        msg =  status.text.encode('utf-8')
        try:
            self.producer.send_messages(b'twitterstream', msg)
        except Exception as e:
            print(e)
            return False
        return True

    def on_error(self, status_code):
        print("E01: Error in kafka producer")
        return True # not killing stream

    def on_timeout(self):
        return True


if __name__ == '__main__':

    consumer_key = 'JZY7KTazAz1NwKbJWUemqr5iu'
    consumer_secret =  'vBqgUdWXxLxbCLaEKaWVqEKLPdIwuYLprUFiU0xQOK6jKpyDTk'
    access_token = '52938773-q5WpydcLIVugvp2ToCDcpACnflZME149c8Z9pETzy'
    access_secret = 'RQuTKyZoinsC9shD1onuTXXYGU3Fb7uPUZ1H88vgZ9wuu'
    
    auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
    auth.set_access_token(access_token, access_secret)
    api = tweepy.API(auth)

    # Creating stream and binding the listener
    stream = tweepy.Stream(auth, listener = twitterListner(api))
	
    stream.filter(locations=[-180,-90,180,90], languages = ['en'])