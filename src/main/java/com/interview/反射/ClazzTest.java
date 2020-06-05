package com.interview.反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author 洪飞
 * @date 2020/6/3
 */
public class ClazzTest {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> aClass = Class.forName("java.util.LinkedList");
//        Method[] methods = aClass.getDeclaredMethods();
//        for (Method method : methods) {
//            System.out.println(method.toString());
//        }
//
//        Field[] fields = aClass.getDeclaredFields();
//        for (Field field : fields) {
//            System.out.println(field.toString());
//        }

        Constructor<?>[] constructors = aClass.getConstructors();
//        for (Constructor<?> constructor : constructors) {
//            System.out.println(constructor.toString());
//        }
        Constructor<?> constructor = aClass.getConstructor(Collection.class);
        List<Integer> instance = (LinkedList<Integer>) constructor.newInstance(Arrays.asList(1, 2));
        System.out.println(instance);

        Constructor<?> noParamCons = aClass.getConstructor();
//        List<Integer> instance1 = (LinkedList<Integer>) noParamCons.newInstance();
        List<Integer> instance1 = (LinkedList<Integer>) aClass.newInstance();
        Method add = aClass.getMethod("add", Object.class);
        add.invoke(instance1, 1);
        add.invoke(instance1, 2);
        add.invoke(instance1, 3);
        add.invoke(instance1, 4);
        add.invoke(instance1, 5);
        System.out.println(instance1);
    }
}
