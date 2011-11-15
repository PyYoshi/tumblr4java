package biz.remu.libs.java.tumblr.exceptions;

@SuppressWarnings("serial")
public class AuthException extends Exception{

    public AuthException(String message){
        super(message);
    }

    public AuthException(Throwable cause){
        super(cause);
    }

    public AuthException(String message, Throwable cause){
        super(message, cause);
    }

}
