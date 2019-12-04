(ns aoclj.core
  (:require [days.day4b :as day4])
  (:gen-class))

(defn -main "I don't do a whole lot ... yet."
  [& args]
  (println (time (day4/solve)))
  (println (time (day4/solve2))))
