# Scalding Example Project [![Build Status](https://travis-ci.org/soulmachine/scalding-example-project.png)](https://travis-ci.org/soulmachine/scalding-example-project)

## Introduction

This is Twitter's [`WordCountJob`] [wordcount] example for [Scalding] [scalding] adapted to run on Hadoop. It also includes ScalaTest tests. This project is ported directly from [Snowplow Analytics] [snowplow]'s [Scalding Example Project](https://github.com/snowplow/scalding-example-project).

_See also:_ [Spark Example Project] [spark-example-project]

## Building

Assuming you already have SBT installed:

    $ git clone git://github.com/soulmachine/scalding-example-project.git
    $ cd scalding-example-project
    $ sbt assembly

The 'fat jar' is now available as:

    target/scalding-example-project-1.0.jar

## Unit testing

The `assembly` command above runs the test suite - but you can also run this manually with:

    $ sbt test
    <snip>
    [info] + A WordCount job should
	[info]   + count words correctly
	[info] Passed: : Total 2, Failed 0, Errors 0, Passed 2, Skipped 0

## Submit the job to Hadoop

    yarn jar scalding-example-1.0.jar me.soulmachine.WordCountJob --hdfs --input wordcount-test/input --output wordcount-test/output
