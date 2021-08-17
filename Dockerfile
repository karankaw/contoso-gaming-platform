FROM ubuntu
RUN apt update
RUN apt install -y openjdk-8-jdk
WORKDIR /home/ec2-user
COPY contoso-gaming-platform-0.0.1-SNAPSHOT.jar .
#CMD pwd && ls && whoami && docker --version
EXPOSE 8080
ENTRYPOINT ["nohup", "java", "-jar", "contoso-gaming-platform-0.0.1-SNAPSHOT.jar", "2>1", "&"]