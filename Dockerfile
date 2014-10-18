# DOCKER-VERSION 1.1.2
FROM tutum/ubuntu
MAINTAINER Anshuman Dutta <andutta@gmail.com>
FROM ubuntu:trusty
RUN apt-get update && \
    apt-get install -y mongodb pwgen && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*
    
RUN mkdir -p /data/db
VOLUME /data/db

# Add run scripts
# ADD run.sh /run.sh
# ADD set_mongodb_password.sh /set_mongodb_password.sh
# RUN chmod 755 ./*.sh

EXPOSE 27017
EXPOSE 28017
FROM komu/tomcat8-java8
ADD ./build/libs/tangy-mongo-1.0.jar /tangy-mongo-1.0.jar
CMD ["java -jar tangy-mongo-1.0.jar"]
EXPOSE 7001

# CMD ["/run.sh"]
