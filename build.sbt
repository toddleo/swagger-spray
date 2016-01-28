import sbtrelease.ReleasePlugin.ReleaseKeys._

organization := "com.github.swagger-spray"

name := "swagger-spray"

scalaVersion := "2.11.7"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

resolvers += "Maven" at "https://repo1.maven.org/maven2/"

resolvers += "spray repo" at "http://repo.spray.io"

resolvers += Resolver.mavenLocal

checksums in update := Nil

libraryDependencies ++= Seq(
  "io.spray" %% "spray-routing" % "1.3.3",
  "io.spray" %% "spray-testkit" % "1.3.3" % "test",
  "io.spray" %% "spray-json" % "1.3.2",
  "io.swagger" %% "swagger-scala-module" % "1.0.1",
  "io.swagger" % "swagger-core" % "1.5.6",
  "io.swagger" % "swagger-annotations" % "1.5.6",
  "io.swagger" % "swagger-models" % "1.5.6",
  "io.swagger" % "swagger-jaxrs" % "1.5.6",
  "org.scalatest" %% "scalatest" % "2.2.5" % "test" ,
  "com.typesafe.akka" %% "akka-actor" % "2.3.11",
  "org.json4s" %% "json4s-jackson" % "3.2.11" % "test",
  "org.json4s" %% "json4s-native" % "3.2.11" % "test",
  "joda-time" % "joda-time" % "2.8" % "test",
  "org.joda" % "joda-convert" % "1.7" % "test",
  "javax.ws.rs" % "jsr311-api" % "1.1.1"
)


releaseSettings

testOptions in Test += Tests.Argument("-oD")

parallelExecution in Test := false

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

parallelExecution in Test := false

homepage := Some(url("https://github.com/swagger-spray/swagger-spray"))

licenses := Seq("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

publishArtifactsAction := PgpKeys.publishSigned.value

pomExtra := (
  <scm>
    <url>git@github.com:swagger-spray/swagger-spray.git</url>
    <connection>scm:git:git@github.com:swagger-spray/swagger-spray.git</connection>
  </scm>
  <developers>
    <developer>
      <id>mhamrah</id>
      <name>Michael Hamrah</name>
      <url>http://michaelhamrah.com</url>
    </developer>
    <developer>
      <id>efuquen</id>
      <name>Edwin Fuquen</name>
      <url>http://parascal.com</url>
    </developer>
    <developer>
      <id>rliebman</id>
      <name>Roberto Liebman</name>
      <url>https://github.com/rleibman</url>
    </developer>
    <developer>
      <id>pjfanning</id>
      <name>PJ Fanning</name>
      <url>https://github.com/pjfanning</url>
    </developer>
  </developers>)
