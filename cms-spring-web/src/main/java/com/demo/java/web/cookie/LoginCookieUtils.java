package com.demo.java.web.cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.user.entity.User;
import com.demo.java.utils.crypto.Base64Utils;
import com.demo.java.utils.string.StringUtils;

public class LoginCookieUtils extends CookieUtils {

    static final Logger LOG = LoggerFactory.getLogger(LoginCookieUtils.class);

    /**
     * 登陆成功写Cookie.<br/>
     * 
     * @author zhanghanlin
     * @param user
     * @param request
     * @param response
     * @since JDK 1.7
     */
    public static void setLoginCookie(User user, HttpServletRequest request, HttpServletResponse response) {
        String baseCode = Base64Utils.encode(user.getUserName() + "&" + user.getId().toString());
        addCookie(request, response, CookieConstants.CMS_LOGIN, baseCode, CookieConstants.MAX_AGE);
        addCookie(request, response, CookieConstants.CMS_USER_ID, user.getId().toString(), CookieConstants.MAX_AGE);
    }

    /**
     * 
     * 清理登陆Cookie.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @param response
     * @since JDK 1.7
     */
    public static void clearLoginCookie(HttpServletRequest request, HttpServletResponse response) {
        deleteCookie(request, response, CookieConstants.CMS_LOGIN);
        deleteCookie(request, response, CookieConstants.CMS_USER_ID);
    }

    /**
     * 验证登陆Cookie.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @param response
     * @return
     * @since JDK 1.7
     */
    public static boolean validLoginCookie(HttpServletRequest request, HttpServletResponse response) {
        String cookie = getCookie(request, CookieConstants.CMS_LOGIN);
        String userId = getCookie(request, CookieConstants.CMS_USER_ID);
        if (StringUtils.isNotBlank(cookie) && StringUtils.isNotBlank(userId)) {
            String code = Base64Utils.decode(cookie);
            String cookieUid = code.split("&")[1];
            if (cookieUid.equals(userId)) {
                return true;
            }
        }
        return false;
    }
}
