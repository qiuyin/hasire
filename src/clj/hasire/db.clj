(ns hasire.db
  (:require [toucan.db :as db]))

(db/set-default-db-connection!
             {:classname   "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname "//localhost/hasire"
              :user "hasire"
              :password "198211"})
