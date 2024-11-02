(ns user)

(require '[nextjournal.clerk :as clerk])

(clerk/serve! {:watch-paths ["notebooks" "src"]})

(defn start-clerk []
  (clerk/serve! {:watch-paths ["notebooks" "src"]}))
