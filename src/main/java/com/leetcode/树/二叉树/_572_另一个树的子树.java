package com.leetcode.树.二叉树;

import com.leetcode.树.TreeNode;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;

/**
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 * <p>
 * 示例 1:
 * 给定的树 s:
 * <p>
 * 3
 * / \
 * 4   5
 * / \
 * 1   2
 * 给定的树 t：
 * <p>
 * 4
 * / \
 * 1   2
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 * <p>
 * 示例 2:
 * 给定的树 s：
 * <p>
 * 3
 * / \
 * 4   5
 * / \
 * 1   2
 * /
 * 0
 * 给定的树 t：
 * <p>
 * 4
 * / \
 * 1   2
 * 返回 false。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree
 */
public class _572_另一个树的子树 implements Serializable {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) return false;
        return postSerialize(s).contains(postSerialize(t));
    }

    private String postSerialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder builder = new StringBuilder();
        postSerialize(root, builder);
        return builder.toString();
    }

    private void postSerialize(TreeNode root, StringBuilder builder) {
        if (root.left != null) {
            postSerialize(root.left, builder);
        } else {
            builder.append("#!");
        }
        if (root.right != null) {
            postSerialize(root.right, builder);
        } else {
            builder.append("#!");
        }
        builder.append(root.val).append("!");
    }
}
