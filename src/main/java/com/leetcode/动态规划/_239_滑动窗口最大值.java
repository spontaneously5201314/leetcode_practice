package com.leetcode.动态规划;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 * <p>
 * 进阶：
 * 你能在线性时间复杂度内解决此题吗？
 * <p>
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 *  
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 */
public class _239_滑动窗口最大值 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return new int[0];
        if (k == 1) return nums;

        int[] maxes = new int[nums.length - k + 1];
        // 当前滑动窗口的最大值索引
        int maxIdx = 0;
        // 求出前k个元素的最大值索引
        for (int i = 1; i < k; i++) {
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }

        // li是滑动窗口的最左索引
        for (int li = 0; li < maxes.length; li++) {
            // ri是滑动窗口的最右索引
            int ri = li + k - 1;
            if (maxIdx < li) { // 最大值的索引不在滑动窗口的合理范围内
                // 求出[li, ri]范围内最大值的索引
                maxIdx = li;
                for (int i = li + 1; i <= ri; i++) {
                    if (nums[i] > nums[maxIdx]) maxIdx = i;
                }
            } else if (nums[ri] >= nums[maxIdx]) { // 最大值的索引在滑动窗口的合理范围内
                maxIdx = ri;
            }
            maxes[li] = nums[maxIdx];
        }

        return maxes;
    }

    public int[] maxSlidingWindow_Deque(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return new int[0];
        if (k == 1) return nums;
        int[] maxes = new int[nums.length - k + 1];

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            int w = i - k + 1;
            if (w < 0) continue;
            if (deque.peekFirst() < w) {
                deque.pollFirst();
            }
            maxes[w] = nums[deque.peekFirst()];
        }

        return maxes;
    }
}
