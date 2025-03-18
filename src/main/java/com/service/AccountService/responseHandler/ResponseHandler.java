package com.service.AccountService.responseHandler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.service.AccountService.response.AccountServiceResponse;
import com.service.AccountService.response.ECustomerNotFound;
import com.service.AccountService.response.EInvalidParameters;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ResponseHandler {
	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public AccountServiceResponse<Object> handleGeneralException(Exception ex, HttpServletRequest request) {
		return ResponseHandler.error(ex.getMessage(), "An Error occurred", 500, request.getRequestURI());
	}
	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ECustomerNotFound.class)
	public AccountServiceResponse<Object> handleResourceNotFoundException(ECustomerNotFound ex,
			HttpServletRequest request) {
		return ResponseHandler.error(ex.getMessage(), "Resource not found", 404, request.getRequestURI());
	}
	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(EInvalidParameters.class)
	public AccountServiceResponse<Object> handleResponseNotFoundException(EInvalidParameters ex,
			HttpServletRequest request) {
		return ResponseHandler.error(ex.getMessage(), "Invalid Parameters", 204, request.getRequestURI());
	}
	/**
	 * 
	 * @param <T>
	 * @param data
	 * @param message
	 * @param path
	 * @return
	 */
    public static <T> AccountServiceResponse<T> success(T data, String message) {
    	AccountServiceResponse<T> response = new AccountServiceResponse<>();
        response.setSuccess(true);
        response.setMessage(message);
        response.setData(data);
        response.setErrorCode(0); // No error
        return response;
    }
    /**
     * 
     * @param <T>
     * @param error
     * @param message
     * @param errorCode
     * @param path
     * @return
     */
	public static <T> AccountServiceResponse<T> error(String error, String message, int errorCode, String path) {
		AccountServiceResponse<T> response = new AccountServiceResponse<>();
        response.setSuccess(false);
        //response.setMessage(message);
        response.setMessage(error);
        response.setData(null);
        response.setErrorCode(errorCode);
        return response;
	}
}