(ns voicemail-notifier.config
  (:import [com.typesafe.config ConfigFactory]
           [com.twilio.type PhoneNumber]))

(def config (. ConfigFactory load))

(def account-sid (.getString config "account.sid"))

(def auth-token (.getString config "auth.token"))

(def source-phone-number (new PhoneNumber (.getString config "source.number")))

(def target-phone-number (new PhoneNumber (.getString config "target.number")))

(def callback-secret (.getString config "callback.secret"))

(def validation-enabled? (.getBoolean config "validation.enabled"))
