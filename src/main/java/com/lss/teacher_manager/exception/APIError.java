package com.lss.teacher_manager.exception;

/**
 * @author ss
 */
public enum APIError {
    NOT_FOUND(404,"没有找到对象"),
    CUSTOM(400,"参数错误"),
    NOTEMPTY(410,"参数不能为空");
    private Integer  code;
    private String  msg;
    APIError(Integer code, String msg) {
        this.code = code;
        this.msg= msg;
    }
    public APIError set(Integer code,String msg){
        this.msg = msg;
        this.code = code;
        return this;
    }

    public APIError set(Integer code){
        this.code = code;
        return this;
    }
    public APIError set(String msg){
        this.msg = msg;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void expose(){
        throw  new BussinessException(this.code,this.msg);
    }

}
