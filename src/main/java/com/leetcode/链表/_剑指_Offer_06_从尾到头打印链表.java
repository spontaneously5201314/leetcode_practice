package com.leetcode.链表;

import java.util.Arrays;

/**
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *  
 * <p>
 * 限制：
 * <p>
 * 0 <= 链表长度 <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof
 */
public class _剑指_Offer_06_从尾到头打印链表 {

    public static void main(String[] args) {
        _剑指_Offer_06_从尾到头打印链表 v = new _剑指_Offer_06_从尾到头打印链表();
        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next = new ListNode(2);
        System.out.println(Arrays.toString(v.reversePrint(head)));
    }

    int n = 0;
    int[] array;

    public int[] reversePrint(ListNode head) {
        reverseRecur(head);
        return array;
    }

    private void reverseRecur(ListNode node) {
        if (node == null) {
            array = new int[n];
            n = 0;
            return;
        }
        n++;
        reverseRecur(node.next);
        array[n++] = node.val;
    }
}
