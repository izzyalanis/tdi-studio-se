/*
 * An XML document type.
 * Localname: ArrayOfint
 * Namespace: http://schemas.microsoft.com/2003/10/Serialization/Arrays
 * Java type: com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument
 *
 * Automatically generated - do not modify.
 */
package com.microsoft.schemas._2003._10.serialization.arrays;


/**
 * A document containing one ArrayOfint(@http://schemas.microsoft.com/2003/10/Serialization/Arrays) element.
 *
 * This is a complex type.
 */
public interface ArrayOfintDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ArrayOfintDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s7EBB4BC9E00A8FB74293D27D6A5BA466").resolveHandle("arrayofintcaefdoctype");
    
    /**
     * Gets the "ArrayOfint" element
     */
    com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfint getArrayOfint();
    
    /**
     * Tests for nil "ArrayOfint" element
     */
    boolean isNilArrayOfint();
    
    /**
     * Sets the "ArrayOfint" element
     */
    void setArrayOfint(com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfint arrayOfint);
    
    /**
     * Appends and returns a new empty "ArrayOfint" element
     */
    com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfint addNewArrayOfint();
    
    /**
     * Nils the "ArrayOfint" element
     */
    void setNilArrayOfint();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument newInstance() {
          return (com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfintDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
