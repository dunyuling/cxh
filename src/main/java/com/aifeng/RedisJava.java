package com.aifeng;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by pro on 17-6-20.
 */
public class RedisJava {
    public static void main(String[] args) {
        set();
    }

    private static void set() {
        Jedis jedis = new Jedis("localhost", 6379);
        System.out.println("Connection to server successfully");
        System.out.println("Server is running: " + jedis.set("age","27"));
    }

    private static void keys() {
        Jedis jedis = new Jedis("localhost");
        System.out.println("Connection to server sucessfully");
        List<String> list = (List<String>) jedis.keys("*");

        System.out.println("---");

//        for(int i = 0; i<list.size(); i++) {
//            System.out.println();
//            System.out.println("List of stored keys:: "+list.get(i));
//        }

    }
}