FROM openjdk:8u162-jre

ADD target/voicemail-notifier-*-standalone.jar /voicemail-notifier-standalone.jar

CMD ["java", "-Dfile.encoding=UTF-8", "-jar", "/voicemail-notifier-standalone.jar"]
