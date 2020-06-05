package com.leetcode.链表;

import com.alibaba.fastjson.JSON;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 *
 * @author 洪飞
 * @date 2020/6/3
 */
public class _19_删除链表的倒数第N个节点 {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        System.out.println(removeNthFromEnd(listNode, 2));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode sentinelNode = new ListNode(0);
        sentinelNode.next = head;
        ListNode first = sentinelNode;
        for (int i = 0; i < n; i++) {
            if (first.next == null) {
                return null;
            }
            first = first.next;
        }
        ListNode last = sentinelNode;
        while (first.next != null) {
            first = first.next;
            last = last.next;
        }
        last.next = last.next.next;
        return sentinelNode.next;
    }
}
