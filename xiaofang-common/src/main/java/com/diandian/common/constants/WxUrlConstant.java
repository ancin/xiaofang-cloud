package com.diandian.common.constants;

/**
 * @author shengxiaohua
 * @Description:
 * @create 2019-11-23 18:00
 * @last modify by [shengxiaohua 2019-11-23 18:00]
 **/
public class WxUrlConstant {

    /**
     * 获取access_token
     */
    public static final String URL_GET_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?";

    /**
     * 创建菜单链接-默认菜单
     */
    public static final String URL_MENU_DEFAULT = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";

    /**
     * 创建菜单链接-权限菜单
     */
    public static final String URL_MENU_CONDITION = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=";

    /**
     * 查询菜单
     */
    public static final String URL_MENU_QUERY = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";

    /**
     * 删除菜单
     */
    public static final String URL_MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=";

    /**
     * 群发消息接口链接
     */
    public static final String URL_MSG_ALL="";

    /**
     * 模板消息接口链接
     */
    public static final String URL_MSG_MODEL="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    /**
     * 48小时客服消息接口链接
     */
    public static final String URL_MSG_AGENT="";

    /**
     * 第三方浏览器扫码登录链接
     */
    public static final String URL_THIRD_LOGIN = "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect";

    /**
     * 用户授权获取openId和token
     */
    public static final String URL_OAUTH2_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    /**
     * 获取用户信息
     */
    public static final String URL_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
}
