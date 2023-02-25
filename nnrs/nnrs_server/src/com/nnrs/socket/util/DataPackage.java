package com.nnrs.socket.util;
/*
 * 数据打包
 */
public class DataPackage implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 编码
	 */
	String code;
	
	/**
	 * 动作
	 */
	int action;
	
	/**
	 * 服务端接收客户端的数据
	 */
	Object data;
	
	/**
	 * 返回状态
	 */
	int state;
	
	/**
	 * 返回消息
	 */
	String message;
	
	/**
	 * 服务端返回客户端的数据
	 */
	Object rdata;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getRdata() {
		return rdata;
	}

	public void setRdata(Object rdata) {
		this.rdata = rdata;
	}
}
