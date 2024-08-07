package com.coolers.housekeep.housekeep.constant;

public class UserConst {
    /**
     * 用户手机验证返回状态码，long，1：等待回执
     */
    public static final String WATTING = "1";
    /**
     * 用户手机验证返回状态码，long，2：发送失败
     */
    public static final String ERROR = "2";
    /**
     * 用户手机验证返回状态码，long，3：发送成功
     */
    public static final String SUCCESS = "3";
    /**
     * 用户手机验证码位数，6位数
     */
    public static final int VERIFICATION_CODE_DIGIT = 6;
    /**
     * 用户手机验证码过期时间0，秒
     */
    public static final int VERIFICATION_CODE_TIMEOUT = 60;
}
