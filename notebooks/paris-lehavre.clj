^{:nextjournal.clerk/visibility {:code :hide}}
(ns notebooks.paris-lehavre
  (:require [rando-planner.diagram :as diagram]
            [rando-planner.gpx :as gpx]
            [rando-planner.plan :as plan]
            [rando-planner.leaflet :as leaflet]
            [nextjournal.clerk :as clerk]))

;; # 000 - Paris / Le Havre
;; 
;; Une virée bikepacking des rues animées parisiennes aux côtes havraises. 
;; 
;; - **Distance Totale**: 🏁 Estimations available
;; - **Vitesse Moyenne Anticipée**: 🚴 15 km/h
;; - **Temps Total Estimé**: 🕒 Adjusts based on speed
;; 

;; ## Aperçu de la route
;; Voici la trace de Paris au Havre.
^{:nextjournal.clerk/visibility {:code :hide}}
(clerk/with-viewer leaflet/leaflet-gpx-viewer
  {:gpx "gpx/paris-lehavre.gpx"})

;; ## Distance Totale
^{:nextjournal.clerk/visibility {:code :hide}}
(def total-distance (gpx/total-distance "gpx/paris-lehavre.gpx"))

;; ## Profil d'Élévation
^{:nextjournal.clerk/visibility {:code :hide}}
(clerk/with-viewer diagram/elevation-viewer {:gpx "gpx/paris-lehavre.gpx"})

;; ## Vitesse Moyenne Anticipée
^{:nextjournal.clerk/visibility {:code :hide}}
(def average-speed 15)

;; ## Temps Estimé de Roulage
^{:nextjournal.clerk/visibility {:code :hide}}
(def estimated-time (/ total-distance average-speed))

;; ## Lieux d'intérêt
[:ul
 [:li "Paris: Point Zéro"]
 [:li "Rouen: Escale Historique"]
 [:li "Le Havre: Porte sur l'Atlantique"]]






^{:nextjournal.clerk/visibility {:code :hide}}
(def equally-split-plan
  {:gpx "gpx/paris-lehavre.gpx"
   :average-speed average-speed
   :daily-plans [{:date "2025-01-01"
                  :label "First day"
                  :activities [{:start "10:00" :type :ride :length 6}]}
                 {:date "2025-01-02"
                  :label "Second day"
                  :activities [{:start "10:00" :type :ride :length 6}]}
                 {:date "2025-01-03"
                  :label "Third day"
                  :activities [{:start "10:00" :type :ride :length 6}]}
                 {:date "2025-01-04"
                  :label "Fourth day"
                  :activities [{:start "10:00" :type :ride :length 9.2}]}]})

^{:nextjournal.clerk/visibility {:code :hide}}
(def equally-split-plan-with-pauses
  {:gpx "gpx/paris-lehavre.gpx"
   :average-speed average-speed
   :daily-plans [{:date "2025-01-01"
                  :label "First day"
                  :activities [{:start "08:00" :type :ride :length 3}
                               {:start "13:00" :type :ride :length 3}]}
                 {:date "2025-01-02"
                  :label "Second day"
                  :activities [{:start "08:00" :type :ride :length 3}
                               {:start "13:00" :type :ride :length 3}]}
                 {:date "2025-01-03"
                  :label "Third day"
                  :activities [{:start "08:00" :type :ride :length 3}
                               {:start "13:00" :type :ride :length 3}]}
                 {:date "2025-01-04"
                  :label "Fourth day"
                  :activities [{:start "08:00" :type :ride :length 3}
                               {:start "12:00" :type :ride :length 6.2}]}]})

^{:nextjournal.clerk/visibility {:code :hide}}
(merge
 {:nextjournal/width :full}
 (clerk/row
  (clerk/col
   (clerk/html [:h3 "Plan sans pauses!"])
   (clerk/with-viewer diagram/elevation-viewer equally-split-plan)
   (clerk/with-viewer diagram/plan-viewer equally-split-plan))
  (clerk/col
   (clerk/html [:h3 "Plan avec pauses!"])
   (clerk/with-viewer diagram/elevation-viewer equally-split-plan-with-pauses)
   (clerk/with-viewer diagram/plan-viewer equally-split-plan-with-pauses))))

^{:nextjournal.clerk/visibility {:code :hide}}
(merge
 {:nextjournal/width :full}
 (clerk/with-viewer leaflet/leaflet-gpx-viewer equally-split-plan-with-pauses))
