package biz.remu.libs.java.tumblr.auth;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;

import java.net.HttpURLConnection;

public interface BaseAuth {
    String consumerKey = null;
    String consumerSecret = null;
    String requestKey = null;
    String requestSecret = null;
    OAuthConsumer consumer = null;
    OAuthProvider provider = null;

    String auth_type = null;
    String RequestTokenUR = null;
    String AuthorizeURL = null;
    String AccessTokenURL = null;

    public void setTokens(String tokenKey, String tokenSecret);

    public void setEmailPassword(String email, String password);

    public String getAuthorizationURL(String callbackURL);

    public AuthTokenObject getRequestTokens();

    public AuthTokenObject getAccessToken(String verifier);

    public HttpURLConnection sign(HttpURLConnection request);
}
