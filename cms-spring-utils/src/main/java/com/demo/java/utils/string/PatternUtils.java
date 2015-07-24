package com.demo.java.utils.string;

import java.util.regex.Pattern;

public class PatternUtils {

    final static String REGEX_EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

    final static String REGEX_PHONE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    public static boolean regexEmail(String str) {
        return Pattern.matches(REGEX_EMAIL, str);
    }

    public static boolean regexPhone(String str) {
        return Pattern.matches(REGEX_PHONE, str);
    }
}