(ns voicemail-notifier.handler
  (:require [voicemail-notifier.core :as vncore]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(defroutes app-routes
  (ANY "/notify/:secret" [secret RecordingUrl] (vncore/notify secret RecordingUrl))
  (route/not-found "Not Found"))

(def app
  (-> app-routes wrap-json-response
                 (wrap-defaults api-defaults)))
