package com.leetcode.链表;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * 示例 1:
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 *
 * @author 洪飞
 * @date 2020/6/3
 */
public class _83_删除排序链表中的重复元素 {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode first = head;
        ListNode last = head;
        while (first != null){
            if (first.val == last.val){
                first = first.next;
            }
            if (first == null){
                last.next = null;
            }else if (first.val != last.val){
                last.next = first;
                last = last.next;
            }
        }
        return head;
    }
}
