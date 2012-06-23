(ns leiningen.new.default
  "Generate a library project."
  (:use [leiningen.new.templates :only [renderer year project-name
                                        ->files sanitize-ns name-to-path]]))

(defn default
  "A general project template for libraries.

Accepts a group id in the project name: `lein new foo.bar/baz`"
  [name]
  (let [render (renderer "default")
        data {:raw-name name
              :name (project-name name)
              :namespace (sanitize-ns name)
              :nested-dirs (name-to-path name)
              :year (year)}]
    (println "Generating a project called" name "based on the 'default' template.")
    (println "To see other templates (app, lein plugin, etc), try `lein help new`.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             [".gitignore" (render "gitignore" data)]
             ["src/{{nested-dirs}}/core.clj" (render "core.clj" data)]
             ["test/{{nested-dirs}}/core_test.clj" (render "test.clj" data)])))
