package com.mk.certificate;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.Map;

/**
 * @Classname XmlUtilTest
 * @Created by Michael
 * @Date 2021/9/28
 * @Description test XmlUtil.java
 */
public class XmlUtilTest {
  @Test
  public void xmlSignTest() throws Exception {
    String salt = "abc123";
    String path = "D:\\work\\ASL\\truststore_2021.jks";
    String aliase = "ehr_encipherment_2021";
    File jksFile = new File(path);
    KeyStore ks = XmlUtil.getKeyStore(salt, jksFile);
    Key key = ks.getKey(aliase, salt.toCharArray());
    Map<String, Certificate> cerMap = XmlUtil.loadCertifications(ks);
    Certificate cer = ks.getCertificate(aliase);
    KeyPair kp = new KeyPair(cer.getPublicKey(), (PrivateKey) key);
    String signXml = XmlUtil.sign("<a>aaa</a>", kp, cer);
    System.out.println(signXml);
  }

  @Test
  public void loadJKSTest() {
    Map<String, Certificate> cerMap = getCerByJks();
    Assert.assertNotNull(cerMap);

  }

  private Map<String, Certificate> getCerByJks() {
    String salt = "abc123";
    String path = "D:\\work\\ASL\\truststore_2021.jks";
    File jksFile = new File(path);
    KeyStore ks = XmlUtil.getKeyStore(salt, jksFile);
    Map<String, Certificate> cerMap = XmlUtil.loadCertifications(ks);
    return cerMap;
  }
}
