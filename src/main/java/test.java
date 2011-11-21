import biz.remu.libs.java.tumblr.models.ParseModel;
import biz.remu.libs.java.tumblr.models.v1.Postsv1;
import biz.remu.libs.java.tumblr.models.v1.rmodels.Tumblelog;
import biz.remu.libs.java.tumblr.serializers.JsonSerializer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

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
            FileReader f = new FileReader("E:\\MySourcecode\\workspace\\tumblr4java\\src\\main\\java\\testUserPost.json");
            BufferedReader b = new BufferedReader(f);
            String json_string = b.readLine();
            JsonSerializer json = new JsonSerializer(json_string);
            ParseModel parseModel = new Postsv1();

            Map<String,Object> obj = json.serialize(parseModel);
            Tumblelog tumblelog = (Tumblelog) obj.get("tumblelog");
            Integer posts_start = (Integer) obj.get("posts-start");
            Integer posts_total = (Integer) obj.get("posts-total");
            Boolean posts_type = (Boolean) obj.get("posts-type");

            obj = null;// GC

            System.out.println("============================");
            System.out.println(tumblelog);
            System.out.println(posts_start);
            System.out.println(posts_total);
            System.out.println(posts_type);
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();

        }catch(Exception e){
            System.out.println("ファイル読み込み失敗");
            e.printStackTrace();
        }


    }

}
