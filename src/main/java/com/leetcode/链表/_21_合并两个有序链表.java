package com.leetcode.链表;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 */
public class _21_合并两个有序链表 {

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        //按照两个链表的头元素小的那个为新开始
        ListNode head = l1.val > l2.val ? l2 : l1;
        ListNode currentNode = head;
        ListNode anotherNode = l1.val > l2.val ? l1 : l2;
        while (anotherNode != null) {
            if (currentNode.next == null) {
                currentNode.next = anotherNode;
                break;
            }
            if (currentNode.next.val <= anotherNode.val) {
                currentNode = currentNode.next;
            } else {
                ListNode temp1 = currentNode.next;
                ListNode temp2 = anotherNode.next;
                currentNode.next = anotherNode;
                anotherNode.next = temp1;
                anotherNode = temp2;
                currentNode = currentNode.next;
            }
        }
        return head;
    }

    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head = new ListNode();
        ListNode tempHead = head;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                tempHead.next = l2;
                l2 = l2.next;
            } else {
                tempHead.next = l1;
                l1 = l1.next;
            }
            tempHead = tempHead.next;
        }
        if (l1 != null) tempHead.next = l1;

        if (l2 != null) tempHead.next = l2;
        return head.next;
    }
}
