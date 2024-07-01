package com.coolers.housekeep.housekeep.po;

public class ApplyStep {
    private Integer applyId;

    private Byte stepId;

    private Integer worker;

    private String opinion;

    private String readers;

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Byte getStepId() {
        return stepId;
    }

    public void setStepId(Byte stepId) {
        this.stepId = stepId;
    }

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

    public String getReaders() {
        return readers;
    }

    public void setReaders(String readers) {
        this.readers = readers == null ? null : readers.trim();
    }
}