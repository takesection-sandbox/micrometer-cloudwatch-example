import Dependencies._

lazy val root = (project in file("."))
  .settings(
    organization := "com.pigumer",
    name := "micrometer-example",
    scalaVersion := "2.12.6",
    javacOptions ++= Seq("-source", "8", "-target", "8"),
    mainClass := Some("com.pigumer.example.Main"),
    libraryDependencies ++= Seq(
      micrometer
    )
  )