(defproject voicemail-notifier "0.1.0-SNAPSHOT"
  :description "A simple service to notify me when Twilio gets a voicemail for me"
  :url "https://github.com/farmdawgnation/voicemail-notifier"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.4.0"]
                 [com.twilio.sdk/twilio "7.37.3"]
                 [com.typesafe/config "1.3.4"]
                 [ch.qos.logback/logback-classic "1.2.3"]
                 [org.clojure/tools.logging "0.4.0"]]
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler voicemail-notifier.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
