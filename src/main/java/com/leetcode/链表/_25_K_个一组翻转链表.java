package com.leetcode.链表;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 示例：
 * <p>
 * 给你这个链表：1->2->3->4->5
 * <p>
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * <p>
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明：
 * <p>
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 *
 * @author 洪飞
 * @date 2020/6/15
 */
public class _25_K_个一组翻转链表 {

    public ListNode reverseKGroup(ListNode head, int k) {
        // TODO: 2020/6/15  
        return null;
    }

    private ListNode reverse(ListNode head, int start, int end) {
        if (start >= end) return head;
        head.next = reverse(head.next, start + 1, end);
        if (end - start == 1) {
            ListNode temp = head.next.next;
            head.next.next = head;
            head.next = temp;
        }
        return null;
    }
}
