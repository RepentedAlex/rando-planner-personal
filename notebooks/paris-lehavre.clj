^{:nextjournal.clerk/visibility {:code :hide}}
(ns notebooks.paris-lehavre
  (:require [rando-planner.diagram :as diagram]
            [rando-planner.gpx :as gpx]
            [rando-planner.plan :as plan]
            [rando-planner.leaflet :as leaflet]
            [nextjournal.clerk :as clerk]))

;; # Paris - Le Havre

;; Prévisualisation du trajet

^{:nextjournal.clerk/visibility {:code :hide}}
(clerk/with-viewer leaflet/leaflet-gpx-viewer
  {:gpx "gpx/paris-lehavre.gpx"})

;; ## Distance totale

^{:nextjournal.clerk/visibility {:code :hide}}
(gpx/total-distance "gpx/paris-lehavre.gpx")

;; ## A little peek at the elevation

^{:nextjournal.clerk/visibility {:code :hide}}
(clerk/with-viewer diagram/elevation-viewer {:gpx "gpx/paris-lehavre.gpx"})

;; ## Vitesse moyenne anticipée

^{:nextjournal.clerk/visibility {:code :hide}}
(def average-speed 15)

;; ## Nombre d'heures anticipées (selon vitesse anticipée)

^{:nextjournal.clerk/visibility {:code :hide}}
(/ (gpx/total-distance "gpx/paris-lehavre.gpx")
   average-speed)

;; ## So if we ride for four days, how will it go ?

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

(clerk/with-viewer diagram/plan-viewer equally-split-plan)

;; Each square in the diagram corresponds to one hour of ride.  The
;; color of the square displays the light condition at that time.
;; This is why it is important to provide a `:date` in the plan:
;; rando-planner uses that, along with the GPX route, to calculate
;; when the Sun is setting in a particular place and time.


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

(clerk/with-viewer diagram/plan-viewer equally-split-plan-with-pauses)

^{:nextjournal.clerk/visibility {:code :hide}}
(clerk/with-viewer diagram/elevation-viewer equally-split-plan-with-pauses)

(merge
 {:nextjournal/width :full}
 (clerk/row
  (clerk/col
   (clerk/html [:h3 "Plan with no lunch!"])
   (clerk/with-viewer diagram/elevation-viewer equally-split-plan)
   (clerk/with-viewer diagram/plan-viewer equally-split-plan))
  (clerk/col
   (clerk/html [:h3 "Plan with a pause"])
   (clerk/with-viewer diagram/elevation-viewer equally-split-plan-with-pauses)
   (clerk/with-viewer diagram/plan-viewer equally-split-plan-with-pauses))))

^{:nextjournal.clerk/visibility {:code :hide}}
(merge
 {:nextjournal/width :full}
 (clerk/with-viewer leaflet/leaflet-gpx-viewer equally-split-plan-with-pauses))
