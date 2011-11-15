package biz.remu.libs.java.tumblr.exceptions;


public class APICommunicationException extends APIException{

    private String responseBody = null;

    public APICommunicationException(String message){
        super(message);
    }

    public APICommunicationException(String message,Exception cause){
        super(message,cause);
    }

    public APICommunicationException(Exception cause) {
        super(
                "Communication with the service provider failed: "
                + cause.getLocalizedMessage(), cause
        );
    }

    public APICommunicationException(String message, String responseBody) {
        super(message);
        this.responseBody = responseBody;
    }

    public String getResponseBody(){
        return this.responseBody;
    }
}
