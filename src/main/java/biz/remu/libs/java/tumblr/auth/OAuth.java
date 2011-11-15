package biz.remu.libs.java.tumblr.auth;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

import java.net.HttpURLConnection;

public abstract class OAuth implements BaseAuth{
    private String consumerKey;
    private String consumerSecret;
    private String requestKey;
    private String requestSecret;
    private OAuthConsumer consumer;
    private OAuthProvider provider;

    private String auth_type = "OAUTH";
    private String RequestTokenURL = "http://www.tumblr.com/oauth/request_token";
    private String AuthorizeURL = "http://www.tumblr.com/oauth/authorize";
    private String AccessTokenURL = "http://www.tumblr.com/oauth/access_token";

	public OAuth(String consumerKey, String consumerSecret){
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;

        this.consumer = new DefaultOAuthConsumer(
                this.consumerKey,
                this.consumerSecret
        );

        this.provider = new DefaultOAuthProvider(
                RequestTokenURL,
                AccessTokenURL,
                AuthorizeURL
        );
	}

    public void setTokens(String tokenKey, String tokenSecret){
        this.consumer.setTokenWithSecret(tokenKey,tokenSecret);
    }

    public void setEmailPassword(String email, String password){
        //To change body of implemented methods use File | Settings | File Templates.
    };


    public String getAuthorizationURL(String callbackURL){

        String authUrl = null;
        try {
            authUrl = this.provider.retrieveRequestToken(this.consumer, callbackURL);
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (OAuthNotAuthorizedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        this.requestKey = this.consumer.getToken();
        this.requestSecret = this.consumer.getTokenSecret();

        return authUrl;
    }

    public AuthTokenObject getRequestTokens(){
        AuthTokenObject request = new AuthTokenObject();
        request.tokenKey = this.requestKey;
        request.tokenSecret = this.requestSecret;
        return request;
    }

    public AuthTokenObject getAccessToken(String verifier){
        try {
            this.provider.retrieveAccessToken(this.consumer,verifier);
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (OAuthNotAuthorizedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        AuthTokenObject access = new AuthTokenObject();
        access.tokenKey = this.consumer.getToken();
        access.tokenSecret = this.consumer.getTokenSecret();

        return access;
    }

    public HttpURLConnection sign(HttpURLConnection request){
        try {
            this.consumer.sign(request);
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return request;
    }
}
