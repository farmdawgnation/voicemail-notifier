FROM openjdk:11.0.1-jre-slim-stretch

ADD target/voicemail-notifier-*-standalone.jar /voicemail-notifier-standalone.jar

CMD ["java", "-Dfile.encoding=UTF-8", "-jar", "/voicemail-notifier-standalone.jar"]
