package org.yee.springboot.security.dictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: org.yee.springboot.security.dictionary
 * @Class: Arrange
 * @Author: brucewong
 * @Time: 16/02/2018 17:50 50
 * @Version 1.0.0
 * @Copyright: Copyright (c)2018
 * @Describe: springboot-learning
 * 一. 排列组合
 * 1. 排列
 * （1）排列：从n个元素中排列m个元素。
 * Amn=n∗(n−2)∗...∗(n−m+1)=Cmn∗m!
 * （2）全排列：把n个元素全部排列。
 * Ann=n!=n∗(n−1)∗...∗1
 * 2. 组合
 * 组合：从n个元素中选择m个元素（不考虑顺序）。
 * Cmn=n∗(n−2)∗...∗(n−m+1)m∗(m−1)∗...∗1
 **/
public class Arrange {
    public static void main(String[] args) {
        Arrange arrange = new Arrange();
        List<Character> data = new ArrayList<Character>();
        data.add('a');
        data.add('b');
//        data.add('c');
//        data.add('d');

        //输出A(n,n)的全排列
        for (int i = 2; i <= data.size(); i++) {
            arrange.arrangeSelect(data, new ArrayList<Character>(), i);
        }

    }

    /**
     * 计算A(n,k)
     *
     * @param data
     * @param target
     * @param k
     */
    public <E> void arrangeSelect(List<E> data, List<E> target, int k) {
        List<E> copyData;
        List<E> copyTarget;
        if (target.size() == k) {
            for (E i : target) {
                System.out.print(i);
            }
            System.out.println();
        }

        for (int i = 0; i < data.size(); i++) {
            copyData = new ArrayList<E>(data);
            copyTarget = new ArrayList<E>(target);

            copyTarget.add(copyData.get(i));
            copyData.remove(i);

            arrangeSelect(copyData, copyTarget, k);
        }
    }
}
