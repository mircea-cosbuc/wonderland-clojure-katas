(ns tiny-maze.solver)

(defn solve-maze [maze]

  )
(defn find-start [maze] [0 0])

(def rotations
  {:N :W
   :W :S
   :S :E
   :E :N})

(def steps
  {:N [-1 0]
   :W [0 -1]
   :E [0 1]
   :S [1 0]})

(defn left [direction]
  (direction rotations))

(defn left-pos [position direction]
  (let [[x y] position
        [a b] (direction steps)]
    [(+ x a) (+ y b)]
    ))

(defn valid? [maze position direction walls]
  (let [[x y] position
        value (get-in maze [x y])
        wall (left-pos position direction)]
    (and (not= value 1)
         (not (contains? walls wall)))
    ))

(defn find-next-position
  [maze position direction walls]
  (let [next-position (left-pos position direction)]
    (if (valid? maze next-position direction walls)
      (let [new-maze (assoc-in maze next-position :x)
            new-walls (conj walls (left-pos next-position direction))]
        [new-maze next-position direction new-walls])
      [maze position (direction rotations) walls]
      ))
  )
