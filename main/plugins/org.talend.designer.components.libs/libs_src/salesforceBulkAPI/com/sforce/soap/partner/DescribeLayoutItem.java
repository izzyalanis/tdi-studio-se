package com.sforce.soap.partner;

/**
 * Generated by ComplexTypeCodeGenerator.java. Please do not edit.
 */
public class DescribeLayoutItem implements com.sforce.ws.bind.XMLizable , IDescribeLayoutItem{

    /**
     * Constructor
     */
    public DescribeLayoutItem() {}

    /**
     * element : editableForNew of type {http://www.w3.org/2001/XMLSchema}boolean
     * java type: boolean
     */
    private static final com.sforce.ws.bind.TypeInfo editableForNew__typeInfo =
      new com.sforce.ws.bind.TypeInfo("urn:partner.soap.sforce.com","editableForNew","http://www.w3.org/2001/XMLSchema","boolean",1,1,true);

    private boolean editableForNew__is_set = false;

    private boolean editableForNew;

    @Override
    public boolean getEditableForNew() {
      return editableForNew;
    }

    @Override
    public boolean isEditableForNew() {
      return editableForNew;
    }

    @Override
    public void setEditableForNew(boolean editableForNew) {
      this.editableForNew = editableForNew;
      editableForNew__is_set = true;
    }

