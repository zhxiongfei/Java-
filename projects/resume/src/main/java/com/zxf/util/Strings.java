package com.zxf.util;

public class Strings {

    /**
     * 驼峰转下划线
     * @param string 可能是小驼峰 大驼峰 myAge MyAge
     * @return my_age
     */
    public static String underlineClass(String string){
        if (string == null || string.length() == 0) return string;

        int len = string.length();
        StringBuilder sb = new StringBuilder();
        sb.append(Character.toLowerCase(string.charAt(0)));
        for (int i = 1; i < len; i ++){
            char c = string.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
