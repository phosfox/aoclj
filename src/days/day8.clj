(ns days.day8
  (:require [clojure.string :as str]))

(def raw-test-input "123456789012")

(def raw-input (slurp "./src/days/day8.txt"))

(defn get-layer [s width height]
  (->> s
       (partition width)
       (partition height)))

(defn least-zeroes-layer [layer]
  (as-> layer l
    (map #(frequencies (flatten %)) l)
    (map-indexed (fn [idx itm]
                   {:idx idx :zeroes (itm \0)}) l)
    (sort-by :zeroes l)
    (first l)
    (nth layer (l :idx))))

(defn count-digits [layer]
  (let [freqs (->> layer
                   (flatten)
                   (frequencies))
        ones (freqs \1)
        twos (freqs \2)]
    (* ones twos)))

(defn solve []
  (as-> raw-input inp
    (get-layer inp 25 6)
    (least-zeroes-layer inp)
    (count-digits inp)))
