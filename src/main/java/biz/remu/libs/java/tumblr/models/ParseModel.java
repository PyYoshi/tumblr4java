package biz.remu.libs.java.tumblr.models;

import org.codehaus.jackson.JsonParser;


public interface ParseModel {
    public Object parse(JsonParser jsonParser);

}
