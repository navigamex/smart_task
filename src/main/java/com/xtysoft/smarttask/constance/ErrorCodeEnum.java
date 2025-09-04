package com.xtysoft.smarttask.constance;


import com.xtysoft.common.constance.IBaseErrorCodeEnum;
import lombok.Getter;

@Getter

public enum ErrorCodeEnum implements IBaseErrorCodeEnum {

    SYSTEM_UNKNOW_ERROR(-1, "系统错误"),
    LOGIN_FAILED(1001, "登录失败"),
    SUCCESS(0, "成功");

    private int errorCode;
    private String message;

    ErrorCodeEnum(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public static ErrorCodeEnum getErrorCodeEnum(int err) {
        for (int i = 0; i < ErrorCodeEnum.values().length; i++) {
            if (ErrorCodeEnum.values()[i].getErrorCode() == err) {
                return ErrorCodeEnum.values()[i];
            }
        }
        return SYSTEM_UNKNOW_ERROR;
    }

    public static String getErrorMessage(int errorCode) {
        ErrorCodeEnum[] values = values();
        for (ErrorCodeEnum error : values) {
            if (error.getErrorCode() == errorCode) {
                return error.getMessage();
            }
        }
        return "系统错误!参考错误码" + errorCode;
    }

    public int getCode() {
        return this.errorCode;
    }
     
}