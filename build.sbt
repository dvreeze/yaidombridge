
// Building both for JVM and JavaScript runtimes.

// To convince SBT not to publish any root level artifacts, I had a look at how scala-java-time does it.
// See https://github.com/cquiroz/scala-java-time/blob/master/build.sbt as a "template" for this build file.


// shadow sbt-scalajs' crossProject and CrossType from Scala.js 0.6.x

import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

val scalaVer = "2.13.2"
val crossScalaVer = Seq(scalaVer)

lazy val commonSettings = Seq(
  name         := "yaidombridge",
  description  := "Conversions between yaidom and yaidom2",
  organization := "eu.cdevreeze.yaidombridge",
  version      := "0.2.0-SNAPSHOT",

  scalaVersion       := scalaVer,
  crossScalaVersions := crossScalaVer,

  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xfatal-warnings", "-Xlint"),

  Test / publishArtifact := false,
  publishMavenStyle := true,

  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value) {
      Some("snapshots" at nexus + "content/repositories/snapshots")
    } else {
      Some("releases" at nexus + "service/local/staging/deploy/maven2")
    }
  },

  pomExtra := pomData,
  pomIncludeRepository := { _ => false },

  libraryDependencies += "eu.cdevreeze.yaidom" %%% "yaidom" % "1.11.0",

  libraryDependencies += "eu.cdevreeze.yaidom2" %%% "yaidom2" % "0.11.0",

  libraryDependencies += "org.scalatest" %%% "scalatest" % "3.1.1" % "test"
)

lazy val root = project.in(file("."))
  .aggregate(yaidombridgeJVM, yaidombridgeJS)
  .settings(commonSettings: _*)
  .settings(
    name                 := "yaidombridge",
    // Thanks, scala-java-time, for showing us how to prevent any publishing of root level artifacts:
    // No, SBT, we don't want any artifacts for root. No, not even an empty jar.
    publish              := {},
    publishLocal         := {},
    publishArtifact      := false,
    Keys.`package`       := file(""))

lazy val yaidombridge = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Full)
  .in(file("."))
  .settings(commonSettings: _*)
  .jvmSettings(
    // By all means, override this version of Saxon if needed, possibly with a Saxon-EE release!

    libraryDependencies += "net.sf.saxon" % "Saxon-HE" % "9.9.1-7",

    libraryDependencies += "org.scalacheck" %%% "scalacheck" % "1.14.3" % "test",

    // mimaPreviousArtifacts := Set("eu.cdevreeze.yaidombridge" %%% "yaidombridge" % "0.1.0")
  )
  .jsSettings(
    // Do we need this jsEnv?
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),

    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "1.0.0",

    Test / parallelExecution := false,

    // mimaPreviousArtifacts := Set("eu.cdevreeze.yaidombridge" %%% "yaidombridge" % "0.1.0")
  )

lazy val yaidombridgeJVM = yaidombridge.jvm

lazy val yaidombridgeJS = yaidombridge.js

lazy val pomData =
  <url>https://github.com/dvreeze/yaidombridge</url>
  <licenses>
    <license>
      <name>Apache License, Version 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
      <distribution>repo</distribution>
      <comments>Yaidombridge is licensed under Apache License, Version 2.0</comments>
    </license>
  </licenses>
  <scm>
    <connection>scm:git:git@github.com:dvreeze/yaidombridge.git</connection>
    <url>https://github.com/dvreeze/yaidombridge.git</url>
    <developerConnection>scm:git:git@github.com:dvreeze/yaidombridge.git</developerConnection>
  </scm>
  <developers>
    <developer>
      <id>dvreeze</id>
      <name>Chris de Vreeze</name>
      <email>chris.de.vreeze@caiway.net</email>
    </developer>
  </developers>
