FROM dockerpull.org/openjdk:17-jdk-alpine
EXPOSE 8026
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
#RUN  apk add --no-cache fontconfig ttf-dejavu font-bitstream-vera ttf-freefont
RUN  apk add --no-cache fontconfig ttf-dejavu ttf-liberation ttf-freefont

# 添加环境变量
ENV JAVA_OPTS="-Xms512M -Xmx2g -Djava.awt.headless=true"
VOLUME /tmp
COPY target/smart-task-0.0.1-SNAPSHOT.jar /smart-task-0.0.1-SNAPSHOT.jar

# 添加 client.wallet.mysql.server 的解析


ENTRYPOINT java ${JAVA_OPTS} -jar /smart-task-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod

