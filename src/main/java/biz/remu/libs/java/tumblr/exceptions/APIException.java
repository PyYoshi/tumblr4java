package biz.remu.libs.java.tumblr.exceptions;


@SuppressWarnings("serial")
public class APIException extends Exception{
    public APIException(String message){
        super(message);
    }

    public APIException(Throwable cause){
        super(cause);
    }

    public APIException(String message,Throwable cause){
        super(message,cause);
    }
}
