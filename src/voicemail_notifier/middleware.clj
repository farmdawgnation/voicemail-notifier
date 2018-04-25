(ns voicemail-notifier.middleware
  (:require [voicemail-notifier.config :as config]
            [clojure.tools.logging :as log]
            [clojure.walk :as walk])
  (:import [com.twilio.security RequestValidator]
           [java.util HashMap]))

(defn wrap-request-validation
  "Middleware that will validate a request came from Twilio"
  [handler]
  (if config/validation-enabled?
    (fn [request]
      (let [validator (new RequestValidator config/auth-token)
            url (str config/base-url (:uri request))
            params (walk/stringify-keys (:params request))
            twilio-sig (get (:headers request) "x-twilio-signature")]
        (log/info "Verifying signature on request")
        (log/info "URL: " url)
        (log/info "Params: " params)
        (log/info "Key set: " (.keySet params))
        (log/info "Twilio's signature: " twilio-sig)
        (if (.validate validator url params twilio-sig)
          (do (log/info "Request was valid")
              (handler request))
          (do (log/info "Request was not valid")
              {:status 401 :body {:error "Invalid signature."}}))))
    handler))
