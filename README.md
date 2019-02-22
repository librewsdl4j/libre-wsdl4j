# libre-wsdl4j (A fork of WSDL4J 1.6.3) 

A fork of WSDL4J 1.6.3. WSL4j is a Java stub generator for WSDL.

 [![Build Status](https://travis-ci.org/librewsdl4j/libre-wsdl4j.svg?branch=master)](https://travis-ci.org/librewsdl4j/libre-wsdl4j)

Pull requests welcome!


Source code downloaded from:  https://sourceforge.net/projects/wsdl4j/

License: Common Public License 1.0 

## Changes:
* Modified PopulatedExtensionRegistry, so that external HTTP requests 
to fetch .xsd files references in the .wsdl files are not fetched. 
This would cause connection refused errors when the .xsd files are not
available.
* Ported from Ant to Maven.


