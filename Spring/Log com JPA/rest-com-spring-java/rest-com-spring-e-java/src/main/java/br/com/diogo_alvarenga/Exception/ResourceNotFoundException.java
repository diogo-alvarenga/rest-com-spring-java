package br.com.diogo_alvarenga.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)//adicionar um status code
public class ResourceNotFoundException  extends RuntimeException{
	public ResourceNotFoundException(String message) {super(message);
	
		
	}
}
