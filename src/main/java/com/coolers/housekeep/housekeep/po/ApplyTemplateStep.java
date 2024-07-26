package com.coolers.housekeep.housekeep.po;

public class ApplyTemplateStep extends ApplyTemplateStepKey {
    private String participants;

    private String introduce;

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