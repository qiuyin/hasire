(ns hasire.events
  (:require [re-frame.core :as re-frame]
            [ajax.core :refer [GET]]
            [hasire.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(re-frame/reg-event-db        ;; <-- register an event handler
  :display-user-list        ;; <-- the event id
  (fn                ;; <-- the handler function
    [db _]

    ;; kick off the GET, making sure to supply a callback for success and failure
    (GET
      "http://127.0.0.1:3000/users.json"
      {:handler       #(re-frame/dispatch [:process-response %1])   ;; <2> further dispatch !!
       :error-handler #(re-frame/dispatch [:bad-response %1])})    ;; <2> further dispatch !!

     ;; update a flag in `app-db` ... presumably to cause a "Loading..." UI
     (assoc db :loading? true)))    ;; <3> return an updated db

(re-frame/reg-event-db
  :process-response
  (fn
    [db [_ response]]
    (-> db
        (assoc :loading? false) ;; take away that "Loading ..." UI
        (assoc :users (js->clj response)))))
      ;; fairly lame processing

(re-frame/reg-event-db
  :bad-response
  (fn
    [db [_ response]]           ;; destructure the response from the event vector
    (-> db
        (assoc :loading? false))))  ;; fairly lame processing
