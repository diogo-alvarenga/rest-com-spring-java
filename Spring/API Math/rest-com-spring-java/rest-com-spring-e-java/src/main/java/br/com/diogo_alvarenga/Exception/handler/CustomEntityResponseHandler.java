package br.com.diogo_alvarenga.Exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.diogo_alvarenga.Exception.ExceptionResponse;
import br.com.diogo_alvarenga.Exception.UnsupportedMathOperationException;

//ControllerAdvice é um manipulador de excecoes global
@ControllerAdvice //quando um controller lançar uma exceção ele cairá aqui
@RestController
public class CustomEntityResponseHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)//qual excecao irá tratar
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request){
		ExceptionResponse response = new ExceptionResponse(
				new Date(),
				ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(UnsupportedMathOperationException.class)//qual excecao irá tratar
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception ex, WebRequest request){
		ExceptionResponse response = new ExceptionResponse(
				new Date(),
				ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
