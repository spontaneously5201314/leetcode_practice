package com.leetcode.链表;

import com.alibaba.fastjson.JSON;

/**
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 * 示例 1：
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
 * 示例 2：
 * 输入：[1,2,3,4,5,6]
 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
 * 提示：
 * 给定链表的结点数介于 1 和 100 之间。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/middle-of-the-linked-list
 *
 * @author 洪飞
 * @date 2020/6/2
 */
public class _876_链表的中间结点 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println(JSON.toJSONString(middleNode(head)));
    }

    public static ListNode middleNode(ListNode head) {
        ListNode first = head;
        ListNode last = head;
        while (first.next != null) {
            if (first.next.next == null) {
                return last.next;
            }
            first = first.next.next;
            last = last.next;
        }
        return last;
    }
}
