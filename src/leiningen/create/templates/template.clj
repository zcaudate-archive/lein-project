(ns leiningen.create.templates.template
  (:use [leiningen.create.utils :only [sanitize year]]))

(defn template
  "A meta-template for 'lein new' templates."
  [name]
  {:template true
   :data 
     {:name name
      :sanitized (sanitize name)
      :placeholder "{{sanitized}}"
      :year (year)}
   :directives
     {:render-dirs [["" :except ["src"]]
                    ["src" "src/leiningen/new"]]}})
