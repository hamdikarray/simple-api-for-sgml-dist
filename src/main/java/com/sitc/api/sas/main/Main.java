package com.sitc.api.sas.main;

import java.io.IOException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.InputSource;
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
	 * @throws SASException the SAS exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws SASException, IOException {
		convert(args);
	}

	/**
	 * Convert.
	 *
	 * @param args the args
	 */
	public static void convert(String[] args) {

		
		System.out.println("start ...");

		for (String filePath : args) {
			try {
				final SASParserFactory factory = SASParserFactory.newInstance();
				factory.setValidating(true);

				final CatalogResolver catalogResolver = new CatalogResolver();
				//catalogResolver.addCatalog("file:/d:/sgml/catalog/sgml.catalog");

				SASParser parser = factory.newSASParser();
				Reader reader = parser.getSGMLReader();
				reader.setEntityResolver(catalogResolver);

				XMLReader xr = reader.asXMLReader();
				Source src = new SAXSource(xr, new InputSource(filePath));

				Result res = new StreamResult(filePath+ ".xml");
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				transformer.transform(src, res);
				System.out.println("Converting [" + filePath + "] : done");
			} catch (Exception e) {
				System.err.println("Converting [" + filePath + "] : error");
				e.printStackTrace();
			}
		}

		System.out.println("end ...");

	}
	
}
