package com.yajun.longyj.common.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class ShiroKit {

    public static String md5(String originPassword,String salt){
        return new Md5Hash(originPassword,salt).toString();
    }
    
    public static String md5Pwd(String password, String salt) {  
        String md5Pwd = new Md5Hash(password, salt).toHex();  
        return md5Pwd;  
    }  

    public static void main(String[] args) {
        System.out.println(md5("123456","admin"));
        System.out.println(md5("123456","dev"));
        System.out.println(md5("123456","test"));
        System.out.println(md5Pwd("123456","doc"));
    }
}
