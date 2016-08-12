(ns pony-namegen.ponygen
  (:require [clojure.string :as str]))

(def name-list (str/split (slurp "pony_names.txt") #"[ \n]"))

(defn single-pony [s]
  (nth name-list
       (mod (hash s)
            (count name-list))))

(defn double-pony [s]
  (str (single-pony s) " " (single-pony (str s "I love my little pony"))))