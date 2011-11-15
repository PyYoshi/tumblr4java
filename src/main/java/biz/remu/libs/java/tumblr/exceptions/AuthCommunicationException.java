package biz.remu.libs.java.tumblr.exceptions;

public class AuthCommunicationException extends AuthException {

    private String responseBody;

    public AuthCommunicationException(Exception cause){
        super(
                "Communication with the service provider failed: "
                + cause.getLocalizedMessage(), cause
        );
    }

    public AuthCommunicationException(String message, String responseBody){
        super(message);
        this.responseBody = responseBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

}
