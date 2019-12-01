(ns days.day1
  (:require [clojure.string :as str]
            [clojure.core :as core]))

(def raw-input (slurp "./src/days/day1.txt"))

(def parsed-input 
  (as-> raw-input inp
    (str/split inp #"\n")
    (map #(Integer/parseInt %) inp)))

(defn calc-mass [n]
  (- (int (Math/floor  (/ n 3))) 2))

(defn solve []
  (->> parsed-input
    (map calc-mass)
    (reduce +)))
