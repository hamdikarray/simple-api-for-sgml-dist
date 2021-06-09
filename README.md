# Simple API for SGML
Simple API for SGML (SAS) is an event-driven, serial-access mechanism for accessing SGML documents with JAVA.
Very similar to SAX it allows you to handle events :

- Error Handler : to handle warning, error and fatal erros
- Declaration Handler : events related to the parsing of the SGML declaration part
- Definition Handler : events related to the parsing of the SGML definition part (DTD)
- Instance Handler : events related to the parsing of the SGML document it self


Very simple to use, it allows you also to deal with the SGML document as XML document :

- Convert from SGML to XML with many options
- Convert from XML back to SGML
    - infine allows converting from SGML to SGML
    - transparency : dealing only with XML

- Transforming SGML with XSLT transform for various engine like SAXON API (directly as a source or throw extension function) 
- Thread SAFE : very usefull for big data


## Usage

Like SAX, we need to declare a Reader :  

```
final SASParserFactory factory = SASParserFactory.newInstance();
factory.setValidating(true);

final CatalogResolver catalogResolver = new CatalogResolver();
catalogResolver.addCatalog("file:/d:/sgml/sgml.catalog");

SASParser parser = factory.newSASParser();
Reader reader = parser.getSGMLReader();
reader.setEntityResolver(catalogResolver);

```

This is a catalog sample : 

```

SGMLDECL "docbook.dec"
PUBLIC "ISO 8879-1986//ENTITIES Added Latin 1//EN" "entities/Isolat1.ent"
DOCTYPE BOOK "doctypes/book.dtd"

```

**The SGMLDECL and the DOCTYPE entries are mandatory.**

For dealing with SGML events : 

```
reader.setInstanceHandler(new InstanceHandler() {
		
		@Override
		public void startElement(String[] grpList, String qName) throws SASException {
		}
		
		@Override
		public void startElement(String qName) throws SASException {
		}
		
		@Override
		public void startDocument() throws SASException {
		}
		
		@Override
		public void startContent() throws SASException {
		}
		
		@Override
		public void setDocumentLocator(Locator locator) {
		}
		
		@Override
		public void processingInstruction(String content) throws SASException {
		}
		
		@Override
		public void endElement(String[] grpList, String qName) throws SASException {
		}
		
		@Override
		public void endElement(String qName) throws SASException {
		}
		
		@Override
		public void endDocument() throws SASException {
		}
		
		@Override
		public void emptyStartElement() throws SASException {
		}
		
		@Override
		public void emptyEndElement() throws SASException {
		}
		
		@Override
		public void comment(String string) throws SASException {
		}
		
		@Override
		public void characters(char[] ch, int start, int length) throws SASException {
		}
		
		@Override
		public void attribute(String qName, String value) throws SASException {
		}
});
				
reader.parse(new InputSource("file:/d:/sgml/file.sgm"));

```

or more simple for converting to XML document : 

```

XMLReader xr = reader.asXMLReader();
Source src = new SAXSource(xr, new InputSource("file:/d:/sgml/file.sgm"));

Result res = new StreamResult("file:/d:/sgml/file.xml");
Transformer transformer = TransformerFactory.newInstance().newTransformer();
transformer.transform(src, res);

```

We can configure the SASParserFactory : 

```
factory.setFeature(SASParserFeatures.QUANTITY_CHECKING, true);
factory.setFeature(SASParserFeatures.CAPACITY_CHECKING, false);
factory.setFeature(SASParserFeatures.ID_REF_CHECKING, true);
```


## Version
### Home Edition 

See the [documentation](https://hamdikarray.github.io/simple-api-for-sgml-dist/docs/he/apidocs/index.html)

This is an evaluation version with some features desactivated : 
- Fully java support for SAS like support, conversion in XML, parsing SGML declarartion and DTD definition  
- Some SGML declaration features not supported : DATATAG, RANK
- Somme DTD features not supported : USEMAP, PIENTITY, STARTAGENTITY, ENDTAGENTITY and MSENTITY
- Some instance handler event deactivated for entities and attrbutes : all event fired as character event or ignored 
- Limited time evaluation : Evaluation licenses must not be used for production work. On about 1% of time, it will insert random asterisks into serialized output.

### Professional Edition 

See the [documentation](https://hamdikarray.github.io/simple-api-for-sgml-dist/docs/pe/apidocs/index.html)

adds a number of features to HE version : 
- All SGML declaration features supported
- All DTD features supported
- Fully instance handler event support 

### Entreprise Edition (coming soon) 
The fully-featured product with in addition : 
- SGML reader and writer support : 
  - Directly for performance 
  - Transparency with XML support
- DTD canonizing
- ...

## Evaluation
For PE or EE version, please create an issue with the following informations : 

|Information   | |
|---|---|
| * First Name   | |
| * Last Name  	  | |
|  Company | |
|  * Country  	 | |
|  * Email | |
|  Phone | |