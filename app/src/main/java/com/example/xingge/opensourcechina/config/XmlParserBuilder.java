package com.example.xingge.opensourcechina.config;

import com.thoughtworks.xstream.XStream;

/**
 * Created by xingge on 2017/4/12.
 */

public class XmlParserBuilder {

    private static XmlParserBuilder xmlParserBuilder;
    private XStream xStream;
    private XmlParserBuilder(){
        xStream = new XStream();
    }
    public synchronized static XmlParserBuilder getInstance(){
        if(xmlParserBuilder == null)
            xmlParserBuilder = new XmlParserBuilder();

        return xmlParserBuilder;
    }

    public XmlParserBuilder alias(String name,Class type){
        xStream.alias(name,type);
        return this;
    }

    public Object build(String xmlData){
        return xStream.fromXML(xmlData);
    }

}
