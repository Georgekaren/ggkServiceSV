package com.tower.resmaster.framework.exceptions;

public class AppException extends Exception {
    private static final long serialVersionUID = 1L;
    
    private String code;
    private String resolve;

    public String getCode() {
		return code;
	}

	public String getResolve() {
		return resolve;
	}

	public void setResolve(String resolve) {
		this.resolve = resolve;
	}
	
	public AppException(String message, String code, String resolve){
		super(message);
		this.code = code;
		this.resolve = resolve;
	}
	
	public AppException(String message, String code, String resolve, Throwable e){
        super(message, e);
        this.code = code;
        this.resolve = resolve;
    }
    
    public AppException(Throwable e){
        super(e);
    }
	
	public AppException(String message, Throwable e){
		super(message, e);
	}

	public AppException(String message){
        super(message);
    }

    public AppException(){
        super();
    }
}
