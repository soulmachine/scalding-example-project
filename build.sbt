// Your sbt build file. Guides on how to write one can be found at
// http://www.scala-sbt.org/0.13/docs/index.html

// Project name
name := "scalding-example"

// Don't forget to set the version
version := "1.0.0-SNAPSHOT"

// All Scalding Packages need a license
licenses := Seq("Apache-2.0" -> url("http://opensource.org/licenses/Apache-2.0"))

// scala version to be used
scalaVersion := "2.11.8"
// force scalaVersion
//ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

// scalding version to be used
val saldingVersion = "0.16.0"

// Java version
javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

// Use local repositories by default
resolvers ++= Seq(
  Resolver.defaultLocal,
  Resolver.mavenLocal,
  // make sure default maven local repository is added... Resolver.mavenLocal has bugs.
  "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository",
  "Conjars Repo" at "http://conjars.org/repo",  // For Scalding, Cascading etc
  "Twitter Repo" at "http://maven.twttr.com/" // For com.hadoop.gplcompression:hadoop-lzo
)


// Dependencies
libraryDependencies ++= Seq(
  "org.apache.hadoop" % "hadoop-client" % "2.7.1" % "provided",
  "com.twitter" %% "scalding-core" % saldingVersion,
  "com.twitter" %% "scalding-args" % saldingVersion,
  "com.twitter" %% "scalding-date" % saldingVersion,
  "com.twitter" %% "scalding-commons" % saldingVersion,
  "com.twitter" %% "scalding-avro" % saldingVersion,
  "com.twitter" %% "scalding-parquet" % saldingVersion
)
// testing
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"

mainClass in Compile := Some("com.twitter.scalding.Tool")


// sbt-assembly settings for building a fat jar
// Reference: https://github.com/danosipov/sbt-scalding-plugin/blob/master/src/main/scala/com/danosipov/sbtplugin/scalding/ScaldingPlugin.scala
// Slightly cleaner jar name
assemblyJarName in assembly := s"${name.value}-${version.value}.jar"
mainClass in assembly := Some("com.twitter.scalding.Tool")

  // Drop these jars
assemblyExcludedJars in assembly := {
  val cp = (fullClasspath in assembly).value
//assemblyExcludedJars in assembly <<= (fullClasspath in assembly) map { cp =>
  cp.filter { jar =>
    val name = jar.data.getName
    name.matches("scala-compiler[0-9\\.\\-]*\\.jar") ||
      name.matches("jsp-api[0-9\\.\\-]*\\.jar") ||
      name.matches("jsp[0-9\\.\\-]*\\.jar") ||
      name.matches("jasper-compiler[0-9\\.\\-]*\\.jar") ||
      name.matches("minlog[0-9\\.\\-]*\\.jar") ||
      name.matches("janino[0-9\\.\\-]*\\.jar") ||
      name.matches("commons-beanutils-core[0-9\\.\\-]*\\.jar") ||
      name.matches("commons-beanutils[0-9\\.\\-]*\\.jar") ||
      name.matches("stax-api[0-9\\.\\-]*\\.jar") ||
      name.matches("asm[0-9\\.\\-]*\\.jar") ||
      name.matches("scalatest[0-9\\.\\-]*\\.jar") ||
      name.matches("mockito[0-9\\.\\-]*\\.jar")
  }
}

assemblyMergeStrategy in assembly := {
  case "project.clj" => MergeStrategy.discard // Leiningen build files
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

