package com.leetcode.树.二叉搜索树;

import com.leetcode.树.TreeNode;
import com.base.structure.list.List;
import com.base.structure.list.linkList.LinkedList;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 *
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 *
 *  
 *
 * 示例：
 *
 *
 *
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // 返回 3
 * iterator.next();    // 返回 7
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 9
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 15
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 20
 * iterator.hasNext(); // 返回 false
 *  
 *
 * 提示：
 *
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-search-tree-iterator
 * @author 洪飞
 * @date 2020/6/12
 */
public class _173_二叉搜索树迭代器 {

    private AtomicInteger currentIndex = new AtomicInteger(0);

    private List<TreeNode> list = new LinkedList<>();

    public _173_二叉搜索树迭代器(TreeNode root) {
        inorder(root);
    }

    private void inorder(TreeNode node){
        if (node == null) return;

        inorder(node.left);
        list.add(node);
        inorder(node.right);
    }

    /** @return the next smallest number */
    public int next() {
        return list.get(currentIndex.getAndIncrement()).val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return currentIndex.get() < list.size();
    }
}
