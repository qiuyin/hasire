(ns hasire.handler
  (:require [hasire.db]
            [toucan.db :as db]
            [hasire.models.user :refer [User]]
            [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [resources]]
            [cheshire.core :refer :all]
            [compojure.handler :as handler]
            [ring.util.response :refer [resource-response response]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.json :as middleware]))

(defroutes routes
  (GET "/" [] (resource-response "index.html" {:root "public"}))
  (GET "/users.json" [] (response (db/select [User :name :email])))
  (resources "/"))

(def app
  (-> (handler/site routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)))

(def dev-handler (-> app wrap-reload))
