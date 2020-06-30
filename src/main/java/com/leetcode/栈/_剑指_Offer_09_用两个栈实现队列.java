package com.leetcode.栈;

import java.util.Stack;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 * <p>
 * 示例 1：
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 提示：
 * <p>
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 */
public class _剑指_Offer_09_用两个栈实现队列 {
}

class CQueue {

    private Stack<Integer> inStack;
    private Stack<Integer> outStack;
    private ReentrantLock reentrantLock;

    public CQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
        reentrantLock = new ReentrantLock();
    }

    public void appendTail(int value) {
        reentrantLock.lock();
        try {
            inStack.push(value);
        } finally {
            reentrantLock.unlock();
        }
    }

    public int deleteHead() {
        if (outStack.isEmpty()) {
            reentrantLock.lock();
            try {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            } finally {
                reentrantLock.unlock();
            }
        }
        if (outStack.isEmpty()) {
            return -1;
        }
        return outStack.pop();
    }
}