package biz.remu.libs.java.tumblr.serializers;


import biz.remu.libs.java.tumblr.models.ParseModel;
import biz.remu.libs.java.tumblr.models.v1.rmodels.Tumblelog;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonSerializer {

    private String json_string = null;

    public JsonSerializer(String json_string){
        this.json_string = json_string;
    }

    public Map<String,Object> serialize(ParseModel model){
        /*
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap< String,Object> >() {};
        HashMap<String,Object> o = null;
        try {
            o = mapper.readValue(this.json_string, typeRef);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        model.parse(o);*/

        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jsonParser = null;
        Map<String,Object> obj = null;
        try {
            jsonParser = jsonFactory.createJsonParser(this.json_string);
            obj = (Map<String, Object>) model.parse(jsonParser);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return obj;
    }

}
