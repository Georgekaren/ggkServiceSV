package com.tower.resmaster.framework.exceptions;

/**
 * DAO“Ï≥£¿‡
 * @author shen.zhi
 * @date 2016-5-14
 */
public class DaoException extends RuntimeException{
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
	
	public DaoException(String message){
		super(message);
	}

	public DaoException(String msg, Throwable cause){
		super(msg, cause);
	}
	
	public DaoException(Throwable cause){
	    super(cause);
	}
	
	public DaoException(){
        super();
    }
	
    public DaoException(String message, String code, String resolve){
        super(message);
        this.code = code;
        this.resolve = resolve;
    }
    
    public DaoException(String message, String code, String resolve, Throwable e){
        super(message, e);
        this.code = code;
        this.resolve = resolve;
    }
}

