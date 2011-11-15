package biz.remu.libs.java.tumblr.auth;

import java.net.HttpURLConnection;


public class BasicAuth{

    private String auth_type = "BASIC";

    private String login_uri = "http://www.tumblr.com/api/authenticate";

    private String username;
    private String passwd;

    BasicAuth(String email, String password){
        this.username = email;
        this.passwd = password;
    }

    /**
     * リクエストヘッダにauth用ヘッダを付加して返す
     * @param request;
     *
     */
    public final HttpURLConnection sign(HttpURLConnection request){
        // authヘッダの付加
        String basic = this.username + ":" + this.passwd;
        basic = new String(basic.getBytes());
        request.setRequestProperty(
                "Authorization",
                "Basic "+basic
        );

        return request;
    }

    public final void getUserInfo(){

    }
}
