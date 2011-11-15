package biz.remu.libs.java.tumblr.conf;

public class APIv1Properties {

    /**
     *
     */
    public enum post_type{
        ALL,
        Text,// text
        Photo,// photo
        Quote,// quote
        Link,// link
        Chat,// chat
        Audio,// audio
        Video,// video
    }

    /**
     *
     */
    public enum filter{
        NONE,// none
        TEXT,// text
    }

    /**
     *
     */
    public enum state{
        NONE,
        DRAFT,// draft
        QUEUE,// queue
        SUBMISSION// submission
    }



    /**
     *
     */
    public Integer read_start = 0;

    /**
     *
     */
    public Integer read_num = 20;

    /**
     *
     */
    public Integer[] read_type = null;

    /**
     *
     */
    public String read_id = null;

    /**
     *
     */
    public Integer read_filter = 0;

    /**
     *
     */
    public String[] read_tagged = null;

    /**
     *
     */
    public Boolean read_tagged_chrono = false;

    /**
     *
     */
    public String[] read_search = null;

    /**
     *
     */
    public Integer read_state = 0;

    /**
     * true: 1
     */
    public Boolean read_dashboard_likes = false;


}
