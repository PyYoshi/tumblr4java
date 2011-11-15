package biz.remu.libs.java.tumblr.serializers;


import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.HashMap;

public class JsonSerializer {

    private String json_string = null;

    public JsonSerializer(String json_string){
        this.json_string = json_string;
    }

    public void serialize(){
        System.out.println("Json serialize");
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap< String,Object> >() {};
        HashMap<String,Object> o = null;
        try {
            o = mapper.readValue(this.json_string, typeRef);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        System.out.println(o.get("posts"));

    }

}
