package com.leetcode.链表;

/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * <p>
 * 你应当保留两个分区中每个节点的初始相对位置。
 * <p>
 * 示例:
 * <p>
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-list
 */
public class _86_分隔链表 {

    /**
     * 使用两个子链表，然后拼接起来
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;

        ListNode lHead = new ListNode();
        ListNode lTail = lHead;

        ListNode rHead = new ListNode();
        ListNode rTail = rHead;

        while (head != null) {
            if (head.val < x) {
                lTail = lTail.next = head;
            } else {
                rTail = rTail.next = head;
            }
            head = head.next;
            //下面两句很重要，防止出现循环
            lTail.next = null;
            rTail.next = null;
        }

        lTail.next = rHead.next;
        return lHead.next;
    }
}
