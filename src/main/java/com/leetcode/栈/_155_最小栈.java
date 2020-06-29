package com.leetcode.栈;

import com.leetcode.链表.ListNode;

import java.util.Stack;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * <p>
 * 提示：
 * <p>
 * pop、top 和 getMin 操作总是在 非空栈 上调用。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 */
public class _155_最小栈 {

    public static void main(String[] args) {
        //null
        MinStackWithLink minStack = new MinStackWithLink();
        //null
        minStack.push(-2);
        //null
        minStack.push(0);
        //null
        minStack.push(-3);
        //-3
//        -- > 返回 - 3.
        System.out.println(minStack.getMin());
        //null
        minStack.pop();
        //0
//        -- > 返回 0.
        System.out.println(minStack.top());
        //-2
//        -- > 返回 - 2.
        System.out.println(minStack.getMin());
    }
}

class MinStackWithLink {

    /**
     * 思路：使用链表来实现栈，栈顶元素就是链表的头结点，每次往栈中push元素就采用链表的头插法
     */

    private Node head;

    /**
     * initialize your data structure here.
     */
    public MinStackWithLink() {
        head = new Node(0, Integer.MAX_VALUE, null);
    }

    public void push(int x) {
        head = new Node(x, Math.min(x, head.min), head);
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.value;
    }

    public int getMin() {
        return head.min;
    }

    private static class Node {
        Integer value;
        Integer min;
        Node next;

        public Node(Integer value, Integer min, Node next) {
            this.value = value;
            this.min = min;
            this.next = next;
        }
    }
}


class MinStackWithTwoStack {

    /**
     * 思路：使用两个栈来实现，一个栈正常存放数据，另外一个栈是最小栈，每次push数据，就判断当前栈的最小值push到最小栈的栈顶
     */

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    /**
     * initialize your data structure here.
     */
    public MinStackWithTwoStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            minStack.push(Math.min(x, minStack.peek()));
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}