package com.demo.java.web.common.jcaptcha;

import javax.servlet.http.HttpServletRequest;

import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;

public class JCaptcha {
    public static final CmsManageableImageCaptchaService captchaService = new CmsManageableImageCaptchaService(new FastHashMapCaptchaStore(), new GMailEngine(), 180, 100000, 75000);

    /**
     * 验证当前请求输入的验证码否正确.<br/>
     * 并从captchaService中删除已经生成的验证码
     * 
     * @author zhanghanlin
     * @param request
     * @param captchaResponse
     * @return
     * @since JDK 1.7
     */
    public static boolean validateResponse(HttpServletRequest request, String captchaResponse) {
        if (request.getSession(false) == null) {
            return false;
        }
        boolean validated = false;
        try {
            String id = request.getSession().getId();
            validated = captchaService.validateResponseForID(id, captchaResponse).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return validated;
    }

    /**
     * 验证当前请求输入的验证码是否正确.<br/>
     * 但不从captchaService中删除已经生成的验证码.<br/>
     * 比如Ajax验证时可以使用，防止多次生成验证码
     * 
     * @author zhanghanlin
     * @param request
     * @param captchaResponse
     * @return
     * @since JDK 1.7
     */
    public static boolean hasCaptcha(HttpServletRequest request, String captchaResponse) {
        if (request.getSession(false) == null) {
            return false;
        }
        boolean validated = false;
        try {
            String id = request.getSession().getId();
            validated = captchaService.hashCpcha(id, captchaResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return validated;
    }
}