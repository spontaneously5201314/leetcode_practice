package com.leetcode.链表;

/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 *
 * @author 洪飞
 * @date 2020/6/16
 */
public class _148_排序链表 {

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        _148_排序链表 v = new _148_排序链表();
        System.out.println(v.sortList(head));
    }

    public ListNode sortList(ListNode head) {
        // TODO: 2020/6/16
        return null;
    }
}
