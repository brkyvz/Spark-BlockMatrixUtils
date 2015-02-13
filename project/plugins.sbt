// You may use this file to add plugin dependencies for sbt.
scalaVersion := "2.10.4"

resolvers += "Spark Packages repo" at "https://dl.bintray.com/spark-packages/maven/"

lazy val sparkPackagesPlugin = RootProject(file("Users/burakyavuz/Documents/sbt-spark-packages"))

lazy val root = Project(id = "plugins", base = file(".")).dependsOn(sparkPackagesPlugin)