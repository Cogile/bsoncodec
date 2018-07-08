scalaVersion := "2.12.3"

name := "bsoncodec"
organization := "io.cogile.mongo"
version := "1.0"

homepage := Some(url("https://github.com/Cogile/bsoncodec"))
scmInfo := Some(ScmInfo(url("https://github.com/Cogile/bsoncodec"),
                            "git@github.com:Cogile/bsoncodec.git"))
developers := List(Developer("khamutov",
                             "Aleksandr Khamutov",
                             "me@khamutov.ru",
                             url("https://github.com/khamutov")))
licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
publishMavenStyle := true

// Add sonatype repository settings
publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.1.0"