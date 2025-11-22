package br.com.diogo_alvarenga.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)//adicionar um status code
public class RequiredObjectIsNullException  extends RuntimeException{
	
	public RequiredObjectIsNullException() {
		super("It is not allowed to persist a null object!");
	
	}
	public RequiredObjectIsNullException(String message) {super(message);
	
		
	}
}
