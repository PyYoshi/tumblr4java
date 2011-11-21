package biz.remu.libs.java.tumblr.models.v1.rmodels;

import java.util.TimeZone;

public class Tumblelog {
    public String title = null;
    public String description = null;
    public String name = null;
    public TimeZone timezone = null;
    public Boolean cname = null;
    //String[] feeds = null;

    public void setVal(String key, String val){
        if(key == "title"){
            this.title = val;
        }else if(key == "description"){
            this.description = val;
        }else if(key == "name"){
            this.name = val;
        }else if(key == "timezone"){
            this.timezone = TimeZone.getTimeZone(val);
        }else if(key == "cname"){
            this.cname = Boolean.valueOf(val);
        }
    }

    public String toString(){
        String result="title: " + this.title + "\n"
               + "description: " + this.description + "\n"
               + "name: " + this.name + "\n"
               + "timezone: " + this.timezone + "\n"
               + "cname: " + this.cname;
        return result;
    }
}
