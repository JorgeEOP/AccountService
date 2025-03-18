package com.service.AccountService.response;

/**
 * Handles all responses of the API
 */
public class AccountServiceResponse<T> {
	private boolean success = false;
	private String message;
	private T data;
	private int errorCode;
	/**
	 * 
	 * @return
	 */
	public boolean isSuccess() {
		return this.success;
	}
	/**
	 * 
	 * @param success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 
	 * @return
	 */
	public T getData() {
		return data;
	}
	/**
	 * 
	 * @param data
	 */
	public void setData(T data) {
		this.data = data;
	}
	/**
	 * 
	 * @return
	 */
	public int getErrorCode() {
		return errorCode;
	}
	/**
	 * 
	 * @param errorCode
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
