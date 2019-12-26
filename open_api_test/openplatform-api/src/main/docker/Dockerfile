#FROM frolvlad/alpine-oraclejdk8:slim
FROM registry.bizport.cn/com_xy/jdk1.8
VOLUME /tmp
ADD xy-boot-openplatform-api-1.0.0-SNAPSHOT.jar app.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dfile.encoding=UTF8","-Duser.timezone=GMT+08","-jar","/app.jar"]
EXPOSE 9901