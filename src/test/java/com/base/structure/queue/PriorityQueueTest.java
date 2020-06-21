package com.base.structure.queue;

/**
 * @author 洪飞
 * @date 2020/6/20
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue<Person> queue = new PriorityQueue<>();
        queue.enQueue(new Person("Jack", 2));
        queue.enQueue(new Person("Rose", 10));
        queue.enQueue(new Person("Jake", 5));
        queue.enQueue(new Person("James", 15));

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }
    }

    private static class Person implements Comparable<Person> {
        private String name;
        private int boneBreak;

        public Person(String name, int boneBreak) {
            this.name = name;
            this.boneBreak = boneBreak;
        }

        @Override
        public int compareTo(Person person) {
            return this.boneBreak - person.boneBreak;
        }

        @Override
        public String toString() {
            return "Person [name=" + name + ", boneBreak=" + boneBreak + "]";
        }
    }
}