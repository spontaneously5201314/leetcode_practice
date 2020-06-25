package com.leetcode.递归;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。移动圆盘时受到以下限制:
 * (1) 每次只能移动一个盘子;
 * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
 * (3) 盘子只能叠在比它大的盘子上。
 * <p>
 * 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
 * <p>
 * 你需要原地修改栈。
 * <p>
 * 示例1:
 * <p>
 * 输入：A = [2, 1, 0], B = [], C = []
 * 输出：C = [2, 1, 0]
 * 示例2:
 * <p>
 * 输入：A = [1, 0], B = [], C = []
 * 输出：C = [1, 0]
 * 提示:
 * <p>
 * A中盘子的数目不大于14个。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hanota-lcci
 */
public class _面试题_08_06_汉诺塔问题 {

    /**
     * 到
     * 思路：递归调用
     * 其实分两种情况（假设盘子的数量是n）：
     * 1、当n==1时，直接将盘子从第一个柱子移动到最后一个柱子
     * 2、当n>1时候，可以拆分成3个步骤来执行：
     * 2.1、将n-1个盘子从最左边移动到中间的柱子上
     * 2.2、将剩下的编号为n的盘子从最左边的柱子移动到最右边的柱子
     * 2.3、将剩下n-1个盘子从中间的柱子移动到最右边的柱子上
     * 可见：步骤2.1和2.3是递归调用
     */


    public static void main(String[] args) {
        _面试题_08_06_汉诺塔问题 v = new _面试题_08_06_汉诺塔问题();
        v.hanota(Arrays.asList(1, 0), new ArrayList<>(), new ArrayList<>());
    }

    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        hanota(A.size(), A, B, C);
    }

    private void hanota(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
        if (n == 1) {
            C.add(A.get(A.size() - 1));
            A.remove(A.size() - 1);
        } else {
            //把A的n-1个盘子放到B上
            hanota(n - 1, A, C, B);
            //把A放到C上
            C.add(A.get(A.size() - 1));
            A.remove(A.size() - 1);
            //把B放到C上
            hanota(n - 1, B, A, C);
        }
    }
}
