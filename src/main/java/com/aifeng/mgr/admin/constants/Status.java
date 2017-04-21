package com.aifeng.mgr.admin.constants;

/**
 * Created by pro on 17-4-21.
 */
public enum Status {

    WAITING("等待中"), SUCCESS("成功"), FAILURE("失败");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
