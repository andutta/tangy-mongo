# DOCKER-VERSION 1.1.2
FROM dockerfile/java:oracle-java8
MAINTAINER andutta@gmail.com
EXPOSE 9099
ADD build/libs/tangy-mongo-1.0.jar /data/tangy-mongo-1.0.jar
CMD java -jar tangy-mongo-1.0.jar
