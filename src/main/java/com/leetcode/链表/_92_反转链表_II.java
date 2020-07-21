package com.leetcode.链表;

import com.alibaba.fastjson.JSON;

import java.util.Stack;

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
        _92_反转链表_II v = new _92_反转链表_II();
        ListNode reverseListNode = v.reverseBetween(head, 2, 4);
        System.out.println(reverseListNode);
    }

    /**
     * 本地采用递归方式解决，思路将：https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/bu-bu-chai-jie-ru-he-di-gui-di-fan-zhuan-lian-biao/
     */

    private ListNode successor;

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

    private ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode newHead = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return newHead;
    }
}
