package com.aifeng.util;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/*******************************************************************************
 * AES加解密算法
 * 
 * @author arix04
 * 
 */

public class AES {

	/**
     * 
     * @param s:要进行加密的字符串
     * @return  字符串的md5值
     */
    public static String getMd5(String s) {
            char hexChar[] = {'0', '1', '2', '3', '4', '5', '6', 
                            '7', '8' , '9', 'a', 'b', 'c', 'd', 'e', 'f','A', 'B', 'C', 'D', 'E', 'F'};
            //md5加密算法的加密对象为字符数组，这里是为了得到加密的对象
            byte[] b = s.getBytes(StandardCharsets.UTF_8);
            try {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    md.update(b);
                    byte[] b2 = md.digest();// 进行加密并返回字符数组
                    char str[] = new char[b2.length << 1];
                    int len = 0;
                    //将字符数组转换成十六进制串，形成最终的密文
                    for (int i = 0; i < b2.length; i++) {
                            byte val = b2[i];
                           str[len++] = hexChar[(val >>> 4) & 0xf];
                            str[len++] = hexChar[val & 0xf];
                    }
                    return new String(str);
            } catch (NoSuchAlgorithmException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            return null;
    }
    // 加密
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 32) {
            System.out.print("Key长度不是16位");
            return null;
        }
        
        String key = sKey.substring(0,16);
        String ivstr = sKey.substring(16);
//        String md5Pass = getMd5(sSrc);
        
        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivstr.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());

        return byte2hex(encrypted).toLowerCase();
    }

    // 解密
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 32) {
                System.out.print("Key长度不是16位");
                return null;
            }
            
            String key = sKey.substring(0,16);
            String ivstr = sKey.substring(16);
            
            byte[] raw = key.getBytes("ASCII");
            
            
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivstr
                    .getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = hex2byte(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static byte[] hex2byte(String strhex) {
        if (strhex == null) {
            return null;
        }
        int l = strhex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2),
                    16);
        }
        return b;
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }
    
    
    public static String sKey = "637c3da60e43a39e9d8bd3d929e77786";  // "hzygift"  AES.getMd5
    //使用BASE64做转码。
    // 加密
    public static String Encrypt2(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 32) {
            System.out.print("Key长度不是16位");
            return null;
        }
        
        String key = sKey.substring(0,16);
        String ivstr = sKey.substring(16);
//        String md5Pass = getMd5(sSrc);
        
        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivstr.getBytes()); // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());
        return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
    }

    // 解密
    public static String Decrypt2(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 32) {
                System.out.print("Key长度不是16位");
                return null;
            }
            
            String key = sKey.substring(0,16);
            String ivstr = sKey.substring(16);
            
            byte[] raw = key.getBytes("ASCII");
            
            
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivstr.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
    	
        /*
         * 加密用的Key 可以用26个字母和数字组成，最好不要用保留字符，虽然不会错，至于怎么裁决，个人看情况而定
         */
        String cKey = "1234567890123456";
        // 需要加密的字串
        String cSrc = "123456";
        String md5Pass = getMd5(cSrc);
        System.out.println(md5Pass);
        
        System.out.println(cSrc);
        // 加密
        long lStart = System.currentTimeMillis();
        String enString = AES.Encrypt(cSrc, md5Pass);
        
        System.out.println("加密后的字串是：" + enString);

        long lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("加密耗时：" + lUseTime + "毫秒");
        // 解密
        lStart = System.currentTimeMillis();
        String DeString = AES.Decrypt(enString, md5Pass);
        System.out.println("解密后的字串是：" + DeString);
        lUseTime = System.currentTimeMillis() - lStart;
        System.out.println("解密耗时：" + lUseTime + "毫秒");
        

		KeyGenerator kg = KeyGenerator.getInstance("AES");
		
		
		kg.init(128);//要生成多少位，只需要修改这里即可128, 192或256
		
		
		SecretKey sk = kg.generateKey();
		byte[] b = sk.getEncoded();
		
		
		System.out.println(AES.Encrypt2("123456", sKey));
		
		System.out.println(AES.Decrypt2("8y96IcZd1IeYJmbmD+zCKg==", sKey));
		
		System.out.println(AES.Encrypt("123456", sKey));
		System.out.println(AES.Decrypt("52828190562d6c4b362cac77b2b70a45", sKey));
		
    }
}