(ns days.day2
  (:require [clojure.string :as str]))

(def raw-input (slurp "./src/days/day2.txt"))

(def parsed-input
  (as-> raw-input inp
    (str/trim inp)
    (str/split inp #",")
    (mapv #(Integer/parseInt %) inp)))

(def operation {1 + 2 *})

(defn intcode [ls]
  (loop [pos 0
         ls ls]
    (let [opcode (get ls pos)
          inp1 (get ls (get ls (+ pos 1)))
          inp2 (get ls (get ls (+ pos 2)))
          out (get ls (+ pos 3))]
      (if (>= opcode 99)
        (get ls 0)
        (recur (+ 4 pos) (assoc ls out ((operation opcode) inp1 inp2)))))))

(defn solve []
  (intcode parsed-input))
