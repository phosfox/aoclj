(ns days.day4b)

(defn asc [x] (= (sort x) (seq x)))

(def freqs
  (->> (range 172851 675869)
       (map str)
       (filter asc)
       (map #(map last (frequencies %)))))

(def part1 (filter #(some (fn [x] (< 1 x)) %) freqs))

(def part2 (filter #(some (fn [x] (= 2 x)) %) part1))

(defn solve []
  (count part1))

(defn solve2 []
  (count part2))
