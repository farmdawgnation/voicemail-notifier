(ns voicemail-notifier.handler
  (:require [voicemail-notifier.core :as vncore]
            [voicemail-notifier.middleware :refer [wrap-request-validation]]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(defroutes app-routes
  (ANY "/notify" [RecordingUrl] (vncore/notify RecordingUrl))
  (ANY "/ping" [] (vncore/ping))
  (route/not-found {:body {:error "Not found"}}))

(def app
  (-> app-routes wrap-request-validation
                 wrap-json-response
                 (wrap-defaults api-defaults)))
