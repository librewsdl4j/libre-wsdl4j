# libre-wsdl4j - A fork of WSDL4J 1.6.3.

Libre-wsdl4j is a fork of WSDL4J 1.6.3. WSL4j is a Java stub generator for WSDL. Many software projects still depends on WSL4j, so the idea is that libre-wsdl4j should be an actively maintained version of this software.

Pull requests welcome! The last release of the original WSDL4J was in Feb, 2013.

Source code downloaded from:  https://sourceforge.net/projects/wsdl4j/

License: Common Public License 1.0 

 [![Build Status](https://travis-ci.org/librewsdl4j/libre-wsdl4j.svg?branch=master)](https://travis-ci.org/librewsdl4j/libre-wsdl4j)
 [![Code Quality: Java](https://img.shields.io/lgtm/grade/java/g/librewsdl4j/libre-wsdl4j.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/librewsdl4j/libre-wsdl4j/context:java)
 

## Changes:
* Modified PopulatedExtensionRegistry, so that external HTTP requests 
to fetch .xsd files references in the .wsdl files are not fetched. 
This would cause connection refused errors when the .xsd files are not
available.
* Ported from Ant to Maven.


