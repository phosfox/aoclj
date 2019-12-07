(ns days.day6
  (:require [clojure.string :as str]))

(def raw-input (slurp "./src/days/day6.txt"))

(def test-input "COM)B\nB)C\nC)D\nD)E\nE)F\nB)G\nG)H\nD)I\nE)J\nJ)K\nK)L")

(def test-input2 "COM)B
B)C
C)D
D)E
E)F
B)G
G)H
D)I
E)J
J)K
K)L
K)YOU
I)SAN")

(def parsed-input
  (as-> raw-input inp
    (str/split inp #"\n")
    (map #(str/split % #"\)") inp)))


(defn orbits [input]
  (let [key-map (reduce #(assoc %1 (first %2) (conj #{} (second %2))) {} input)
        fin-map (reduce (fn [m i] (update m (first i) #(conj % (second i)))) key-map parsed-input)]
    fin-map))


(defn orbits-rec [input]
  (loop [lines (str/split input #"\n")
         orbs {}]
    (if lines
      (let [[lhs rhs] (str/split (first lines) #"\)")]
        (recur (next lines)
               (update orbs lhs (fnil conj #{}) rhs)))
      orbs)))

(defn count-all-edges [edges root depth]
  (+ depth (reduce (fn [c node]
                     (+ c (count-all-edges edges node (inc depth))))
                   0
                   (edges root))))

(defn solve []
  (count-all-edges (orbits parsed-input) "COM" 0))

(defn invert-orbits [input]
  (loop [lines (str/split input #"\n")
         orbs {}]
    (if lines
      (let [[lhs rhs] (str/split (first lines) #"\)")]
        (recur (next lines)
               (update orbs rhs (fnil conj #{}) lhs)))
      orbs)))

(defn path [edges root]
  (let [node (first (edges root))]
    (cons root
          (if (nil? node)
            nil
            (path edges node)))))

(defn first-common-node [path1 path2]
  (first (for [p1 path1
               p2 path2
               :when (= p1 p2)]
           [p1 (- (.indexOf path1 p1) 1)])))

(def you-path (-> raw-input
                  (invert-orbits)
                  (path , "YOU")))

(def san-path (-> raw-input
                  (invert-orbits)
                  (path , "SAN")))

(defn solve2 []
  (+ (second (first-common-node you-path san-path)) (second (first-common-node san-path you-path))))
