import Dependencies._

lazy val root = (project in file("."))
  .settings(
    organization := "com.pigumer",
    name := "micrometer-example",
    scalaVersion := "2.12.6",
    mainClass := Some("com.pigumer.example.Main"),
    libraryDependencies ++= Seq(
      micrometer
    )
  )