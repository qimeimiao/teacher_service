package com.lss.teacher_manager.exception;

public class BussinessException extends RuntimeException {


	private Integer code;

	private String  msg;

	private Object body;


	public BussinessException(Integer code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public BussinessException(String msg, Object body) {
		super(msg);
		this.body = body;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
