package com.example.xingge.opensourcechina.config;

/**
 * Created by xingge on 2017/4/12.
 */

public class Urls {

    //每页返回的条数
    public static final String PAGESIZE = "20";
    //1代表新闻列表
    public static final String NEWSTYPE = "1";
    //搜索时传递的类型 software表示从软件下面搜索
    public static final String SOFTWARE = "software";
    //问答
    public static final String POST = "post";
    //博客
    public static final String BLOG = "blog";
    //资讯
    public static final String NEWS = "news";

    //服务器地址
    public static final String BASE_URL = "http://www.oschina.net/";

    //资讯
    public static final String NEWSLIST = BASE_URL + "action/api/news_list";

    public static final String PANDABANNER = "http://www.ipanda.com/kehuduan/PAGE14501749764071042/index.json";

    //搜索
    public static final String SEARCH = BASE_URL+"action/api/search_list";

    //登陆 登录
    public static final String LOGIN = BASE_URL+"action/api/login_validate";

    //最新动弹
    public static final String TWEEN = BASE_URL+"action/api/tweet_list";
    public static final String TWEEN_ADDLIKE = BASE_URL+"action/api/tweet_like";
}
