package com.leetcode.树.二叉树;

import com.leetcode.树.TreeNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * @author 洪飞
 * @date 2020/6/10
 */
public class _106_从中序与后序遍历序列构造二叉树 {

    public static void main(String[] args) {
        int[] inorder = new int[]{9, 3, 15, 20, 7};
//        int[] inorder = new int[]{2, 1};
        int[] postorder = new int[]{9, 15, 7, 20, 3};
//        int[] postorder = new int[]{2, 1};
        _106_从中序与后序遍历序列构造二叉树 v = new _106_从中序与后序遍历序列构造二叉树();
        v.buildTree(inorder, postorder);
    }

    int[] postorder;

    /**
     * 存储中序遍历中，每个元素和其对应的下标，其中key是元素，value是对应下标
     */
    Map<Integer, Integer> inOrderIndex = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            inOrderIndex.put(inorder[i], i);
        }
        this.postorder = postorder;
        return buildTree(0, inorder.length - 1, 0, postorder.length - 1);
//        return buildTree(Arrays.stream(inorder).boxed().collect(Collectors.toList()), Arrays.stream(postorder).boxed().collect(Collectors.toList()));
    }

    /**
     * 这种方法，最好的地方在于不用在方法上传递数据，只需要传递下标即可
     * 主要在于计算下标
     * @param inLeft
     * @param inRight
     * @param postLeft
     * @param postRight
     * @return
     */
    private TreeNode buildTree(int inLeft, int inRight, int postLeft, int postRight) {
        if (inLeft > inRight || postLeft > postRight) {
            return null;
        }
        Integer rootValue = postorder[postRight];
        TreeNode root = new TreeNode(rootValue);

        int rootIndex = inOrderIndex.get(rootValue);

        root.left = buildTree(inLeft, rootIndex - 1, postLeft, postLeft + rootIndex - 1 - inLeft);
        root.right = buildTree(rootIndex + 1, inRight, postLeft + rootIndex - inLeft, postRight - 1);

        return root;
    }

    /**
     * 这种方法因为要转换数组和List，时间上会慢很多
     * @param inorder
     * @param postorder
     * @return
     */
    private TreeNode buildTree(List<Integer> inorder, List<Integer> postorder) {
        if (inorder.size() == 0 || postorder.size() == 0) {
            return null;
        }
        //获取根节点的值
        Integer rootValue = postorder.get(postorder.size() - 1);
        TreeNode root = new TreeNode(rootValue);

        if (inorder.size() <= 1 || postorder.size() <= 1) {
            return root;
        }

        //获取左子树的中序遍历结果
        List<Integer> tempInorder = inorder.subList(0, inorder.indexOf(rootValue));
        root.left = buildTree(tempInorder, postorder.subList(0, tempInorder.size()));

        List<Integer> tempPostOrder = postorder.subList(tempInorder.size(), postorder.size() - 1);
        root.right = buildTree(inorder.subList(inorder.indexOf(rootValue) + 1, inorder.size()), tempPostOrder);

        return root;
    }
}
