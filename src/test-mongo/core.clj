(ns test-mongo.core
  (:gen-class)
  (:require [monger.core :as mg]
            [monger.credentials :as mcr]
            [monger.collection :as mc]
            [monger.operators :as mop])
  (:import org.bson.types.ObjectId))

(def creds (mcr/create "mongoadmin" "admin" (.toCharArray "my-secret")))

(def conn (mg/connect-with-credentials "127.0.0.1" 27018 creds))

(def db (mg/get-db conn "local"))


(def collection-name "test-collection")

(def my-document-id   (ObjectId.))

(defn -main
  [& _]
  (println "Start")
  (loop [i 0]
    (when (zero? (mod i  1000))
      (printf "Inserted: %s items%n " i))
    (mc/update-by-id db collection-name my-document-id
                     {mop/$push {:events {:type :new-event
                                          :id (str (java.util.UUID/randomUUID))}}})
    (recur (inc i))))