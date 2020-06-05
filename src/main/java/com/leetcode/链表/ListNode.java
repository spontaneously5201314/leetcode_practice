package com.leetcode.链表;

/**
 * @author 洪飞
 * @date 2020/6/2
 */
public class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ListNode{val=");
        sb.append(val).append(", next=");
        if (next != null) {
            sb.append(next.toString());
        } else {
            sb.append("null");
        }
        sb.append("}");
        return sb.toString();
    }
}
