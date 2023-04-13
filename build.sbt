ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "Calculator",
    idePackagePrefix := Some("com.knoldus")
  )
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.32"

