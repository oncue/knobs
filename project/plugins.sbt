
resolvers ++= Seq(
  "Sonatype Nexus Intel Media Maven Group" at "http://nexus.svc.oncue.com/nexus/content/groups/intel_media_maven/",
  "Sonatype Nexus Intel Media Ivy Group" at "http://nexus.svc.oncue.com/nexus/content/groups/intel_media_ivy/"
)

addSbtPlugin("oncue.build" %% "sbt-oncue" % "6.4.+")

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