    protected void setEditableForNew(com.sforce.ws.parser.XmlInputStream __in,
        com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException, com.sforce.ws.ConnectionException {
      __in.peekTag();
      if (__typeMapper.verifyElement(__in, editableForNew__typeInfo)) {
        setEditableForNew(__typeMapper.readBoolean(__in, editableForNew__typeInfo, boolean.class));
      }
    }

    /**
     * element : editableForUpdate of type {http://www.w3.org/2001/XMLSchema}boolean
     * java type: boolean
     */
    private static final com.sforce.ws.bind.TypeInfo editableForUpdate__typeInfo =
      new com.sforce.ws.bind.TypeInfo("urn:partner.soap.sforce.com","editableForUpdate","http://www.w3.org/2001/XMLSchema","boolean",1,1,true);

    private boolean editableForUpdate__is_set = false;

    private boolean editableForUpdate;

    @Override
    public boolean getEditableForUpdate() {
      return editableForUpdate;
    }

    @Override
    public boolean isEditableForUpdate() {
      return editableForUpdate;
    }

    @Override
    public void setEditableForUpdate(boolean editableForUpdate) {
      this.editableForUpdate = editableForUpdate;
      editableForUpdate__is_set = true;
    }

    protected void setEditableForUpdate(com.sforce.ws.parser.XmlInputStream __in,
        com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException, com.sforce.ws.ConnectionException {
      __in.peekTag();
      if (__typeMapper.verifyElement(__in, editableForUpdate__typeInfo)) {
        setEditableForUpdate(__typeMapper.readBoolean(__in, editableForUpdate__typeInfo, boolean.class));
      }
    }

    /**
     * element : label of type {http://www.w3.org/2001/XMLSchema}string
     * java type: java.lang.String
     */
    private static final com.sforce.ws.bind.TypeInfo label__typeInfo =
      new com.sforce.ws.bind.TypeInfo("urn:partner.soap.sforce.com","label","http://www.w3.org/2001/XMLSchema","string",1,1,true);

    private boolean label__is_set = false;

    private java.lang.String label;

    @Override
    public java.lang.String getLabel() {
      return label;
    }

    @Override
    public void setLabel(java.lang.String label) {
      this.label = label;
      label__is_set = true;
    }

    protected void setLabel(com.sforce.ws.parser.XmlInputStream __in,
        com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException, com.sforce.ws.ConnectionException {
      __in.peekTag();
      if (__typeMapper.verifyElement(__in, label__typeInfo)) {
        setLabel(__typeMapper.readString(__in, label__typeInfo, java.lang.String.class));
      }
    }

    /**
     * element : layoutComponents of type {urn:partner.soap.sforce.com}DescribeLayoutComponent
     * java type: com.sforce.soap.partner.DescribeLayoutComponent[]
     */
    private static final com.sforce.ws.bind.TypeInfo layoutComponents__typeInfo =
      new com.sforce.ws.bind.TypeInfo("urn:partner.soap.sforce.com","layoutComponents","urn:partner.soap.sforce.com","DescribeLayoutComponent",0,-1,true);

    private boolean layoutComponents__is_set = false;

    private com.sforce.soap.partner.DescribeLayoutComponent[] layoutComponents = new com.sforce.soap.partner.DescribeLayoutComponent[0];

    @Override
    public com.sforce.soap.partner.DescribeLayoutComponent[] getLayoutComponents() {
      return layoutComponents;
    }

    @Override
    public void setLayoutComponents(com.sforce.soap.partner.IDescribeLayoutComponent[] layoutComponents) {
      this.layoutComponents = castArray(com.sforce.soap.partner.DescribeLayoutComponent.class, layoutComponents);
      layoutComponents__is_set = true;
    }

    protected void setLayoutComponents(com.sforce.ws.parser.XmlInputStream __in,
        com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException, com.sforce.ws.ConnectionException {
      __in.peekTag();
      if (__typeMapper.isElement(__in, layoutComponents__typeInfo)) {
        setLayoutComponents((com.sforce.soap.partner.DescribeLayoutComponent[])__typeMapper.readObject(__in, layoutComponents__typeInfo, com.sforce.soap.partner.DescribeLayoutComponent[].class));
      }
    }

    /**
     * element : placeholder of type {http://www.w3.org/2001/XMLSchema}boolean
     * java type: boolean
     */
    private static final com.sforce.ws.bind.TypeInfo placeholder__typeInfo =
      new com.sforce.ws.bind.TypeInfo("urn:partner.soap.sforce.com","placeholder","http://www.w3.org/2001/XMLSchema","boolean",1,1,true);

    private boolean placeholder__is_set = false;

    private boolean placeholder;

    @Override
    public boolean getPlaceholder() {
      return placeholder;
    }

    @Override
    public boolean isPlaceholder() {
      return placeholder;
    }

    @Override
    public void setPlaceholder(boolean placeholder) {
      this.placeholder = placeholder;
      placeholder__is_set = true;
    }

    protected void setPlaceholder(com.sforce.ws.parser.XmlInputStream __in,
        com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException, com.sforce.ws.ConnectionException {
      __in.peekTag();
      if (__typeMapper.verifyElement(__in, placeholder__typeInfo)) {
        setPlaceholder(__typeMapper.readBoolean(__in, placeholder__typeInfo, boolean.class));
      }
    }

    /**
     * element : required of type {http://www.w3.org/2001/XMLSchema}boolean
     * java type: boolean
     */
    private static final com.sforce.ws.bind.TypeInfo required__typeInfo =
      new com.sforce.ws.bind.TypeInfo("urn:partner.soap.sforce.com","required","http://www.w3.org/2001/XMLSchema","boolean",1,1,true);

    private boolean required__is_set = false;

    private boolean required;

    @Override
    public boolean getRequired() {
      return required;
    }

    @Override
    public boolean isRequired() {
      return required;
    }

    @Override
    public void setRequired(boolean required) {
      this.required = required;
      required__is_set = true;
    }

    protected void setRequired(com.sforce.ws.parser.XmlInputStream __in,
        com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException, com.sforce.ws.ConnectionException {
      __in.peekTag();
      if (__typeMapper.verifyElement(__in, required__typeInfo)) {
        setRequired(__typeMapper.readBoolean(__in, required__typeInfo, boolean.class));
      }
    }

    /**
     */
    @Override
    public void write(javax.xml.namespace.QName __element,
        com.sforce.ws.parser.XmlOutputStream __out, com.sforce.ws.bind.TypeMapper __typeMapper)
        throws java.io.IOException {
      __out.writeStartTag(__element.getNamespaceURI(), __element.getLocalPart());
      writeFields(__out, __typeMapper);
      __out.writeEndTag(__element.getNamespaceURI(), __element.getLocalPart());
    }

    protected void writeFields(com.sforce.ws.parser.XmlOutputStream __out,
         com.sforce.ws.bind.TypeMapper __typeMapper)
         throws java.io.IOException {
       __typeMapper.writeBoolean(__out, editableForNew__typeInfo, editableForNew, editableForNew__is_set);
       __typeMapper.writeBoolean(__out, editableForUpdate__typeInfo, editableForUpdate, editableForUpdate__is_set);
       __typeMapper.writeString(__out, label__typeInfo, label, label__is_set);
       __typeMapper.writeObject(__out, layoutComponents__typeInfo, layoutComponents, layoutComponents__is_set);
       __typeMapper.writeBoolean(__out, placeholder__typeInfo, placeholder, placeholder__is_set);
       __typeMapper.writeBoolean(__out, required__typeInfo, required, required__is_set);
    }

    @Override
    public void load(com.sforce.ws.parser.XmlInputStream __in,
        com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException, com.sforce.ws.ConnectionException {
      __typeMapper.consumeStartTag(__in);
      loadFields(__in, __typeMapper);
      __typeMapper.consumeEndTag(__in);
    }

    protected void loadFields(com.sforce.ws.parser.XmlInputStream __in,
        com.sforce.ws.bind.TypeMapper __typeMapper) throws java.io.IOException, com.sforce.ws.ConnectionException {
        setEditableForNew(__in, __typeMapper);
        setEditableForUpdate(__in, __typeMapper);
        setLabel(__in, __typeMapper);
        setLayoutComponents(__in, __typeMapper);
        setPlaceholder(__in, __typeMapper);
        setRequired(__in, __typeMapper);
    }

    @Override
    public String toString() {
      java.lang.StringBuilder sb = new java.lang.StringBuilder();
      sb.append("[DescribeLayoutItem ");
      sb.append(" editableForNew='").append(com.sforce.ws.util.Verbose.toString(editableForNew)).append("'\n");
      sb.append(" editableForUpdate='").append(com.sforce.ws.util.Verbose.toString(editableForUpdate)).append("'\n");
      sb.append(" label='").append(com.sforce.ws.util.Verbose.toString(label)).append("'\n");
      sb.append(" layoutComponents='").append(com.sforce.ws.util.Verbose.toString(layoutComponents)).append("'\n");
      sb.append(" placeholder='").append(com.sforce.ws.util.Verbose.toString(placeholder)).append("'\n");
      sb.append(" required='").append(com.sforce.ws.util.Verbose.toString(required)).append("'\n");
      sb.append("]\n");
      return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private <T,U> T[] castArray(Class<T> clazz, U[] array) {
        if (array == null) {
            return null;
        }
        T[] retVal = (T[]) java.lang.reflect.Array.newInstance(clazz, array.length);
        for (int i=0; i < array.length; i++) {
            retVal[i] = (T)array[i];
        }

        return retVal;
	}
}
