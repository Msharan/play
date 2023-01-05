import java.util.TimeZone

name := "auth"

version := "0.0.1"

organization := "com.brambles"

scalaVersion := "2.13.3"

libraryDependencies ++= Seq(ws,
  filters,
  guice,
  "org.json4s" %% "json4s-jackson" % "3.6.9",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.12.5",
  "mysql" % "mysql-connector-java" % "5.1.48",
  "net.debasishg" %% "redisclient" % "3.30",
  "ch.qos.logback" % "logback-classic" % "1.2.10",
  "ch.qos.logback" % "logback-core" % "1.2.10",
  "org.mindrot" % "jbcrypt" % "0.4",
  "com.zaxxer" % "HikariCP" % "4.0.3" excludeAll( ExclusionRule(organization = "org.slf4j"))
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)

flywayLocations += "filesystem:conf/db/migration"

flywayUrl := "jdbc:mysql://localhost:3306/auth"

flywayUser := "root"

flywayPassword := ""

sources in(Compile, doc) := Seq.empty

publishArtifact in(Compile, packageDoc) := false

routesGenerator := InjectedRoutesGenerator
