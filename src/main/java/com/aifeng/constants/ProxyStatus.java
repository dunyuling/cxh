package com.aifeng.constants;

/**
 * Created by pro on 17-4-29.
 */
public enum ProxyStatus {

    Applying("申请中"), Authored("已授权"), Expired("已过期") ,Refused("被拒绝");

    private String ps;

    ProxyStatus(String ps) {
        this.ps = ps;
    }

    public String getPs() {
        return ps;
    }
}
