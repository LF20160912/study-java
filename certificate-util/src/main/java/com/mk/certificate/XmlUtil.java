package com.mk.certificate;

import jdk.internal.org.xml.sax.InputSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.crypto.KeySelector;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.security.KeyPair;
import java.security.Provider;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Classname XmlUtil
 * @Created by Michael
 * @Date 2021/9/27
 * @Description Xml operated tool
 */
public class XmlUtil {
  public static boolean verifyMultipleXmlSignature(String xmlString,
                                                   Map<String, Certificate> certs) {

    for (String key : certs.keySet()) {
      Certificate cert = certs.get(key);
      if (cert != null) {
        if (verifyXmlSignature(xmlString, cert)) {
//          logger.info(key + " is valid and applied");
          return true;
        }
      } else {
//        logger.error(key + " is null");
      }

    }

//    logger.error("both cert are invalid.");

    return false;
  }

  public static void xmlSign(Element element, KeyPair kp, Certificate cert) {
    try {
      DOMSignContext dsc = new DOMSignContext(kp.getPrivate(), element);

      String providerName =
              System.getProperty("jsr105Provider", "org.jcp.xml.dsig.internal.dom.XMLDSigRI");

      XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM",
              (Provider) Class.forName(providerName).newInstance());

      Reference ref =
              fac.newReference("", fac.newDigestMethod(DigestMethod.SHA256, null),
                      Collections.singletonList(
                              fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)),
                      null, null);
      SignedInfo si = fac.newSignedInfo(
              fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,
                      (C14NMethodParameterSpec) null),
              // fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
              fac.newSignatureMethod("http://www.w3.org/2001/04/xmldsig-more#rsa-sha256", null),

              Collections.singletonList(ref));

      KeyInfoFactory kif = fac.getKeyInfoFactory();
      List<Object> x509 = new ArrayList<Object>();
      x509.add(((X509Certificate) cert).getSubjectDN().getName());
      x509.add(cert);
//      logger.debug("[getSubjectDN] = " + ((X509Certificate) cert).getSubjectDN().getName());

      X509Data x509Data = null;
      try {
        x509Data = kif.newX509Data(x509);

      } catch (IllegalArgumentException e) {
//        logger.info("Skip to generate Subject DN! " + e.getMessage());
        x509.clear();
        x509.add(cert);
        x509Data = kif.newX509Data(x509);
      }
      List<Object> items = new ArrayList<Object>();
      items.add(x509Data);

      KeyInfo ki = kif.newKeyInfo(items);

      XMLSignature signature = fac.newXMLSignature(si, ki);
      signature.sign(dsc);

    } catch (Exception ex) {

      ex.printStackTrace();
    }
  }

  public static boolean verifyXmlSignature(String xmlString, Certificate cert) {
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);

      Document doc = dbf.newDocumentBuilder().parse(new ByteArrayInputStream(xmlString.getBytes()));

      NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
      if (nl.getLength() == 0) {
//        logger.error("Cannot find Signature element");
        return false;
      }

      PublicKey publicKey = cert.getPublicKey();

      String providerName =
              System.getProperty("jsr105Provider", "org.jcp.xml.dsig.internal.dom.XMLDSigRI");
      XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM",
              (Provider) Class.forName(providerName).newInstance());
      DOMValidateContext valContext =
              new DOMValidateContext(KeySelector.singletonKeySelector(publicKey), nl.item(0));

      XMLSignature signature = fac.unmarshalXMLSignature(valContext);
      return signature.validate(valContext);
    } catch (Exception e) {
//      logger.error(e.getMessage(), e);
      return false;
    }
  }

  public static String sign(String xmlMessage,KeyPair kp,Certificate cer) throws ParserConfigurationException, IOException, SAXException {

    // System.out.println("Before signed message: ");
    // System.out.println(xmlMessage);

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
    dbf.setIgnoringElementContentWhitespace(true);
    dbf.setNamespaceAware(true);
    Document doc;
    DocumentBuilder db = dbf.newDocumentBuilder();
    InputSource is = new InputSource(new StringReader(xmlMessage));
    doc = db.parse(String.valueOf(is));

    Element element = doc.getDocumentElement();

    xmlSign(element, kp, cer);

    String result = JaxbUtil.asXml(element);
    // System.out.println("After signed message: ");
    // System.out.println(result);
    return result;
  }

  public static String trimXml(String xmlMessage) {
    // Remove all indents, new line and space between element tag
    StringBuffer sb = new StringBuffer();
    try (BufferedReader reader = new BufferedReader(new StringReader(xmlMessage))) {
      String line;
      while ((line = reader.readLine()) != null)
        sb.append(line.trim());
      return sb.toString();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
