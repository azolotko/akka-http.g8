name := "$name$"

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.8"

scalacOptions ++= Seq("-feature",
                      "-unchecked",
                      "-deprecation",
                      "-Ywarn-value-discard",
                      "-Ywarn-unused-import")

val akkaVersion = "2.5.21"
val akkaHttpVersion = "10.1.8"
val circeVersion = "0.11.1"
val kantanVersion = "0.5.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,
  "com.nrinaudo" %% "kantan.csv" % kantanVersion,
  "com.nrinaudo" %% "kantan.csv-generic" % kantanVersion,
  "com.nrinaudo" %% "kantan.csv-java8" % kantanVersion,
  "de.heikoseeberger" %% "akka-http-circe" % "1.25.2",
  "org.typelevel" %% "cats-core" % "1.6.0",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.scalatest" %% "scalatest" % "3.0.7" % "test",
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % "test"
)

mainClass in Compile := Some("$package$.App")
