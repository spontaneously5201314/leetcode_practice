package com.leetcode.链表;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 */
public class _23_合并K个排序链表 {

    /**
     * 采用分治来做
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeKL(lists, 0, lists.length - 1);
    }

    private ListNode mergeKL(ListNode[] lists, int start, int end) {
        if (start == end) return lists[start];
        if (start > end) return null;
        int mid = (start + end) >> 1;
        ListNode left = mergeKL(lists, start, mid);
        ListNode right = mergeKL(lists, mid + 1, end);
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        if (left == null) return right;
        if (right == null) return left;

        ListNode head = new ListNode();
        ListNode tempHead = head;
        while (left != null && right != null) {
            if (left.val > right.val) {
                tempHead.next = right;
                right = right.next;
            } else {
                tempHead.next = left;
                left = left.next;
            }
            tempHead = tempHead.next;
        }
        if (left != null) tempHead.next = left;

        if (right != null) tempHead.next = right;
        return head.next;
    }

    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);

        for (ListNode list : lists) {
            if (list != null) {
                queue.add(list);
            }
        }

        ListNode head = new ListNode();
        ListNode tempHead = head;
        while (!queue.isEmpty()) {
            ListNode minNode = queue.poll();
            tempHead.next = minNode;
            tempHead = minNode;
            if (minNode.next != null) {
                queue.offer(minNode.next);
            }
        }
        return head.next;
    }
}
