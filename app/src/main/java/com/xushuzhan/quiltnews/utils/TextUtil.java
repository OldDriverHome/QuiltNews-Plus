package com.xushuzhan.quiltnews.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xushuzhan on 2016/8/22.
 */
public class TextUtil {
    //验证邮箱格式
    public static boolean isEmail(String email){
        if (null==email || "".equals(email)) return false;
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
