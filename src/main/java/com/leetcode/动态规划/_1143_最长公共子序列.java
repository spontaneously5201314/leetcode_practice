package com.leetcode.动态规划;

/**
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 * <p>
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 * <p>
 * 若这两个字符串没有公共子序列，则返回 0。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 * 示例 2:
 * <p>
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc"，它的长度为 3。
 * 示例 3:
 * <p>
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0。
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= text1.length <= 1000
 * 1 <= text2.length <= 1000
 * 输入的字符串只含有小写英文字符。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-subsequence
 */
public class _1143_最长公共子序列 {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3, 5, 9, 10};
        int[] nums2 = new int[]{1, 4, 9, 10};

        _1143_最长公共子序列 v = new _1143_最长公共子序列();
        System.out.println(v.lcs4(nums1, nums2));
//        System.out.println(v.lcsRecur(nums1, nums1.length, nums2, nums2.length));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null) return 0;
        char[] chars1 = text1.toCharArray();
        if (chars1.length == 0) return 0;
        char[] chars2 = text2.toCharArray();
        if (chars2.length == 0) return 0;

        char[] rowsNums = chars1, colsNums = chars2;
        if (chars1.length < chars2.length) {
            colsNums = chars1;
            rowsNums = chars2;
        }

        int[] dp = new int[colsNums.length + 1];
        for (int i = 1; i <= rowsNums.length; i++) {
            int curr = 0;
            for (int j = 1; j <= colsNums.length; j++) {
                int leftTop = curr;
                curr = dp[j];
                if (rowsNums[i - 1] == colsNums[j - 1]) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[colsNums.length];
    }

    /**
     * 对于{@link _1143_最长公共子序列#lcs3(int[], int[])}优化
     * 因为一位数组的长度是nums2的长度，但是如果给定两个数组，可以使用最短的数组的长度作为dp的长度
     */
    private int lcs4(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return 0;

        int[] rowsNums = nums1, colsNums = nums2;
        if (nums1.length < nums2.length) {
            colsNums = nums1;
            rowsNums = nums2;
        }

        int[] dp = new int[colsNums.length + 1];
        for (int i = 1; i <= rowsNums.length; i++) {
            int curr = 0;
            for (int j = 1; j <= colsNums.length; j++) {
                int leftTop = curr;
                curr = dp[j];
                if (rowsNums[i - 1] == colsNums[j - 1]) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[colsNums.length];
    }

    /**
     * 优化{@link _1143_最长公共子序列#lcs2(int[], int[])}
     * 1、对dp的存储空间进行优化，因为原来是使用一个二维数组来实现的，但是其实每次计算只使用到了三个元素，所以可以使用一位数组来实现
     */
    private int lcs3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return 0;

        int[] dp = new int[nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            int curr = 0;
            for (int j = 1; j <= nums2.length; j++) {
                int leftTop = curr;
                curr = dp[j];
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[nums2.length];
    }

    /**
     * 优化{@link _1143_最长公共子序列#lcs1(int[], int[])}
     * 1、对dp的存储空间进行优化
     * 2、对取模运算进行优化，从%2改成&1
     */
    private int lcs2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return 0;

        int[][] dp = new int[2][nums2.length + 1];

        for (int i = 1; i <= nums1.length; i++) {
            int currentRow = i & 1;
            int prevRow = (i - 1) & 1;
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[currentRow][j] = dp[prevRow][j - 1] + 1;
                } else {
                    dp[currentRow][j] = Math.max(dp[prevRow][j], dp[currentRow][j - 1]);
                }
            }
        }
        return dp[nums1.length & 1][nums2.length];
    }

    /**
     * 使用动态规划来实现，使用了二维数组来存储dp，但是每次计算dp[i][j]其实只和dp[i-1][j-1]、dp[i-1][j]或者dp[i][j-1]有关
     * 可以优化成使用滚动数组，只保留两行数据，见{@link _1143_最长公共子序列#lcs2(int[], int[])}
     */
    private int lcs1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return 0;

        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        //Java中数组在初始化之后，如果没有赋值，默认是0，所以下面的赋值代码可以不用
//        for (int i = 0; i < nums1.length; i++) {
//            dp[i][0] = 0;
//        }
//        for (int j = 0; j < nums2.length; j++) {
//            dp[0][j] = 0;
//        }

        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[nums1.length][nums2.length];
    }

    /**
     * 使用递归，求nums1前i个元素和nums2前j个元素的最长公共子序列长度
     */
    private int lcsRecur(int[] nums1, int i, int[] nums2, int j) {
        if (i == 0 || j == 0) return 0;
        if (nums1[i - 1] == nums2[j - 1]) {
            return lcsRecur(nums1, i - 1, nums2, j - 1) + 1;
        }
        return Math.max(lcsRecur(nums1, i, nums2, j - 1), lcsRecur(nums1, i - 1, nums2, j));
    }
}
