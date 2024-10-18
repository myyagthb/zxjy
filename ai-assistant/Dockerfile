
FROM ubuntu:22.04 as base

RUN apt-get update && apt-get install -y openjdk-21-jdk iputils-ping net-tools telnet redis-server

# run service mysql start 
run service redis start    

WORKDIR /app
copy ./target/ai-assistant-0.0.1-SNAPSHOT.jar .
EXPOSE 8888
CMD ["java","-jar","ai-assistant-0.0.1-SNAPSHOT.jar"]