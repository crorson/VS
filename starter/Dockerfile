#Specification of base image
FROM openjdk:11-jre-slim
#Execution of command (create diretory)
#RUN mkdir -p /opt/starter-app
#Specification of base execution directory
#WORKDIR /opt/starter-app
#Copy contents into the container
COPY target/starter-0.1.jar /opt/starter-app.jar
#Specification of container process
CMD ["java", "-jar", "/opt/starter-app.jar"]