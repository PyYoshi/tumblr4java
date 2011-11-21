package biz.remu.libs.java.tumblr.api;


import biz.remu.libs.java.tumblr.auth.BaseAuth;
import biz.remu.libs.java.tumblr.exceptions.APICommunicationException;
import biz.remu.libs.java.tumblr.exceptions.AuthFailedException;
import biz.remu.libs.java.tumblr.serializers.JsonSerializer;
import biz.remu.libs.java.tumblr.utils.Map2StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class APIHandler {

    private BaseAuth auth = null;
    private Integer retry_count = null;
    private Long retry_delay = null;
    private String host = "www.tumblr.com";
    private String separator = ",";
    private enum api_type{
        READ,
        WRITE
    }

    public APIHandler(BaseAuth auth_handler,Integer retry_count, Long retry_delay_millis){
        this.auth = auth_handler;
        this.retry_count = retry_count;
        this.retry_delay = retry_delay_millis;
    }

    public String build_uri(String host, String api_path){
        if(api_path == "" || api_path == null){
            api_path = "/";
        }
        String uri = "http://" + host + api_path;
        return uri;
    }

    private HttpURLConnection do_api(String str_url, Map<String, String> params, Boolean required_auth, Boolean post) throws APICommunicationException, InterruptedException, AuthFailedException {
        // required_auth = trueの時にauth=nullの場合エラーを投げる
        if(required_auth){
            if(this.auth == null){
                throw new AuthFailedException("Required authentication.");
            }
        }
        HttpURLConnection request = null;
        try {
            URL url = new URL(str_url);
            Integer retries_performed = 0;
            while (retries_performed < this.retry_count + 1){
                try {
                    request = (HttpURLConnection)url.openConnection();
                    if(post){
                        request.setRequestMethod("POST");
                        request.setDoOutput(true);
                        OutputStreamWriter osw = new OutputStreamWriter(request.getOutputStream());
                        if(params != null){
                            osw.write(Map2StringUtils.map2string(params));
                        }else{
                            osw.write("");
                        }
                        osw.flush();
                        osw.close();
                    }else{
                        request.setRequestMethod("GET");
                        request.setDoOutput(false);
                    }

                    // auth handlerに渡す
                    if (this.auth != null){
                        HttpURLConnection done_request = auth.sign(request);
                        request = done_request;
                    }

                    // レスポンスコードをみて繰り返し処理を行う
                    Integer code = request.getResponseCode();
                    System.out.println(code);
                    if(code == 200){
                        break;
                    }else{
                        retries_performed +=1;
                        Thread.sleep(this.retry_delay);
                    }

                } catch (IOException e) {
                    throw new APICommunicationException(e);
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return request;
    }

    public void execute(String url, Map<String,String> params, Boolean required_auth, Boolean post, Boolean json) throws APICommunicationException, InterruptedException, AuthFailedException {
        HttpURLConnection req = this.do_api(url, params, required_auth, post);
        String res_content = null;
        try {
            StringBuilder strBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
            while(true){
                String line = reader.readLine();
                if(line == null){
                    break;
                }
                strBuilder.append(line);
            }
            res_content = strBuilder.toString();
            if(json){

                Pattern p = Pattern.compile("^var tumblr_api_read = (.*);\\s*$");
                Matcher m = p.matcher(res_content);
                if (m.find()){
                    res_content = m.group(1);
                }
                // res_contentをシリアライズする
                JsonSerializer json_s = new JsonSerializer(res_content);
                //json_s.serialize();

            }else{
                // xml serializerに渡す

            }

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        // テスト
        System.out.println(url);
        System.out.println(res_content);



    }
}
