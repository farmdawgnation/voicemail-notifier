(ns voicemail-notifier.middleware
  (:require [voicemail-notifier.config :as config])
  (:import [com.twilio.security RequestValidator]
           [java.util HashMap]))

(defn- add-param-to-map [hash-map param]
  (let [key (-> param first (.toString))
        value (second param)]
    (.put hash-map param)
    hash-map))

(defn- to-params-map [params]
  (reduce add-param-to-map (new HashMap) params))

(defn wrap-request-validation
  "Middleware that will validate a request came from Twilio"
  [handler]
  (if config/validation-enabled?
    (fn [request]
      (let [validator (new RequestValidator config/auth-token)
            _ (println request)
            url (:uri request)
            verb (:request-method request)
            params (:params request)
            twilio-sig (get request "x-twilio-signature")]
        (handler request)))
    handler))
