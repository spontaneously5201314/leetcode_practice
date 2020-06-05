package com.structure.dynamicArray;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 洪飞
 * @date 2020/5/28
 */
class ArrayListTest {

    private ArrayList list;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        list = new ArrayList<Integer>(4);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void size() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list.toString());
        System.out.println(list.size());

        List<Integer> arrayList = new java.util.ArrayList<>(4);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        System.out.println(arrayList.toString());
        System.out.println(arrayList.size());
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {

    }

    @org.junit.jupiter.api.Test
    void contains() {
    }

    @org.junit.jupiter.api.Test
    void add() {
    }

    @org.junit.jupiter.api.Test
    void get() {
    }

    @org.junit.jupiter.api.Test
    void set() {
    }

    @org.junit.jupiter.api.Test
    void testAdd() {
    }

    @org.junit.jupiter.api.Test
    void remove() {
    }

    @org.junit.jupiter.api.Test
    void indexOf() {
    }

    @org.junit.jupiter.api.Test
    void clear() {
    }
}