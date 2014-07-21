/*
 * Copyright 1999-2004 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 类Swap.java的实现描述：TODO 类实现描述
 * 
 * @author wb_zhineng.zhaozn 2014-2-8 上午11:44:41
 */
public class Swap {

    public static void swap(String a, String b) {
        String temp = b;
        b = a;
        a = temp;
        System.out.println(a);
        System.out.println(b);
    }

    public static void swapObj(Object a, Object b) {
        // Object temp = b;
        // b = a;
        // a = temp;
        a = new Object();
        b = new Object();
        System.out.println(a);
        System.out.println(b);
    }
    
    public static void add(List<String> list){
        list.add("cc");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // String a = "aa";
        // String b = "bb";
        // swap(a, b);

        Object a = new Object();
        Object b = new Object();

        System.out.println(a);
        System.out.println(b);

        swapObj(a, b);

        System.out.println(a);
        System.out.println(b);
        
        List<String> list = new ArrayList<String>();
        list.add("aa");
        System.out.println(list.toArray(new String[5]));
        add(list);
        System.out.println(list.toArray(new String[5]));
    }

}
