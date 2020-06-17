package com.leetcode.链表;

/**
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 *
 * @author 洪飞
 * @date 2020/6/15
 */
public class _206_反转链表 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        _206_反转链表 v = new _206_反转链表();
        System.out.println(v.reverseList(head));
    }

    public ListNode reverseList(ListNode head) {
        return reverseIter(head);
    }

    private ListNode reverseRecur(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverseRecur(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    private ListNode reverseIter(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }
}
