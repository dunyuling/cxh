package com.aifeng.mgr.admin.constants;

/**
 * Created by pro on 17-4-29.
 */
public enum ProxyStatus {

    APPLYING("申请中"), AUTHORED("已授权"), EXPIRED("已过期") , REFUSED("被拒绝");

    private String ps;

    ProxyStatus(String ps) {
        this.ps = ps;
    }

    public String getPs() {
        return ps;
    }
}
