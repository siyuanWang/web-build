package com.wry.dongman.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;

public class CommonUtil {

    private CommonUtil() {
        super();
    }

    private final Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);

    public static String string2MD5(final String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (final Exception e) {
            return "";
        }
        final char[] charArray = inStr.toCharArray();
        final byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        final byte[] md5Bytes = md5.digest(byteArray);
        final StringBuffer hexValue = new StringBuffer();
        for (final byte md5Byte : md5Bytes) {
            final int val = (md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }


    public static String getCurDate(final String format) {
        final DateTime d = new DateTime();
        return d.toString(format);
    }

    public static String successResponse() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("msg", "success");
        return jsonObject.toJSONString();
    }

    public static String failResponse(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 400);
        jsonObject.put("msg", msg);
        return jsonObject.toJSONString();
    }


    public static boolean isRoot(HttpServletRequest request) {
        int userType = (int) request.getSession().getAttribute("userType");
        return userType == Constance.Root_User;
    }
    public static long getUserId(HttpServletRequest request) {
        return (long) request.getSession().getAttribute("userId");
    }

    public static String getLoginName(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("loginName");
    }

    public static void setUserId(HttpServletRequest request, long userId, String loginName, int type) {
        request.getSession().setAttribute("userId", userId);
        request.getSession().setAttribute("loginName", loginName);
        request.getSession().setAttribute("userType", type);
    }

    public static void logout(HttpServletRequest request) {
        request.getSession().removeAttribute("userId");
    }

}
