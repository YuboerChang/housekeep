package com.coolers.housekeep.housekeep.po;

public class ApplyTemplateStep {
    private Integer templateId;

    private Byte stepId;

    private Byte nextStepId;

    private String participants;

    private String introduce;

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Byte getStepId() {
        return stepId;
    }

    public void setStepId(Byte stepId) {
        this.stepId = stepId;
    }

    public Byte getNextStepId() {
        return nextStepId;
    }

    public void setNextStepId(Byte nextStepId) {
        this.nextStepId = nextStepId;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants == null ? null : participants.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }
}