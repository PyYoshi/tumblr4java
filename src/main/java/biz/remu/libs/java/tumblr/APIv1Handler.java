package biz.remu.libs.java.tumblr;

import biz.remu.libs.java.tumblr.api.APIHandler;
import biz.remu.libs.java.tumblr.auth.BaseAuth;
import biz.remu.libs.java.tumblr.conf.APIv1Properties;
import biz.remu.libs.java.tumblr.exceptions.APICommunicationException;
import biz.remu.libs.java.tumblr.exceptions.AuthFailedException;
import biz.remu.libs.java.tumblr.utils.List2StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class APIv1Handler extends APIHandler {

    private BaseAuth auth = null;
    private Integer retry_count = null;
    private Double retry_delay = null;

    private String host = "www.tumblr.com";
    private String separator = ",";
    private enum api_type{
        READ,
        DASHBOARD,
        PAGES,
        WRITE
    }

    public APIv1Handler(BaseAuth auth_handler,Integer retry_count, Long retry_delay_millis){
        super(auth_handler,retry_count,retry_delay_millis);
    }

    private Map<String, String> build_read_params(APIv1Properties config){
        Map<String,String> params = new HashMap<String, String>();
        if(config.read_start > 0){
            params.put("start",String.valueOf(config.read_start));
        }

        if(config.read_num >0 && config.read_num <=20){
            params.put("num",String.valueOf(config.read_num));
        }

        if(config.read_type != null && config.read_type.length < 7){
            // ソートと重複削除
            StringBuilder strBuilder = new StringBuilder();
            Integer[] read_type = config.read_type;
            Arrays.sort(read_type);
            Integer tmp_num = null;
            Integer tmp_times = 0;
            for(Integer val: read_type){
                if(val > 0 && val <= 7){
                    if(tmp_num == null && tmp_num != val){
                        String post_type = null;
                        switch (val){
                            case 1:
                                post_type = "text";
                                break;
                            case 2:
                                post_type = "photo";
                                break;
                            case 3:
                                post_type = "quote";
                                break;
                            case 4:
                                post_type = "link";
                                break;
                            case 5:
                                post_type = "chat";
                                break;
                            case 6:
                                post_type = "audio";
                                break;
                            case 7:
                                post_type = "video";
                                break;
                            default:
                                post_type = null;
                                break;
                        }
                        if (post_type != null){
                            strBuilder.append(post_type);
                            strBuilder.append(this.separator);
                        }
                    }
                    tmp_num = val;
                }else{
                    strBuilder = null;
                    break;
                }
            }
            params.put("type",strBuilder.toString());
        }


        if(config.read_id != null){
            params.put("id",config.read_id);
        }

        if(config.read_filter == 1){
            params.put("filter","text");
        }

        if(config.read_tagged != null){
            params.put("tagged",List2StringUtils.list2string(config.read_tagged,this.separator));
            if(config.read_tagged_chrono == true){
                params.put("chrono","1");
            }
        }

        if(config.read_search != null){
            params.put("search",List2StringUtils.list2string(config.read_search,this.separator));
        }

        if(this.auth != null){
            if(config.read_state != null){
                String state = null;
                switch (config.read_state){
                    case 1:
                        state = "draft";
                        break;
                    case 2:
                        state = "queue";
                        break;
                    case 3:
                        state = "submission";
                        break;
                }
                params.put("state",state);
            }
        }

        if(config.read_dashboard_likes == true){
            params.put("likes","1");
        }

        return params;
    }

    ////////////////// Read API //////////////////

    public void getUserPosts(String user_domain, APIv1Properties config) throws InterruptedException {
        String api_path = "/api/read/json";
        Boolean required_auth = false;
        Boolean post = false;
        Boolean json = true;

        //
        String uri = this.build_uri(user_domain, api_path);
        Map<String,String> params = build_read_params(config);
        try {
            this.execute(uri,params,required_auth,post,json);
        } catch (APICommunicationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (AuthFailedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void getDashbord(APIv1Properties config){
        String api_path = "/api/dashboard/json";
        Boolean required_auth = true;
        Boolean post = true;
        Boolean json = true;

        // TODO: auth_handlerがない場合exceptionをthrow
    }

    public void getLikes(APIv1Properties config){
        String api_path = "/api/likes";
        Boolean required_auth = true;
        Boolean post = true;
        Boolean json = false;

        // TODO: auth_handlerがない場合exceptionをthrow
    }

    public void getUserPages(String user_domain, APIv1Properties config){
        String api_path = "/api/pages";
        Boolean required_auth = true;
        Boolean post = true;
        Boolean json = false;

        // TODO: auth_handlerがない場合exceptionをthrow
    }

    public void getMyAccountInfo(){
        String api_path = "/api/authenticate";
        Boolean required_auth = true;
        Boolean post = true;
        Boolean json = false;

        // TODO: auth_handlerがない場合exceptionをthrow
    }

    ////////////////// Write API //////////////////

    public void updateLike(String post_id, String reblog_key){
        String api_path = "/api/like";
        Boolean required_auth = true;
        Boolean post = true;

        // TODO: auth_handlerがない場合exceptionをthrow
    }

    public void updateUnLike(String post_id, String reblog_key){
        String api_path = "/api/unlike";
        Boolean required_auth = true;
        Boolean post = true;

        // TODO: auth_handlerがない場合exceptionをthrow

    }

    public void updateReblog(){
        String api_path = "/api/reblog";
        Boolean required_auth = true;
        Boolean post = true;

        // TODO: auth_handlerがない場合exceptionをthrow
    }

    public void updateText(){
        String api_path = "/api/write";
        Boolean required_auth = true;
        Boolean post = true;

        // TODO: auth_handlerがない場合exceptionをthrow

    }

    public void updatePhoto(){
        String api_path = "/api/write";
        Boolean required_auth = true;
        Boolean post = true;

        // TODO: auth_handlerがない場合exceptionをthrow

    }

    public void updateQuote(){
        String api_path = "/api/write";
        Boolean required_auth = true;
        Boolean post = true;

        // TODO: auth_handlerがない場合exceptionをthrow

    }

    public void updateLink(){
        String api_path = "/api/write";
        Boolean required_auth = true;
        Boolean post = true;

        // TODO: auth_handlerがない場合exceptionをthrow

    }

    public void updateChat(){
        String api_path = "/api/write";
        Boolean required_auth = true;
        Boolean post = true;

        // TODO: auth_handlerがない場合exceptionをthrow

    }

    public void updateAudio(){
        String api_path = "/api/write";
        Boolean required_auth = true;
        Boolean post = true;

        // TODO: auth_handlerがない場合exceptionをthrow

    }

    public void updateVideo(){
        String api_path = "/api/write";
        Boolean required_auth = true;
        Boolean post = true;

        // TODO: auth_handlerがない場合exceptionをthrow

    }

    public void deletePost(String post_id){
        String api_path = "/api/delete";
        Boolean required_auth = true;
        Boolean post = true;

        // TODO: auth_handlerがない場合exceptionをthrow
    }



}
