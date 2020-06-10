package com.leetcode.树.二叉树;

import com.leetcode.树.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回与给定的前序和后序遍历匹配的任何二叉树。
 * <p>
 *  pre 和 post 遍历中的值是不同的正整数。
 * 示例：
 * <p>
 * 输入：pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
 * 输出：[1,2,3,4,5,6,7]
 *  
 * 提示：
 * <p>
 * 1 <= pre.length == post.length <= 30
 * pre[] 和 post[] 都是 1, 2, ..., pre.length 的排列
 * 每个输入保证至少有一个答案。如果有多个答案，可以返回其中一个。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal
 *
 * @author 洪飞
 * @date 2020/6/10
 */
public class _889_根据前序和后序遍历构造二叉树 {

    // TODO: 2020/6/10 这个没有自己解出来

    int[] pre;
    int[] post;

    Map<Integer, Integer> preIndex = new HashMap<>();
    Map<Integer, Integer> postIndex = new HashMap<>();

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        this.pre = pre;
        this.post = post;
        for (int i = 0; i < pre.length; i++) {
            preIndex.put(pre[i], i);
        }
        for (int i = 0; i < post.length; i++) {
            postIndex.put(post[i], i);
        }
        return constructFromPrePost(0, pre.length - 1, 0, post.length - 1);
    }

    private TreeNode constructFromPrePost(int preLeft, int preRight, int postLeft, int postRight){
        if (preRight < preLeft || postRight < postLeft) return null;

        TreeNode root = new TreeNode(pre[preLeft]);

        for (int i = preLeft+1; i < preRight; i++) {
            for (int j = 0; j < postRight-1; j++) {

            }
        }
        return null;
    }
}
