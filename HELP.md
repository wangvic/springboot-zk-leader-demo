# Read Me First
The following was discovered as part of building this project:

* The original package name 'dev.vicwang.springboot-zk-leader-demo' is invalid and this project uses 'dev.vicwang.springbootzkleaderdemo' instead.

# Getting Started

Docker run command: (environment variable ZOO_TICK_TIME=100 to speed up leader election / fail-over process)

docker run --hostname=1df80d520cbf --mac-address=02:42:ac:11:00:02 --env=ZOO_TICK_TIME=100 --env=PATH=/opt/java/openjdk/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/apache-zookeeper-3.8.1-bin/bin --env=JAVA_HOME=/opt/java/openjdk --env=LANG=en_US.UTF-8 --env=LANGUAGE=en_US:en --env=LC_ALL=en_US.UTF-8 --env=JAVA_VERSION=jdk-11.0.18+10 --env=ZOO_CONF_DIR=/conf --env=ZOO_DATA_DIR=/data --env=ZOO_DATA_LOG_DIR=/datalog --env=ZOO_LOG_DIR=/logs --env=ZOO_INIT_LIMIT=5 --env=ZOO_SYNC_LIMIT=2 --env=ZOO_AUTOPURGE_PURGEINTERVAL=0 --env=ZOO_AUTOPURGE_SNAPRETAINCOUNT=3 --env=ZOO_MAX_CLIENT_CNXNS=60 --env=ZOO_STANDALONE_ENABLED=true --env=ZOO_ADMINSERVER_ENABLED=true --env=ZOOCFGDIR=/conf --volume=/data --volume=/datalog --volume=/logs --workdir=/apache-zookeeper-3.8.1-bin -p 2181:2181 --label='org.opencontainers.image.ref.name=ubuntu' --label='org.opencontainers.image.version=22.04' --runtime=runc -d zookeeper:latest

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.2/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.2/gradle-plugin/reference/html/#build-image)
* [Apache Zookeeper Quick Start](https://docs.spring.io/spring-cloud-zookeeper/docs/current/reference/html/#distributed-configuration-usage)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.2/reference/htmlsingle/#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

