package biz.remu.libs.java.tumblr.models.v1;

import biz.remu.libs.java.tumblr.models.ParseModel;
import biz.remu.libs.java.tumblr.models.v1.rmodels.Tumblelog;
import biz.remu.libs.java.tumblr.models.v1.rmodels.Posts;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Postsv1 implements ParseModel{

    public Map<String,Object> parse(JsonParser jsonParser) {
        String parentToken = null;
        String elementName = null;
        Map<String,Object> result = new HashMap<String,Object>();
        Tumblelog tumblelog = new Tumblelog();
        List posts = new ArrayList();
        try {
            while (jsonParser.nextToken() != JsonToken.END_ARRAY){
                if(jsonParser.getCurrentToken() == JsonToken.START_OBJECT){
                      while (jsonParser.nextToken() != JsonToken.END_OBJECT){
                          String name = jsonParser.getCurrentName();
                          jsonParser.nextToken();
                          /*
                          jsonParser.nextToken();
                          if("tumblelog".equals(name)){
                              parentToken = name;
                          }else if("posts-start".equals(name)){
                              parentToken = name;
                              result.put(name,Integer.valueOf(jsonParser.getText()));
                          }else if("posts-total".equals(name)){
                              parentToken = name;
                              result.put(name,Integer.valueOf(jsonParser.getText()));
                          }else if("posts-type".equals(name)){
                              parentToken = name;
                              result.put(name,Boolean.valueOf(jsonParser.getText()));
                          }else if("posts".equals(name)){
                              parentToken = name;
                          }

                          System.out.println("========"+parentToken+"========");
                          //System.out.println(name);

                          if(parentToken == "tumblelog"){
                              tumblelog.setVal(name,jsonParser.getText());
                          }else if(parentToken == "posts"){
                           System.out.println("========"+name+"========");
                          }
                          */
                          if("tumblelog".equals(name)){
                              jsonParser.nextToken();
                              while (jsonParser.getText() != "}"){
                                  jsonParser.nextToken();
                                  elementName = jsonParser.getCurrentName();
                                  if(jsonParser.getText() != "{" || jsonParser.getText() != "}" ||
                                          jsonParser.getText() != "[" || jsonParser.getText() != "]"){

                                      tumblelog.setVal(elementName,jsonParser.getText());

                                  }
                              }
                          }else if("posts-start".equals(name)){
                              result.put(name,Integer.valueOf(jsonParser.getText()));

                          }else if("posts-total".equals(name)){
                              result.put(name,Integer.valueOf(jsonParser.getText()));
                          }else if("posts-type".equals(name)){
                              result.put(name,Boolean.valueOf(jsonParser.getText()));
                          }else if("posts".equals(name)){
                              System.out.println("posts");
                              //jsonParser.nextToken();// [
                              System.out.println(jsonParser.getText());// {
                              // TODO: "{", "}"の中身を配列にプッシュしposts.parseをしていく
                              /*
    [

        {
            "id": "12740654763",
            "url": "http://staff.tumblr.com/post/12740654763",
            "url-with-slug": "http://staff.tumblr.com/post/12740654763/ny-times-sunday-review-cover-story-on-the",
            "type": "photo",
            "date-gmt": "2011-11-13 16:00:56 GMT",
            "date": "Sun, 13 Nov 2011 11:00:56",
            "bookmarklet": 0,
            "mobile": 0,
            "feed-item": "",
            "from-feed-id": 0,
            "unix-timestamp": 1321200056,
            "format": "html",
            "reblog-key": "S9sMrgoC",
            "slug": "ny-times-sunday-review-cover-story-on-the",
            "photo-caption": "<p>NY Times Sunday Review cover story on the <a href=\"http://www.nytimes.com/2011/11/13/opinion/sunday/the-entrepreneurial-generation.html?ref=todayspaper\">entrepreneurial generation</a>, Tumblr upper right</p>",
            "photo-link-url": "http://www.nytimes.com/2011/11/13/opinion/sunday/the-entrepreneurial-generation.html?ref=todayspaper",
            "width": "826",
            "height": "693",
            "photo-url-1280": "http://www.tumblr.com/photo/1280/12740654763/1/tumblr_lulqgzeqLN1qz7unw",
            "photo-url-500": "http://27.media.tumblr.com/tumblr_lulqgzeqLN1qz7unwo1_r1_500.png",
            "photo-url-400": "http://24.media.tumblr.com/tumblr_lulqgzeqLN1qz7unwo1_r1_400.png",
            "photo-url-250": "http://25.media.tumblr.com/tumblr_lulqgzeqLN1qz7unwo1_r1_250.png",
            "photo-url-100": "http://25.media.tumblr.com/tumblr_lulqgzeqLN1qz7unwo1_r1_100.png",
            "photo-url-75": "http://24.media.tumblr.com/tumblr_lulqgzeqLN1qz7unwo1_r1_75sq.png",
            "photos": []
        }
    ]
                              */

                          }


                      }
                }
            }
            System.out.println();
            result.put("tumblelog",tumblelog);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return result;
    }

    public String toString(){

        return "";
    }
}
