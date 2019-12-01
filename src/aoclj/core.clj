(ns aoclj.core
  (:require [days.day1 :as day1])
  (:gen-class))

(defn -main "I don't do a whole lot ... yet."
  [& args]
  (println (day1/solve))
  (println (day1/solve2)))
