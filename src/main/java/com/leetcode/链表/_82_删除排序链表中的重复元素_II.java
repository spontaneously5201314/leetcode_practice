package com.leetcode.链表;

/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 * <p>
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
 *
 * @author 洪飞
 * @date 2020/6/17
 */
public class _82_删除排序链表中的重复元素_II {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);
        _82_删除排序链表中的重复元素_II ii = new _82_删除排序链表中的重复元素_II();
        System.out.println(ii.deleteDuplicates(head));
    }

    /**
     * 利用三指针解决这个问题，思路：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/solution/chao-qing-xi-tu-jie-san-zhi-zhen-fa-by-justdo1t/
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;

        ListNode pre = new ListNode(-1);
        pre.next = head;
        head = pre;
        ListNode start = null;
        ListNode end = null;

        while (pre.next != null) {
            start = pre.next;
            end = pre.next;
            while (end.next != null && end.next.val == start.val) {
                end = end.next;
            }
            if (start == end) pre = pre.next;
            else pre.next = end.next;
        }
        return head.next;
    }
}
