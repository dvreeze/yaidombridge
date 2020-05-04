============
Yaidombridge
============

Introduction
============

This project contains conversion between yaidom and yaidom2, in both directions. Some conversions make deep copies, and
some conversions are cheaply instantiated "views".

Usage
=====

Yaidombridge versions can be found in the Maven central repository. Assuming version 0.1.0, yaidombridge can be added as dependency
as follows (in an SBT or Maven build):

**SBT**::

    libraryDependencies += "eu.cdevreeze.yaidombridge" %%% "yaidombridge" % "0.1.0"

**Maven2**::

    <dependency>
      <groupId>eu.cdevreeze.yaidombridge</groupId>
      <artifactId>yaidombridge_2.13</artifactId>
      <version>0.1.0</version>
    </dependency>

Note that yaidombridge itself depends on yaidom and yaidom2, and their transitive dependencies.

Yaidombridge requires Java version 1.8 or later.
