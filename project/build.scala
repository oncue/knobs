import sbt._, Keys._

object Build extends Build {

  lazy val buildSettings =
    Defaults.defaultSettings ++
    ImBuildPlugin.imBuildSettings ++ Seq(
      organization := "knobs",
      scalaVersion := "2.10.3",
      scalacOptions ++= Seq(
        "-feature"
      ),
      resolvers ++= Seq(
        "tpolecat"  at "http://dl.bintray.com/tpolecat/maven",
        "runar"     at "http://dl.bintray.com/runarorama/maven/",
        "ermine"    at "http://dl.bintray.com/ermine/maven/")
      // enable these for oflfine working.
      // , offline := true,
      // skip in update := true
    )

  lazy val root = Project(
    id = "knobs",
    base = file("."),
    settings = buildSettings ++ Seq(
      publishArtifact in (Compile, packageBin) := false,
      publish := (),
      publishLocal := ()
    )
  )
  .settings(ScctPlugin.mergeReportSettings:_*)
  .aggregate(core, typesafe)

  lazy val core = Project("core", file("core"))
    .settings(buildSettings:_*)
    .settings(libraryDependencies ++= Seq(
      "org.scalaz"     %% "scalaz-core"       % "7.0.5",
      "org.scalaz"     %% "scalaz-concurrent" % "7.0.5",
      "scala-parsers"  %% "scala-parsers"     % "0.1",
      "org.spire-math" %% "spire"             % "0.6.0",
      "org.scalacheck" %% "scalacheck"        % "1.10.1" % "test"
    ))

  lazy val typesafe = Project("typesafe", file("typesafe")).dependsOn(core).
    settings(buildSettings:_*).settings(
      libraryDependencies ++= Seq(
        "com.typesafe" % "config" % "1.2.0"
      ))

  // lazy val aws = Project("aws", file("aws"))
  //   .settings(buildSettings:_*)
  //   .settings(
  //     libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.7.1"
  //   )
  //   .dependsOn(core)
}