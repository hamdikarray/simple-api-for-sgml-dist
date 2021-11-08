package com.sitc.api.sas.main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.XMLReader;

import com.sitc.api.sas.exception.SASException;
import com.sitc.api.sas.handler.impl.CatalogResolver;
import com.sitc.api.sas.parser.SASParser;
import com.sitc.api.sas.parser.SASParserFactory;
import com.sitc.api.sas.reader.Reader;

/**
 * The Class Main.
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws SASException                         the SAS exception
	 * @throws IOException                          Signals that an I/O exception
	 *                                              has occurred.
	 * @throws TransformerException
	 * @throws TransformerFactoryConfigurationError
	 * @throws FactoryConfigurationError
	 * @throws XMLStreamException
	 */
	public static void main(String[] args) throws SASException, IOException, XMLStreamException,
			FactoryConfigurationError, TransformerFactoryConfigurationError, TransformerException {
		convert(new String[] { "file:/d:/sgml/sgml.catalog" }, "d:/sgml/file.sgm", "d:/sgml/file.xml");
	}

	public static void convert(String[] catalogUris, String inputFilePath, String outputFilePath)
			throws SASException, IOException, XMLStreamException, FactoryConfigurationError,
			TransformerFactoryConfigurationError, TransformerException {

		SASParserFactory factory = SASParserFactory.newInstance();
		factory.setValidating(true);
		SASParser parser = factory.newSASParser();

		CatalogResolver catalogResolver = new CatalogResolver();
		for (String catalogUri : catalogUris) {
			catalogResolver.addCatalog(catalogUri);
		}

		Reader reader = parser.getSGMLReader();
		reader.setValidating(true);
		reader.setEntityResolver(catalogResolver);

		XMLReader xr = reader.asXMLReader();

		Source src = new SAXSource(xr, new org.xml.sax.InputSource(inputFilePath));

		new File(outputFilePath).getParentFile().mkdirs();

		Result res = new StreamResult(outputFilePath);
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.transform(src, res);

	}

}
