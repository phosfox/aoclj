(ns days.day3
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(def raw-input (slurp "./src/days/day3.txt"))
;; => #'aoclj.core/raw-input


(def test-input (slurp "./src/days/test-input3.txt"))

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
            {:x x :y prev-y}))))

(def start {:x 0 :y 0})

(defn wire-coords [wire]
  (reduce #(flatten (conj %1 (path-to-coords %2 (last %1)))) [start] wire))

(defn manhattan-dist [coords1 coords2]
  (let [x1 (coords1 :x)
        y1 (coords1 :y)
        x2 (coords2 :x)
        y2 (coords2 :y)]
    (+ (Math/abs (- x1 x2)) (Math/abs (- y1 y2)))))

(def wires
  (as-> raw-input inp
    (str/split inp #"\n")
    (map #(str/split % #",") inp)
    (map wire-coords inp)
    (map set inp)
    (println inp)
    (apply set/intersection inp)
    (println inp)
    (map #(manhattan-dist start %) inp)))

(defn solve []
  wires)
