import biz.remu.libs.java.tumblr.serializers.JsonSerializer;

import java.io.BufferedReader;
import java.io.FileReader;

public class test {

    public static void main(String[] args) throws Exception {
        String consumerkey = "ZdjpOx2ruquaoOYg21co5jBdwa4qT9avdLcK4sO8EDkED7k2Rt";
        String consumersecret = "D2Z6uj36SGceKmnJDnT5uJ37S386EeAFZtHA01VGnnhCEfXpMI";
        String callbackurl = "cb://tumblrandroid";
        String accessKey = "knwVY59Flk3cdc6e3L9CUxI3KIga4ZbKIgkihdd4N2CI7lp30p";
        String accessSecret = "WrdpPndauc3yDY84JTHUGUxlR9ooAl64HXecYtLPkmA0XPGyxQ";
        String email = "";
        String password = "";
        String host = "staff.tumblr.com";

        /*
        BaseAuth oauth = new OAuthHandler(consumerkey,consumersecret);
        if (accessKey == null || accessSecret==null){
            try {
                String url = oauth.getAuthorizationURL(callbackurl);
                System.out.println(url);
                System.out.println(oauth.getRequestTokens().tokenKey);
                System.out.println(oauth.getRequestTokens().tokenSecret);

            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }else {
            // OAuthテスト
            BaseAuth auth = new OAuthHandler(consumerkey,consumersecret);
            auth.setTokens(accessKey,accessSecret);

            // xAuthテスト(未実装)
            //BaseAuth xauth = new XAuthHandler(consumerkey,consumersecret);
            //xauth.setEmailPassword(email,password);

            // APIv1テスト
            APIv1Handler apiv1 = new APIv1Handler(auth,3,2000L);
            APIv1Properties config = new APIv1Properties();
            apiv1.getUserPosts(host,config);

            // APIv2テスト
            //APIv2Handler apiv2 = new APIv2Handler();
        }*/
        try{
            FileReader f = new FileReader("/home/renax/IdeaProjects/tumblr4java/src/main/java/testUserPost.json");
            BufferedReader b = new BufferedReader(f);
            String json_string = b.readLine();
            JsonSerializer json = new JsonSerializer(json_string);
            json.serialize();

        }catch(Exception e){
            System.out.println("ファイル読み込み失敗");
        }


    }

}
