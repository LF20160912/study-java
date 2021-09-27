package com.mk.certificate;

/**
 * @Classname JaxbUtil
 * @Created by Michael
 * @Date 2021/9/27
 * @Description operate xml element
 */
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class JaxbUtil {

  private static DocumentBuilder documentBuilder;

  @SuppressWarnings("unchecked")
  public static String asXml(Marshaller m, JAXBElement e) {
    try {
      StringWriter sw = new StringWriter();
      m.marshal(e, sw);
      return sw.toString();
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public static String asXml(Element e) {
    try {
      StringWriter sw = new StringWriter();
      TransformerFactory tf = TransformerFactory.newInstance();
      Transformer trans = tf.newTransformer();
      trans.transform(new DOMSource(e), new StreamResult(sw));
      return sw.toString().replaceAll("&#13;", "");
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  @SuppressWarnings("unchecked")
  public static Element marshalElement(Marshaller m, JAXBElement e) throws Exception {
    if (null == e) {
      return null;
    }

    Document document = getDocumentBuilder().newDocument();
    m.marshal(e, document);
    return document.getDocumentElement();
  }

  private static DocumentBuilder getDocumentBuilder() throws Exception {
    // Lazy load the DocumentBuilder as it is not used for unmarshalling.
    if (null == documentBuilder) {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      documentBuilder = dbf.newDocumentBuilder();
    }
    return documentBuilder;
  }

  @SuppressWarnings("unchecked")
  public static <T> T unmarshalElement(Unmarshaller u, Element element, Class<T> declaredType)
          throws Exception {
    if (null == element) {
      return null;
    }

    DOMSource source = new DOMSource(element);
    JAXBElement jaxbElement = u.unmarshal(source, declaredType);

    return (T) jaxbElement.getValue();
  }
}