package com.base.sort.bubble;

import com.base.util.RandomArrayUtils;
import org.junit.jupiter.api.Test;

/**
 * @author 洪飞
 * @date 2020/6/20
 */
class BubbleSortTest {

    @Test
    void bubbleSortV1() {
        Integer[] random = RandomArrayUtils.random(10, 1, 10000);
        RandomArrayUtils.println(random);
        BubbleSort.bubbleSortV1(random);
        RandomArrayUtils.println(random);
    }

    @Test
    void bubbleSortV2() {
        Integer[] random = RandomArrayUtils.random(10, 1, 1000);
        RandomArrayUtils.println(random);
        BubbleSort.bubbleSortV2(random);
        RandomArrayUtils.println(random);
    }

    @Test
    void bubbleSortV3() {
        Integer[] random = RandomArrayUtils.random(10, 1, 1000);
        RandomArrayUtils.println(random);
        BubbleSort.bubbleSortV3(random);
        RandomArrayUtils.println(random);
    }
}