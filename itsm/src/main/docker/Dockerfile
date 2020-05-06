# Docker image for springboot file run
# VERSION 1.0
# Author: yangliguang
# based on centos7,server-jre-8u251-linux-x64
# 基础镜像使用java
FROM jdk1.8:8u251
# 作者
MAINTAINER yangliguang <yanglg1992@163.com>
# VOLUME 指定了临时文件目录
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/app/itsm
VOLUME /app/itsm
# 将jar包添加到容器中并更名为itsm.jar
ADD itsm-1.0.jar itsm.jar
# 运行jar包
RUN bash -c 'touch /itsm.jar'
# ENTRYPOINT 执行项目 itsm.jar。为了缩短 Tomcat 启动时间，添加一个系统属性指向 “/dev/./urandom” 作为 Entropy Source
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","itsm.jar","--spring.profiles.active=release"]