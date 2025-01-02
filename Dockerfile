FROM  amazoncorretto:17.0.7-alpine
EXPOSE 8089
ADD target/Cab-login-0.0.1-SNAPSHOT.war Cab-login-0.0.1-SNAPSHOT.war
ENTRYPOINT [ "java","-jar","/Cab-login-0.0.1-SNAPSHOT.war" ]
