
package com.ws.nav_server.demo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ws.nav_server.demo package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetWeatherInfoByCityName_QNAME = new QName("http://demo.nav_server.ws.com/", "getWeatherInfoByCityName");
    private final static QName _GetWeatherInfoByCityNameResponse_QNAME = new QName("http://demo.nav_server.ws.com/", "getWeatherInfoByCityNameResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ws.nav_server.demo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetWeatherInfoByCityName }
     * 
     */
    public GetWeatherInfoByCityName createGetWeatherInfoByCityName() {
        return new GetWeatherInfoByCityName();
    }

    /**
     * Create an instance of {@link GetWeatherInfoByCityNameResponse }
     * 
     */
    public GetWeatherInfoByCityNameResponse createGetWeatherInfoByCityNameResponse() {
        return new GetWeatherInfoByCityNameResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWeatherInfoByCityName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://demo.nav_server.ws.com/", name = "getWeatherInfoByCityName")
    public JAXBElement<GetWeatherInfoByCityName> createGetWeatherInfoByCityName(GetWeatherInfoByCityName value) {
        return new JAXBElement<GetWeatherInfoByCityName>(_GetWeatherInfoByCityName_QNAME, GetWeatherInfoByCityName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetWeatherInfoByCityNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://demo.nav_server.ws.com/", name = "getWeatherInfoByCityNameResponse")
    public JAXBElement<GetWeatherInfoByCityNameResponse> createGetWeatherInfoByCityNameResponse(GetWeatherInfoByCityNameResponse value) {
        return new JAXBElement<GetWeatherInfoByCityNameResponse>(_GetWeatherInfoByCityNameResponse_QNAME, GetWeatherInfoByCityNameResponse.class, null, value);
    }

}
