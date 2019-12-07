(ns days.day5
  (:require [clojure.string :as str]))

(def raw-input (slurp "./src/days/day5.txt"))

(def parsed-input
  (as-> raw-input inp
    (str/trim inp)
    (str/split inp #",")
    (mapv #(Integer/parseInt %) inp)))

(defn parse-instruction [n]
  (let [rev-s (reverse (str/split (str n) #""))
        opcode (Integer/parseInt (apply str (concat (second rev-s) (first rev-s))))
        para1 (Integer/parseInt (get rev-s 3))]
    {:opcode opcode}))
