#Copart Coding Excercise

- ###Copart_WebService - I made the REST webservice using Jersey libraries in Java on eclipse IDE. I have implemented the CRUD operations on a vehicle where we can add, delete, update and access the vehicles through get operations. We can access the vehicle through its model,make,year,class or vehicle ID. I have added some automated test cases also. I ran it on my local Tomcat server and some sample path are given in "URIs and Test Cases" text file.
- How to Run - I have run it on Apache tomcat 6.0. I have included a war file as "copart_WebService.war". It can be deoployed and run on Apache. Or you can Run it through Eclipse by downloading it along with all libraries I have added and Right right click and run on server then the sample URI I have given in "URIs and Test Cases" can be used on any browser to use web service.

- ###pdfToText - To extract text from pdf file and get the vehicle id and license plate information.

- ###copartStringToInetger - Converts string to integer without using inbuilt java libraries.

-###Most appropriate location -  I was not able to complete this project. Steps I did - A separate database is created with two tables table in Oracle SQL for Customer locations and customer details.Copart_L ontains copart locations(Taken all copart locations from Copart website).
We fetch the customer zip code related to that customerId and then find the most appropriate location.
 This is done by first storing all the zip codes and their addresses in HashMap with zip code as Key and then finding the closest zip (by min difference) and populate hashmap value (which is address).