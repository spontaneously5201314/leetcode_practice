package com.leetcode.栈;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 */
public class _42_接雨水 {

    public static void main(String[] args) {
        _42_接雨水 v = new _42_接雨水();
        System.out.println(v.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    public int trap(int[] height) {
//        return trapWithStack(height);
        return trapWithTwoPoint(height);
    }

    /**
     * 使用双指针来解决
     */
    private int trapWithTwoPoint(int[] height) {
        if (height == null || height.length == 0) return 0;
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[0];
        int rightMax = height[height.length - 1];
        int sum = 0;

        // TODO: 2020/6/30 这种双指针的方法尚未理解

        return sum;
    }

    /**
     * 使用单调栈来解决
     */
    private int trapWithStack(int[] height) {
        if (height == null || height.length == 0) return 0;
        int[] leftMaxArray = new int[height.length];
        int[] rightMaxArray = new int[height.length];

        int leftMax = height[0];
        leftMaxArray[0] = leftMax;
        for (int i = 0; i < height.length; i++) {
            if (height[i] >= leftMax) {
                leftMax = height[i];
            }
            leftMaxArray[i] = leftMax;
        }

        int rightMax = height[height.length - 1];
        rightMaxArray[height.length - 1] = rightMax;
        for (int i = height.length - 1; i >= 0; i--) {
            if (height[i] >= rightMax) {
                rightMax = height[i];
            }
            rightMaxArray[i] = rightMax;
        }

        int sum = 0;
        for (int i = 0; i < leftMaxArray.length; i++) {
            sum += Math.min(leftMaxArray[i], rightMaxArray[i]) - height[i];
        }
        return sum;
    }
}
