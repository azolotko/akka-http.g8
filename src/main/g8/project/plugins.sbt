addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.9.0")
addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.3.4")
addSbtPlugin("com.geirsson" % "sbt-scalafmt" % "1.6.0-RC3")
addSbtPlugin("org.wartremover" % "sbt-wartremover" % "2.4.1")

resolvers += Resolver.bintrayRepo("sbt", "sbt-plugin-releases")
