package biz.remu.libs.java.tumblr.utils;


public class List2StringUtils {

    public static String list2string(String[] input, String separator){
        StringBuilder strBuilder = new StringBuilder();
        for(String val: input){
            strBuilder.append(val);
            strBuilder.append(separator);
        }
        return strBuilder.toString();
    }

    public static String[] string2list(String input, String separator){
        return input.split(separator);
    }

}
