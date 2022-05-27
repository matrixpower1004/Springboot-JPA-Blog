package com.matrix.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.matrix.blog.dto.ResponseDto;

@ControllerAdvice // 어디서든 Exception이 발생하면 이 class로 오도록
@RestController
public class GlobalExceptionHandler {
	
	// Exception이 발생하면 아래 메소드를 실행
	@ExceptionHandler(value=Exception.class) 
	public ResponseDto<String> handleArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
	}
	
	
}
