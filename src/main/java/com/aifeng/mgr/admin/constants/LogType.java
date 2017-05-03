package com.aifeng.mgr.admin.constants;

/**
 * Created by pro on 17-4-30.
 */
public enum LogType {

    RECHARGE("充值"), REFUND("退费");

    private String type;

    LogType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
