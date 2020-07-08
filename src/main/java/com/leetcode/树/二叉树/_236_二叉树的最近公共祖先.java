package com.leetcode.树.二叉树;

import com.leetcode.树.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *  
 * <p>
 * 说明:
 * <p>
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 *
 * @author 洪飞
 * @date 2020/6/16
 */
public class _236_二叉树的最近公共祖先 {

    private TreeNode ancestorNode = null;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        _236_二叉树的最近公共祖先 v = new _236_二叉树的最近公共祖先();
        TreeNode treeNode = v.lowestCommonAncestor(root, new TreeNode(5), new TreeNode(1));
//        TreeNode treeNode = v.getAncestorByLevel(root, new TreeNode(5), new TreeNode(1));
        System.out.println(treeNode.toString());
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    private Set<Integer> inOrder(TreeNode node, TreeNode p, TreeNode q, Set<Integer> result) {
        if (node == null) return result;

        inOrder(node.left, p, q, result);
        inOrder(node.right, p, q, result);
        result.add(node.val);

        if (result.contains(p.val) && result.contains(q.val) && ancestorNode == null) ancestorNode = node;
        return result;
    }

    private TreeNode getAncestorByLevel(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> levels = new ArrayList<>(10);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 1;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll == null) {
                continue;
            }
            levels.add(index, poll);
            queue.offer(poll.left);
            queue.offer(poll.right);
            index++;
        }
        if (levels.contains(p) && levels.contains(q)) {
            int pIndex = levels.indexOf(p);
            int qIndex = levels.indexOf(q);
            return levels.get(maxCommonDivisor(pIndex, qIndex));
        } else {
            return null;
        }
    }

    // 递归法求最大公约数
    public static int maxCommonDivisor(int m, int n) {
        if (m < n) {// 保证m>n,若m<n,则进行数据交换
            int temp = m;
            m = n;
            n = temp;
        }
        if (m % n == 0) {// 若余数为0,返回最大公约数
            return n;
        } else { // 否则,进行递归,把n赋给m,把余数赋给n
            return maxCommonDivisor(n, m % n);
        }
    }
}
