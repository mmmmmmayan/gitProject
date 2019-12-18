package com.my.myrealm;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5 {

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123", "abc", 1024);
        System.out.println(md5Hash);    //894b3913a4a13b25dc6186d11835c209
        CredentialsMatcher credentialsMatcher;
    }
}
