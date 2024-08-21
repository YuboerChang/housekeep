package com.coolers.housekeep.housekeep.po;

public class WebLogWithBLOBs extends WebLog {
    private String reqParams;

    private String resParams;

    public String getReqParams() {
        return reqParams;
    }

    public void setReqParams(String reqParams) {
        this.reqParams = reqParams == null ? null : reqParams.trim();
    }

    public String getResParams() {
        return resParams;
    }

    public void setResParams(String resParams) {
        this.resParams = resParams == null ? null : resParams.trim();
    }
}