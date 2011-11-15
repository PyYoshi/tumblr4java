package biz.remu.libs.java.tumblr.exceptions;

@SuppressWarnings("serial")
public class AuthFailedException extends AuthException{
    public AuthFailedException(String message){
        super(message);
    }
}
