//============================================================================
// Copyright 5AM Solutions, Inc
// Copyright Ekagra Software Technologies Ltd.
// Copyright Guidewire Architecture
// Copyright The Ohio State University Research Foundation
// Copyright Science Applications International Corporation
//
// Distributed under the OSI-approved BSD 3-Clause License.
// See http://ncip.github.com/iso21090/LICENSE.txt for details.
//============================================================================

package gov.nih.nci.iso21090.grid.ser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.xml.namespace.QName;
import javax.xml.rpc.NamespaceConstants;

import org.apache.axis.encoding.SerializationContext;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 *
 * @author gax
 */
public class Filter implements ContentHandler {
	private final SerializationContext context;

	private final Stack<Map<String, String>> prefixes = new Stack<Map<String, String>>();
	private Map<String, String> head = new HashMap<String, String>();

	/**
	 * Constructs a new filter, based on the given context.
	 * @param context serialization context to use
	 */
	public Filter(SerializationContext context) {
		this.context = context;
	}

	/**
	 * {@inheritDoc}
	 */
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		head.put(prefix, uri);
	}

	/**
	 * {@inheritDoc}
	 */
	public void endPrefixMapping(String prefix) throws SAXException {
		head.remove(prefix);
	}

	/**
	 * {@inheritDoc}
	 */
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

		prefixes.push(head);
		head = new HashMap<String, String>();
		
		AttributesImpl fixed = new AttributesImpl();
		for (int i = 0; i < atts.getLength(); i++) {
			String au = atts.getURI(i);
			String av = atts.getValue(i);
			String aln = atts.getLocalName(i);

			if (NamespaceConstants.NSURI_SCHEMA_XSI.equals(au) && "type".equals(aln)) {

				String p = "";
				String q = av;
				
				int idx = av.indexOf(':');
				if (idx != -1) {
					p = av.substring(0, idx);
					q = av.substring(av.indexOf(':') + 1);
				}
				
				String ns = getNS(p);
				String axisPrefix = context.getPrefixForURI(ns);
				av = axisPrefix + ':' + q;
			}
			fixed.addAttribute(au, aln, atts.getQName(i), atts.getType(i), av);
		}

		try {
			context.startElement(new QName(uri, localName), fixed);
		} catch (IOException ioe) {
			throw new SAXException(ioe);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void endElement(String uri, String localName, String qName) throws SAXException {
		head = prefixes.pop();
		try {
			context.endElement();
		} catch (IOException ioe) {
			throw new SAXException(ioe);
		}
	}

	private String getNS(String prefix) {
		String ns = null;
		for (int i = prefixes.size() - 1; i >= 0 && ns == null; i--) {
			ns = prefixes.get(i).get(prefix);
		}
		return ns;
	}

	/**
	 * {@inheritDoc}
	 */
	public void characters(char[] ch, int start, int length) throws SAXException {
		try {
			context.writeChars(ch, start, length);
		} catch (IOException ioe) {
			throw new SAXException(ioe);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void setDocumentLocator(Locator locator) {
		//
	}

	/**
	 * {@inheritDoc}
	 */
	public void startDocument() throws SAXException {
		//
	}

	/**
	 * {@inheritDoc}
	 */
	public void endDocument() throws SAXException {
		//
	}

	/**
	 * {@inheritDoc}
	 */
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		//
	}

	/**
	 * {@inheritDoc}
	 */
	public void processingInstruction(String target, String data) throws SAXException {
		//
	}

	/**
	 * {@inheritDoc}
	 */
	public void skippedEntity(String name) throws SAXException {
		//
	}

}
