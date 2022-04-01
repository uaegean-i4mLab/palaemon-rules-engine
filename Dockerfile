FROM amazoncorretto:11-alpine-jdk
MAINTAINER Nikos Triantafyllou
ADD ./target/rulesengine-0.0.1-SNAPSHOT.jar app.jar
# Add a /app volume
VOLUME ["/store"]
ENV JAVA_OPTS=""
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=container -jar /app.jar" ]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-jar","/app.jar"]
#EXPOSE 8080