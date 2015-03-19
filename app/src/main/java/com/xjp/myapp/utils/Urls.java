package com.xjp.myapp.utils;

/**
 * Description: 网络请求Url
 * User: xjp
 * Date: 2015/3/10
 * Time: 16:20
 */
public class Urls {

    //app key
    private final static String APPKEY = "6496615db6172cd25da20f9c3880ab61";
    //基础URL
    public final static String BASE_URL = "http://apis.juhe.cn/cook";
    //所有菜谱分类
    public final static String ALL_CATEGORY = "/category?key=" + APPKEY;
    //指定菜谱分类
    public final static String CATEGORY = "/category?key=" + APPKEY + "&parentid=";
    //具体菜谱分类的详细信息
    public final static String INDEX = "/index?key=" + APPKEY + "&cid=";
    //根据菜目id 查找对应的菜谱详细信息
    public final static String QUERY_ID = "/queryid?key=" + APPKEY + "&id=";
    //根据菜名模糊查找对应的菜谱详细信息
    public final static String QUERY_NAME = "/query.php?key=" + APPKEY + "&menu=";
}
