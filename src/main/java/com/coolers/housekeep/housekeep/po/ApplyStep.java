package com.coolers.housekeep.housekeep.po;

import java.util.Date;

public class ApplyStep extends ApplyStepKey {
    private Integer worker;

    private String opinion;

    private String unRead;

    private Boolean isFinish;

    private String hasRead;

    private String introduce;

    private Byte templateStepId;

    private Date createTime;

    public Integer getWorker() {
        return worker;
    }

    public void setWorker(Integer worker) {
        this.worker = worker;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion == null ? null : opinion.trim();
    }

    public String getUnRead() {
        return unRead;
    }

    public void setUnRead(String unRead) {
        this.unRead = unRead == null ? null : unRead.trim();
    }

    public Boolean getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Boolean isFinish) {
        this.isFinish = isFinish;
    }

    public String getHasRead() {
        return hasRead;
    }

    public void setHasRead(String hasRead) {
        this.hasRead = hasRead == null ? null : hasRead.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Byte getTemplateStepId() {
        return templateStepId;
    }

    public void setTemplateStepId(Byte templateStepId) {
        this.templateStepId = templateStepId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}