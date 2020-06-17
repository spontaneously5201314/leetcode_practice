package com.leetcode.链表;

import com.alibaba.fastjson.JSON;

import java.util.Stack;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * 示例:
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 *
 * @author 洪飞
 * @date 2020/6/2
 */
public class _92_反转链表_II {

    public static void main(String[] args) {
//        ListNode head = new ListNode(1);
//        ListNode listNode = head;
//        int i = 2;
//        while (i < 6) {
//            listNode.next = new ListNode(i);
//            listNode = listNode.next;
//            i++;
//        }
//        ListNode reverseListNode = reverseBetween(head, 2, 4);
//        System.out.println(reverseListNode);
        ListNode listNode = new ListNode(3);
        listNode.next = new ListNode(5);
        System.out.println(reverseBetween(listNode, 1, 2));

        // TODO: 2020/6/16 尚未解决
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n) return head;
        Stack<Integer> stack = new Stack<>();
        ListNode pre = new ListNode(-1);
        pre.next = head;
        int index = 0;
        ListNode prefix = pre;
        ListNode suffix = null;
        while (pre != null) {
            if (index <= n && index >= m) {
                stack.push(pre.val);
            }
            if (index < m) {
                prefix = pre;
            }
            pre = pre.next;
            if (index == n) {
                suffix = pre;
            }
            index++;
            if (index > n) {
                break;
            }
        }
        while (!stack.isEmpty()) {
            ListNode pop = new ListNode(stack.pop());
            prefix.next = pop;
            prefix = pop;
        }
        prefix.next = suffix;
        return head;
    }

}
