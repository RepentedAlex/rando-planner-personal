^{:nextjournal.clerk/visibility {:code :hide}}
(ns notebooks.paris-lehavre
  (:require [rando-planner.diagram :as diagram]
            [rando-planner.gpx :as gpx]
            [rando-planner.plan :as plan]
            [rando-planner.leaflet :as leaflet]
            [nextjournal.clerk :as clerk]))

;; # Bikepacking Journey: Paris to Le Havre
;; 
;; Embark on an exciting bikepacking trip from the bustling streets of Paris to the serene coast of Le Havre.
;; 
;; - **Total Distance**: 🏁 Estimations available
;; - **Expected Average Speed**: 🚴 15 km/h
;; - **Estimated Total Time**: 🕒 Adjusts based on speed
;; 
;; Let's dive into the details!

;; ## Route Preview
;; Here is the full route from Paris to Le Havre. Zoom in and out to explore various segments of the journey.
^{:nextjournal.clerk/visibility {:code :hide}}
(clerk/with-viewer leaflet/leaflet-gpx-viewer
  {:gpx "gpx/paris-lehavre.gpx"})

;; ## Total Distance
;; The total distance for the trip is calculated from the GPX file.
^{:nextjournal.clerk/visibility {:code :hide}}
(def total-distance (gpx/total-distance "gpx/paris-lehavre.gpx"))
[:div "Total Distance: " (str total-distance " km")]

;; ## Elevation Profile
;; Get a sneak peek into the elevation changes you will encounter on your trip.
^{:nextjournal.clerk/visibility {:code :hide}}
(clerk/with-viewer diagram/elevation-viewer {:gpx "gpx/paris-lehavre.gpx"})

;; ## Anticipated Average Speed
;; Estimate your journey time based on a planned average speed. Adjust this value for different pace scenarios.
^{:nextjournal.clerk/visibility {:code :show}}
(def average-speed 15)
[:div "Anticipated Average Speed: " (str average-speed " km/h")]

;; ## Estimated Travel Time
;; Calculate your estimated time to complete the trip based on the total distance and planned average speed.
^{:nextjournal.clerk/visibility {:code :show}}
(def estimated-time (/ total-distance average-speed))
[:div "Estimated Total Travel Time: " (str estimated-time " hours")]

;; ## Points of Interest
;; Key highlights and stops along the way to make your journey memorable.
[:ul
 [:li "Paris: Start at the heart of France"]
 [:li "Rouen: A scenic midpoint for rest and refuel"]
 [:li "Le Havre: Destination by the sea"]]






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
