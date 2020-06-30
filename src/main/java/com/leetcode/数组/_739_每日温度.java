package com.leetcode.数组;

import java.util.Arrays;
import java.util.Stack;

/**
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 *
 * @author 洪飞
 * @date 2020/6/16
 */
public class _739_每日温度 {

    public static void main(String[] args) {
        _739_每日温度 v = new _739_每日温度();
        int[] temperatures = v.dailyTemperatures_queue(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        System.out.println(Arrays.toString(temperatures));

        // TODO: 2020/6/17 这种解法的效率最低
    }

    /**
     * 使用单调栈来解决
     */
    public int[] dailyTemperatures_queue(int[] T) {
        if (T == null || T.length == 0) return null;

        int[] result = new int[T.length];

        Stack<Integer> stack = new Stack();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                Integer pop = stack.pop();
                result[pop] = i - pop;
            }
            stack.push(i);
        }
        return result;
    }

    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0) return null;
        int[] result = new int[T.length];

        for (int i = T.length - 2; i >= 0; i--) {
            int j = i + 1;
            while (true) {
                if (T[i] < T[j]) {
                    result[i] = j - i;
                    break;
                } else if (result[j] == 0) {
                    result[i] = 0;
                    break;
                } else if (T[i] == T[j]) {
                    result[i] = result[j] + j - i;
                    break;
                } else {
                    j += result[j];
                }
            }
        }

        return result;
    }
}
