logLevel := Level.Info

resolvers ++= Seq("Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/",
                  "Flyway" at "https://davidmweber.github.io/flyway-sbt.repo",
				  Classpaths.sbtPluginReleases)
resolvers += "Flyway" at "https://flywaydb.org/repo"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.8")
addSbtPlugin("org.flywaydb" % "flyway-sbt" % "4.2.0")
