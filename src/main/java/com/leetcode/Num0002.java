package com.leetcode;

import lombok.val;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 *
 * @author 洪飞
 * @date 2020/5/24
 */
public class Num0002 {

    public static void main(String[] args) {
//        ListNode one = new ListNode(2);
//        one.next = new ListNode(4);
//        one.next.next = new ListNode(3);
//
//        ListNode two = new ListNode(5);
//        two.next = new ListNode(6);
//        two.next.next = new ListNode(4);

//        ListNode one = new ListNode(5);
//        ListNode two = new ListNode(5);

        ListNode one = new ListNode(1);
        one.next = new ListNode(8);
        ListNode two = new ListNode(0);

        System.out.println(addTwoNumbers(one, two));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode first = new ListNode(0);
        return add(l1, l2, first);
    }

    public static ListNode add(ListNode l1, ListNode l2, ListNode carry) {
        int temp = 0;
        if (l1 != null) {
            temp += l1.val;
        }
        if (l2 != null) {
            temp += l2.val;
        }
        if (carry == null) {
            return null;
        }
        temp += carry.val;
        carry.val = temp % 10;
        l1 = l1 == null || l1.next == null ? null : l1.next;
        l2 = l2 == null || l2.next == null ? null : l2.next;
        carry.next = add(l1, l2, l1 == null && l2 == null && temp < 10 ? null : new ListNode(temp / 10));
        return carry;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
