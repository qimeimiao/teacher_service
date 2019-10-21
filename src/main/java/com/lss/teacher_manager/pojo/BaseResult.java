package com.lss.teacher_manager.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseResult {
	private int status = 200;
	private String message = "success";
	private Object body;

	public BaseResult() {
	}
	public BaseResult(int status, String message, Object body) {
		this.status = status;
		this.message = message;
		this.body = body;
	}

	public BaseResult(int status, String message) {
		this.status = status;
		this.message = message;
	}
	public static BaseResult error(Integer  status,String msg){
		return error(status,msg);
	}
	public static BaseResult error(Integer  status,String msg,Object data){
		BaseResult  baseResult = new BaseResult(status,msg);
		baseResult.setBody(data);
		return baseResult;
	}

}
