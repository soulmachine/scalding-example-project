/*
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
import sbt._

object Dependencies {
  val resolutionRepos = Seq(
    Resolver.mavenLocal,
    "ScalaTools snapshots at Sonatype" at "https://oss.sonatype.org/content/repositories/snapshots/",
    "Concurrent Maven Repo" at "http://conjars.org/repo", // For Scalding, Cascading etc
    "Cloudera Repo" at "https://repository.cloudera.com/cloudera/cloudera-repos/"
  )

  object V {
    val scalding  = "0.13.1"
    val hadoop    = "2.5.0-cdh5.3.1"
    val scalaTest    = "2.2.4"
    // Add versions for your additional libraries here...
  }

  object Libraries {
    val scaldingCore = "com.twitter"                %%  "scalding-core"       % V.scalding
    val hadoopClient   = "org.apache.hadoop"          %   "hadoop-client"         % V.hadoop       % "provided"
    // Add additional libraries from mvnrepository.com (SBT syntax) here...

    // Scala (test only)
    val scalaTest    = "org.scalatest" % "scalatest_2.10" % V.scalaTest % "test"
  }
}
