FROM firekcc/java

MAINTAINER firekcc<lyk_1226@126.com>

# ARG VERSION=0.0.1-SNAPSHOT

RUN mkdir -p /usr/local/eureka-server

ADD ./target/eureka-server.jar /usr/local/eureka-server/

CMD java -jar -Xmx512m -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m -Duser.timezone=GMT+08 /usr/local/eureka-server/eureka-server.jar
