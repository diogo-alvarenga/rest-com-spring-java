package br.com.diogo_alvarenga.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)//adicionar um status code
public class UnsupportedMathOperationException  extends RuntimeException{
	public UnsupportedMathOperationException(String message) {super(message);
	
		
	}
}
