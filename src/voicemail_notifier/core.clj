(ns voicemail-notifier.core
  (:import [com.twilio Twilio]
           [com.twilio.rest.api.v2010.account Message]
           [com.twilio.type PhoneNumber]
           [com.typesafe.config ConfigFactory]))

(def config (. ConfigFactory load))

(def account-sid (.getString config "account.sid"))

(def auth-token (.getString config "auth.token"))

(def source-phone-number (new PhoneNumber (.getString config "source.number")))

(def target-phone-number (new PhoneNumber (.getString config "target.number")))

(def callback-secret (.getString config "callback.secret"))

(defn notify
  "Notify that a voicemail has been received"
  [provided-secret recording-url]
  (if (.equals provided-secret callback-secret)
    (let [_ (. Twilio init account-sid auth-token)
          message-text (str "Twilio has received a new voicemail: "
                            recording-url)
          message-creator (. Message creator target-phone-number
                                             source-phone-number
                                             message-text)
          resulting-message (.create message-creator)]
      {:body {:result "ok"}})

    {:status 401 :body {:result "access denied"}}))
