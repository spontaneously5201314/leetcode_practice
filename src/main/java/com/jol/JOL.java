package com.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author 洪飞
 * @date 2020/6/11
 */
public class JOL {

    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        System.out.println(o.hashCode());
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
