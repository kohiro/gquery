/*
 * Copyright 2011, The gwtquery team.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.query.client.builders;

import static com.google.gwt.query.client.GQuery.$;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.query.client.Properties;
import com.google.gwt.query.client.js.JsUtils;

public abstract class XmlBuilderBase<J extends XmlBuilderBase<?>> implements XmlBuilder {
  
  //TODO empty document
  protected GQuery g = $(JsUtils.parseXML("<root/>"));

  public void append(String xml) {
    g.append(JsUtils.parseXML(xml));
  }
  
  public void append(XmlBuilder x) {
    g.append(x.getRootElement());
  }
  
  protected Boolean getBooleanBase(String n) {
    return "true".equalsIgnoreCase(getStrBase(n));
  }

  protected Element getElementBase(String n) {
    return g.children(n).get(0);
  }
  
  protected Element[] getElementsBase(String n) {
    return g.children(n).elements();
  }
  
  protected float getFloatBase(String s) {
    String n = getStrBase(s).replaceAll("[^\\d\\-\\.]", "");
    return n.isEmpty() ? 0 : Float.parseFloat(n);
  }
  
  protected Properties getPropertiesBase(String n) {
    // TODO:
    return null;
  }
  
  public Element getRootElement() {
    return g.get(0);
  }
  
  protected String getStrBase(String n) {
    return g.attr(n);
  }
  
  public String getText() {
    return g.text();
  }
  
  @SuppressWarnings("unchecked")
  public J load(Object o) {
    assert o == null || o instanceof JavaScriptObject && JsUtils.isElement((JavaScriptObject)o) || o instanceof String;
    if (o != null && o instanceof String) {
        return parse((String)o);
    }
    if (o != null) {
      g=$((Element)o);
    }
    return (J)this;
  }
  
  @SuppressWarnings("unchecked")
  public J parse(String xml) {
    return load(JsUtils.parseXML(xml));
  }
  
  protected <T> void setArrayBase(String n, T[] r) {
    String v = "";
    for (T t: r) {
      v += String.valueOf(t);
    }
    setBase(n, v);
  }
  
  protected void setBase(String n, Object v) {
    g.attr(n, v);
  }
  
  public void setText(String s) {
    g.text(s);
  }
   
  public String toString() {
    return g.toString();
  }
}