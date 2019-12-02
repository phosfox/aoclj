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

(defn verb-noun-comb [ls]
  (for [verb (range 1 100)
        noun (range 1 100)]
    (assoc (assoc ls 1 noun) 2 verb)))

(defn find-verb-noun [ls]
  (loop [pos 0
         ls ls]
    (let [opcode (get ls pos)
          inp1 (get ls (get ls (+ pos 1)))
          inp2 (get ls (get ls (+ pos 2)))
          out (get ls (+ pos 3))]
      (if (>= opcode 99)
        {"output" (get ls 0) "noun" (get ls 1) "verb" (get ls 2)}
        (recur (+ 4 pos) (assoc ls out ((operation opcode) inp1 inp2)))))))

(defn solve2 []
  (as-> parsed-input inp
    (verb-noun-comb inp)
    (mapv find-verb-noun inp)
    (filter #(= (% "output") 19690720) inp)
    (first inp)
    (+ (* 100 (inp "noun")) (inp "verb"))))
