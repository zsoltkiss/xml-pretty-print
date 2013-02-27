package hu.zsoltkiss.xml.prettyprint;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class PrettyPrinter {

	public static void main(String args[]) {
		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = dbf.newDocumentBuilder();

			Document doc = builder.parse(PrettyPrinter.class
					.getResourceAsStream("soapmessage.xml"));
			
			Element root = doc.getDocumentElement();
			
			System.out.println(PrettyPrinter.dom2String(root));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static String dom2String(Node node) throws TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		//initialize StreamResult with File object to save to file
		StreamResult result = new StreamResult(new StringWriter());
		DOMSource source = new DOMSource(node);
		transformer.transform(source, result);
		String xmlString = result.getWriter().toString();
		return xmlString;
	}


}
