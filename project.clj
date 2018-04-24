(defproject voicemail-notifier "0.1.0-SNAPSHOT"
  :description "A simple service to notify me when Twilio gets a voicemail for me"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-json "0.4.0"]
                 [com.twilio.sdk/twilio "7.17.0"]
                 [com.typesafe/config "1.3.2"]]
  :plugins [[lein-ring "0.12.4"]]
  :ring {:handler voicemail-notifier.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
