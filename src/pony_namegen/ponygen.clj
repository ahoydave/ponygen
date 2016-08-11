(ns pony-namegen.ponygen
  (:require [clojure.string :as str]))

(def name-list (str/split (slurp "pony_names.txt") #"\n"))

(defn to-pony [s]
  (nth name-list
       (mod (hash s)
            (count name-list))))