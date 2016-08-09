(ns pony-namegen.web
  [:require [compojure.core :refer [defroutes GET POST]]
            [ring.adapter.jetty :as ring]
            [ring.middleware.params :refer [wrap-params]]
            [hiccup.page :as page]
            [hiccup.form :as f]]
  (:gen-class))

(defn index []
  (page/html5
    [:head
     [:title "Hello World"]]
    [:body
     [:div {:id "content"}
      (f/form-to [:post "/"]
                 (f/text-field :name)
                 (f/submit-button "Generate!"))
      ]]))

(defn show-text [t]
  (page/html5
    [:head
     [:title "Hello World"]]
    [:body
     [:div {:id "content"}
      "Text " t
      ]]))

(defroutes routes
           (GET "/" [] (index))
           (GET "/poop/" [] (show-text "poop"))
           (POST "/" [name]
                 (show-text name)))

(def app (wrap-params routes))

(defn -main []
  (ring/run-jetty app {:port (Integer. (or (System/getenv "PORT") "8080"))
                       :join? false}))

