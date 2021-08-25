# libre-wsdl4j - A fork of WSDL4J 1.6.3.

Libre-wsdl4j is a fork of WSDL4J 1.6.3. WSL4j is a Java stub generator for WSDL. Many software projects still depends on WSL4j, so the idea is that libre-wsdl4j should be an actively maintained version of this software on GitHub.

Pull requests welcome! The last release of the original WSDL4J was in Feb, 2013.

WSDL4J is described as "The Web Services Description Language for Java Toolkit (WSDL4J) allows the creation, representation, and manipulation of WSDL documents. It is the reference implementation for JSR110 'JWSDL'", by the original authors.

JSR110 - https://jcp.org/en/jsr/detail?id=110

This source code was downloaded from the original WSDL4j sourceforge page:  https://sourceforge.net/projects/wsdl4j/

License: Common Public License 1.0 


 ![CI](https://github.com/librewsdl4j/libre-wsdl4j/workflows/libre-wsdl4j%20maven%20build/badge.svg)
 [![Build Status](https://travis-ci.org/librewsdl4j/libre-wsdl4j.svg?branch=master)](https://travis-ci.org/librewsdl4j/libre-wsdl4j)
 [![Code Quality: Java](https://img.shields.io/lgtm/grade/java/g/librewsdl4j/libre-wsdl4j.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/librewsdl4j/libre-wsdl4j/)
 
 
   "WSDL, that's an anchronym i haven't heard in almost ten years."  
   "Still big in the enterprise world" - comments on the Internet. 

## Changes:
* Modified PopulatedExtensionRegistry, so that external HTTP requests 
to fetch .xsd files references in the .wsdl files are not fetched. 
This would cause connection refused errors when the .xsd files are not
available.
* Ported from Ant to Maven.


