(ns voicemail-notifier.core
  (:require [voicemail-notifier.config :as config])
  (:import [com.twilio Twilio]
           [com.twilio.rest.api.v2010.account Message]))

(defn ping
  "Play ping pong"
  []
  {:body {:result "pong"}})

(defn notify
  "Notify that a voicemail has been received"
  [recording-url]
  (let [_ (. Twilio init config/account-sid config/auth-token)
        message-text (str "Twilio has received a new voicemail: "
                          recording-url)
        message-creator (. Message creator config/target-phone-number
                                           config/source-phone-number
                                           message-text)
        resulting-message (.create message-creator)]
    {:body {:result "ok"}}))
