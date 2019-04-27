FROM gcr.io/distroless/java:11

ADD target/voicemail-notifier-*-standalone.jar /voicemail-notifier-standalone.jar

CMD ["/voicemail-notifier-standalone.jar"]
