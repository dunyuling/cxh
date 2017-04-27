package com.aifeng.mgr.admin.constants;

/**
 * Created by pro on 17-4-27.
 */
public enum InsuranceType {

    IT_0(0, "交强险"),
    IT_1(1, "机动车辆损失险"),
    IT_2(2, "第三者责任险"),
    IT_3(3, "盗抢险"),
    IT_4(4, "车上人员责任险"),
    IT_5(5, "车身划痕损失险"),
    IT_6(6, "玻璃单独破碎险"),
    IT_7(7, "自燃损失险"),
    IT_8(8, "发动机特别损失险"),
    IT_9(9, "不计免赔率特约损失险");

    private int index;
    private String type;

    InsuranceType(int index, String type) {
        this.index = index;
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    public static InsuranceType getTypeByIndex(int index) {
        switch (index) {
            case 0:
                return IT_0;
            case 1:
                return IT_1;
            case 2:
                return IT_2;
            case 3:
                return IT_3;
            case 4:
                return IT_4;
            case 5:
                return IT_5;
            case 6:
                return IT_6;
            case 7:
                return IT_7;
            case 8:
                return IT_8;
            case 9:
                return IT_9;
            default:
                return IT_0;
        }
    }
}
