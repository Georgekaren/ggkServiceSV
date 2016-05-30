package com.tower.ggk.wsserver.utils;

public class VerifyReturnVO {

	private int state=0;
	private String code;
	private String message;

	public int getState() {
		return state;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public VerifyReturnVO(){
		
	}
	
    public VerifyReturnVO(int state){
    	setState(state);
	}

	public VerifyReturnVO(String code){
		setCode(code);
		setMessage(MessagePropertyPlaceholderConfigurer.getContextProperty(code).toString());
	}

	public VerifyReturnVO(int state,String code){
		setState(state);
		setCode(code);
		setMessage(MessagePropertyPlaceholderConfigurer.getContextProperty(code).toString());
	}
	
	public String getProperyValue(String code){
		return MessagePropertyPlaceholderConfigurer.getContextProperty(code).toString();
	}
}
