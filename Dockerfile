FROM harbor.fjy8018.top:8883/library/openjdk:8-jre
MAINTAINER fjy8018 fjy8018@gmail.com

COPY target/spring-V2.9.13.jar.jar /cms-springboot.jar
# 设置时区，默认为UTC
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' > /etc/timezone

ENTRYPOINT ["java","-jar","/cms-springboot.jar","--spring.profiles.active=k8s"]

EXPOSE 8080
