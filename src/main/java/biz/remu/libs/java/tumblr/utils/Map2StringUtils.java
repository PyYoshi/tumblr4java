package biz.remu.libs.java.tumblr.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Base here: http://www.vineetmanohar.com/2010/05/2-ways-to-convert-java-map-to-string/
 */
public class Map2StringUtils {
    /**
     *
     * @param map
     * @return
     */
    public static String map2string(Map<String,String> map){
        StringBuilder strBuilder = new StringBuilder();
        for (String key : map.keySet()) {
            if (strBuilder.length() > 0) {
                strBuilder.append("&");
            }
            String val = map.get(key);
            try {
                strBuilder.append((key != null ? URLEncoder.encode(key, "UTF-8") : ""));
                strBuilder.append("=");
                strBuilder.append(val != null ? URLEncoder.encode(val, "UTF-8") : "");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("This method requires UTF-8 encoding support", e);
            }
        }

        return strBuilder.toString();
    }

    /**
     *
     * @param qstring
     * @return
     */
    public static Map<String,String> string2map(String qstring){
        Map<String, String> map = new HashMap<String, String>();
        String[] keyValPairs = qstring.split("&");
        for (String keyValPair : keyValPairs) {
            String[] keyValue = keyValPair.split("=");
            try {
                map.put(URLDecoder.decode(keyValue[0], "UTF-8"), keyValue.length > 1 ? URLDecoder.decode(
                keyValue[1], "UTF-8") : "");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("This method requires UTF-8 encoding support", e);
            }
        }

        return map;
    }
}
