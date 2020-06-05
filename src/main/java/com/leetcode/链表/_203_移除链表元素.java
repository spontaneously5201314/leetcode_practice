package com.leetcode.链表;

import com.alibaba.fastjson.JSON;

/**
 * 删除链表中等于给定值 val 的所有节点。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-linked-list-elements/
 *
 * @author 洪飞
 * @date 2020/6/3
 */
public class _203_移除链表元素 {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(1);
        System.out.println(JSON.toJSONString(removeElements(listNode, 1)));
    }

    public static ListNode removeElements(ListNode head, int val) {
//        if (head == null) {
//            return null;
//        }
//        if (head.val == val) {
//            head = removeElements(head.next, val);
//        } else {
//            ListNode newHead = head;
//            while (newHead.next != null) {
//                if (newHead.next.val == val) {
//                    newHead.next = newHead.next.next;
//                } else {
//                    newHead = newHead.next;
//                }
//            }
//        }
//        return head;

        //解法二：使用哨兵节点，使得连表永远有头
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode prev = sentinel, curr = head;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        return sentinel.next;
    }
}
