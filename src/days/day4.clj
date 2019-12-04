(ns days.day4)

(def start 172851)

(def end 675869)

(defn six-digits? [n]
  (if (= 6 (count (str n))) true false))

(defn two-same-adj? [n]
  (let [str-n (str n)]
    (as-> str-n s
      (map #(= %1 %2) (drop 1 s) s)
      (some true? s)
      (if s true false))))

(defn increasing? [n]
  (let [str-n (str n)]
    (as-> str-n s
      (map #(<= (Integer/parseInt (str %1)) (Integer/parseInt (str %2))) s (drop 1 s))
      (every? true? s))))


(defn only-two-same-adj? [n]
  (let [str-n (str n)]
    (as-> str-n s
      (mapcat #(when (= % %2) [%]) (rest s) s)
      (frequencies s)
      (vals s)
      (some #(= 1 %) s)
      (if s true false))))

(def fulfil-rules (every-pred six-digits? two-same-adj? increasing?))

(def fulfil-new-rules (every-pred six-digits? only-two-same-adj? increasing?))

(defn solve []
  (as-> (range start end) nums
    (filter fulfil-rules nums)
    (count nums)))

(defn solve2 []
  (as-> (range start end) nums
    (filter fulfil-new-rules nums)
    (count nums)))
