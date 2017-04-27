package com.aifeng.constants;

import com.aifeng.mgr.admin.constants.InsuranceType;

/**
 * Created by pro on 17-4-20.
 */
public class test {
    public static void main(String[] args) {
//        System.out.println((int)(59/10));
//        System.out.println(1 & 2);
        InsuranceType insuranceType = InsuranceType.IT_0;
        System.out.println(insuranceType.getIndex() + " \t " + insuranceType.getType());

        System.out.println(InsuranceType.getTypeByIndex(2));
    }
}
