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

    public static void loginSuccess(User user, HttpServletRequest request, HttpServletResponse response) {
        String baseCode = Base64Utils.encode(user.getUserName() + "&" + user.getId().toString());
        addCookie(request, response, CookieConstants.CMS_LOGIN, baseCode, CookieConstants.MAX_AGE);
        addCookie(request, response, CookieConstants.CMS_USER_ID, user.getId().toString(), CookieConstants.MAX_AGE);
    }

    public static boolean validLogin(HttpServletRequest request, HttpServletResponse response) {
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
