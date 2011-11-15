package biz.remu.libs.java.tumblr;

import biz.remu.libs.java.tumblr.auth.BaseAuth;

public class APIv2Handler{

    private BaseAuth auth = null;
    private Integer retry_count = null;
    private Float retry_delay = null;

    APIv2Handler(BaseAuth auth_handler,Integer retry_count, Float retry_delay){
        this.auth = auth_handler;
        this.retry_count = retry_count;
        this.retry_delay = retry_delay;
    }
}
