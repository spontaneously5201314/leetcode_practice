package com.leetcode.链表;

import com.alibaba.fastjson.JSON;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * 示例:
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 *
 * @author 洪飞
 * @date 2020/6/2
 */
public class _92_反转链表_II {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode listNode = head;
        int i = 2;
        while (i < 6) {
            listNode.next = new ListNode(i);
            listNode = listNode.next;
            i++;
        }
        ListNode reverseListNode = reverseBetween(head, 2, 4);
        System.out.println(JSON.toJSONString(reverseListNode));
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        //非递归
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head;
        int i = 1;
        while (head.next != null) {
            if (i >= m && i <= n) {
                ListNode temp = head.next;
                head.next = head.next.next;
                newHead = head;
                head = temp;
            } else {
                head = head.next;
            }
            i++;
        }
        return newHead;
    }
}
