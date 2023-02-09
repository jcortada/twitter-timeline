package com.atc.portal.turisme.model.dto;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.Base64;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import lombok.Data;

@Data
public class DocumentDto {
	private static String expressionTotalIngressar = "/DECLARACION/XML/ATC_SOLLICITUD/Cos/Contingut/DadesParticulars/Autoliquidacio/TotalIngressar";
	private static String expressionPeriode = "/DECLARACION/XML/ATC_SOLLICITUD/Cos/Contingut/DadesParticulars/ExerciciPeriode/Periode";
	private static String expressionExercici = "/DECLARACION/XML/ATC_SOLLICITUD/Cos/Contingut/DadesParticulars/ExerciciPeriode/Exercici";
	private static String expressionPresentador = "/DECLARACION/XML/ATC_SOLLICITUD/Cos/Intervinents/Intervinent[@tipusIntervencio=\"presentador\"]/Document/Identificador";	

	private static final Logger log = LoggerFactory.getLogger(DocumentDto.class);

	private Document doc;
	private String content;
	private String docReference;
	private String periode;
	private String exercici;
	private BigDecimal amount;
	private String presenter;

	public DocumentDto(String docReference, String document) {
		this.docReference = docReference;
		this.content = document;
		
		if(parseDocument().equals("DECLARACION"))
			loadValues();

	}

	
	public String getXmlDocument() {
		byte[] decodedBytes = Base64.getDecoder().decode(content);
		return new String(decodedBytes);
	}
	
	
	private void loadValues() {

		exercici = getElement(expressionExercici);
		periode = getElement(expressionPeriode);
		presenter = getElement(expressionPresentador);
		amount = new BigDecimal(getElement(expressionTotalIngressar));
		log.info("Xml values: {}, {}, {}", exercici, periode, amount);
	}

	private String parseDocument() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

			DocumentBuilder db = dbf.newDocumentBuilder();

			doc = db.parse(new InputSource(new StringReader(getXmlDocument())));
			doc.getDocumentElement().normalize();

			String rootElement = doc.getDocumentElement().getNodeName();
			
			log.info("Root Element :\" + {}", rootElement);
			
			return rootElement;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
	}

	public String getElement(String expression) {
		XPath xPath = XPathFactory.newInstance().newXPath();
		try {
			NodeList childs = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

			Node child = childs.item(0);
			short nodeType = child.getNodeType();
			if (nodeType == Node.ELEMENT_NODE) {
				Node firstChild = child.getFirstChild();
				return firstChild.getNodeValue().trim();				
			}

		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
}
