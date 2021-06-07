<!--
  Title: Simple API for SGML
  Description: SGML Parser.
  Author: hamdikarray
  -->


# Simple API for SGML
Simple API for SGML (SAS) is an event-driven, serial-access mechanism for accessing SGML documents with JAVA.
Very similar to SAX it allows you to handle events :

- Error Handler : to handle warning, error and fatal erros
- Declaration Handler : events related to the parsing of the SGML declaration part
- Definition Handler : events related to the parsing of the SGML definition part (DTD)
- Instance Handler : events related to the parsing of the SGML document it self

See the [documentation](https://hamdikarray.github.io/simple-api-for-sgml-parser-dist/docs/apidocs/index.html)

Very simple to use, it allows you also to deal with the SGML document as XML document :

- Convert from SGML to XML with many options
- Convert from XML back to SGML
    - infine allows converting from SGML to SGML
    - transparency : dealing only with XML

- Transforming SGML with XSLT transform for various engine like SAXON API (directly as a source or throw extension function) 
- Thread SAFE : very usefull for big data

For evaluation, create an issue with the following informations : 

|Information   |
|---|
| * First Name   |
| * Last Name  	  |
|  Company |
|  * Country  	 |
|  * Email |
|  Phone |


For running : 
```
java -cp "lib/*" \
-Dsgml.catalog.files=file:/u/user/sgml.catalog \
com.sitc.api.sas.main.Main \
$*
```

- sgml.catalog.files : System property to set the Catalogs files (needs to be URIs and at least one SGML declration)
- $* : SGML File paths

It's a beta version. 
SGML is an old standard that is often very complicated: do not hesitate to create issues. We will try to be very reactive
