import sbt._
import Keys._

ThisBuild / organization := "com.example"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.1"

lazy val commonSettings = Seq(
  scalacOptions ++= Seq(
    "-Ymacro-annotations",
    "-feature"
  ),
  libraryDependencies += "io.estatico" %% "newtype" % "0.4.3",
  libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0",
  libraryDependencies += "eu.timepit" %% "refined" % "0.9.12",
  addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0" cross CrossVersion.full)
)


//libraryDependencies += "io.estatico" %% "newtype" % "0.4.3"
val circeVersion = "0.12.3"

lazy val domain = (project in file("domain"))
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core" % "0.12.3",
      "io.circe" %% "circe-generic" % "0.12.3",
      "io.circe" %% "circe-parser" % "0.12.3",
      "io.circe" %% "circe-refined" % "0.12.3",
      "io.circe" %% "circe-generic-extras" % "0.12.2"
    )
  )

lazy val root = (project in file("."))
  .dependsOn(domain)
  .settings(commonSettings)


