package biz.remu.libs.java.tumblr.auth;

import java.net.HttpURLConnection;

public abstract class XAuth implements BaseAuth {

    public XAuth(String consumerKey, String consumerSecret){

    }

    public void setTokens(String tokenKey, String tokenSecret) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setEmailPassword(String email, String password){
        //To change body of implemented methods use File | Settings | File Templates.
    };

    public String getAuthorizationURL(String callbackURL) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public AuthTokenObject getRequestTokens() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public AuthTokenObject getAccessToken(String verifier) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public HttpURLConnection sign(HttpURLConnection request) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
