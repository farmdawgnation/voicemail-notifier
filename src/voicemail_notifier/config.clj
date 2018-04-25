(ns voicemail-notifier.config
  (:import [com.typesafe.config ConfigFactory]
           [com.twilio.type PhoneNumber]))

(def config (. ConfigFactory load))

(def account-sid (.getString config "account.sid"))

(def auth-token
  (let [env-token (if (.hasPath config "auth.token")
                    (.getString config "auth.token")
                    nil)
        file-token-path (if (.hasPath config "file.auth.token")
                          (.getString config "file.auth.token")
                          nil)]
    (or env-token
        (slurp file-token-path))))

(def source-phone-number (new PhoneNumber (.getString config "source.number")))

(def target-phone-number (new PhoneNumber (.getString config "target.number")))

(def validation-enabled? (.getBoolean config "validation.enabled"))
