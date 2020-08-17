package com.clive.common;

import java.io.Serializable;

public class StoreResult implements Serializable{
	private int code;
	private String msg;
	private Object data;
	public StoreResult(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public StoreResult(Object data) {
		this.code = 200;
		this.msg = "ok";
		this.data = data;
	}
	public StoreResult() {
		this.code = 200;
		this.msg = "ok";
	}
	public StoreResult(int code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	//调用build返回这个三个参数的构造方法
	public static StoreResult build(int code,String msg,Object data){
		return new StoreResult(code, msg, data);
	}
	//调用ok返回一个参数的构造方法
	public static StoreResult ok(Object data){
		return new StoreResult(data);
	}
	//调用ok返回一个参数的构造方法
	public static StoreResult ok(){
		return new StoreResult();
	}
	public static StoreResult ok(int code,String msg){
		return new StoreResult(code,msg);
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
