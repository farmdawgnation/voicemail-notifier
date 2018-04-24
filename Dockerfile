FROM openjdk:8u162-jre

ADD target/voicemail-notifier-*-standalone.jar /voicemail-notifier-standalone.jar

CMD ["java", "-jar", "/voicemail-notifier-standalone.jar"]
