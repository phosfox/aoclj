(ns days.day3
  (:require [clojure.string :as str]))

(def raw-input (slurp "./src/days/day3.txt"))


(defn path-to-map [str]
  (let [matches (re-matches #"([A-Z])(\d+)" str)
        direction (get matches 1)
        steps (get matches 2)]
    {:direction direction :steps (Integer/parseInt steps)}))

(defn path-to-coords [path prev-coords]
  (let [m (path-to-map path)
        dir (m :direction)
        steps (m :steps)
        prev-x (prev-coords :x)
        prev-y (prev-coords :y)]
    (case dir
      "U" (for [y (range (inc prev-y) (+ prev-y steps 1))]
            {:x prev-x :y y})
      "D" (for [y (range (dec prev-y) (- prev-y steps 1) -1)]
            {:x prev-x :y y})
      "L" (for [x (range (dec prev-x) (- prev-x steps 1) -1)]
            {:x x :y prev-y})
      "R" (for [x (range (inc prev-x) (+ prev-x steps 1))]
            {:x x :y prev-y})
      )))

(def wires
  (as-> raw-input inp
    (str/trim inp)
    (str/split inp #"\n")
    (map #(str/split % #","))
    ()))
