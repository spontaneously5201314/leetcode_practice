package com.leetcode.链表;

import java.util.Stack;

/**
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 进阶：
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * <p>
 * 示例：
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
 *
 * @author 洪飞
 * @date 2020/6/3
 */
public class _445_两数相加_II {

    public static void main(String[] args) {
//        ListNode l1 = new ListNode(7);
//        l1.next = new ListNode(2);
//        l1.next.next = new ListNode(4);
//        l1.next.next.next = new ListNode(3);
//
//        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);
//

        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(5);
        _445_两数相加_II ii = new _445_两数相加_II();
        System.out.println(ii.addTwoNumbers(l1, l2));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        ListNode head = null;
        boolean addOne = false;
        while (!s1.isEmpty() || !s2.isEmpty() || addOne) {
            Integer n1 = s1.isEmpty() ? 0 : s1.pop();
            Integer n2 = s2.isEmpty() ? 0 : s2.pop();
            int sum = (n1 == null ? 0 : n1) + (n2 == null ? 0 : n2) + (addOne ? 1 : 0);
            ListNode node = new ListNode(sum % 10);
            node.next = head;
            head = node;
            addOne = sum >= 10;
        }
        return head;
    }
}
