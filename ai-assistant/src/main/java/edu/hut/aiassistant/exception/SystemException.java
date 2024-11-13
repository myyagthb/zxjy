package edu.hut.aiassistant.exception;

import edu.hut.aiassistant.enums.SystemExceptionEnum;

;

public class SystemException extends RuntimeException{

    private SystemExceptionEnum e;

    public SystemException(){

    }

    public SystemException(SystemExceptionEnum e) {
        System.out.println(e.getDesc());
        this.e = e;
    }

    public SystemExceptionEnum getE() {
        return e;
    }

    public void setE(SystemExceptionEnum e) {
        this.e = e;
    }

    //不写入堆信息，提高性能
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
