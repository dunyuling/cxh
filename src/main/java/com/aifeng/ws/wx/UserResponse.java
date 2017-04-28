package com.aifeng.ws.wx;

/**
 * Created by pro on 17-4-27.
 */
public class UserResponse {

    private String userId;
    private String deviceId;



//    private String UserId;
//    private String DeviceId;
    private String errcode;
    private String errmsg;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        deviceId = deviceId;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
