scalaVersion := "2.12.3"

name := "bsoncodec"
organization := "io.cogile.mongo"
version := "1.0"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.1.0"