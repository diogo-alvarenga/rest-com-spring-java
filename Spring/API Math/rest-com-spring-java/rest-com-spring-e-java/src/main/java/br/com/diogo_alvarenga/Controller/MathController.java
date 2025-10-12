package br.com.diogo_alvarenga.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.diogo_alvarenga.Exception.UnsupportedMathOperationException;
import br.com.diogo_alvarenga.Service.MathService;

@RestController
@RequestMapping("/math")//nesessita dessa url
public class MathController {
	
	MathService service = new MathService();
	
	@RequestMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(
			@PathVariable("numberOne") String numberOne, 
			@PathVariable("numberTwo") String numberTwo
	) throws Exception{
		if(!service.isNumeric(numberOne) || !service.isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Please set a numeric value!"); {
			return service.soma(numberOne,numberTwo);
		}
	}
	
	@RequestMapping("/subtraction/{numberOne}/{numberTwo}")
	public Double subtraction(
			@PathVariable("numberOne") String numberOne,
			@PathVariable("numberTwo") String numberTwo) throws Exception {
		if(!service.isNumeric(numberOne)|| !service.isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Please set a numeric value!");{
			return service.subtracao(numberOne, numberTwo);
		}
	}
	
	@RequestMapping("/multiplication/{numberOne}/{numberTwo}")
	public Double multiplication(
			@PathVariable("numberOne") String numberOne,
			@PathVariable("numberTwo") String numberTwo) throws Exception{
		if(!service.isNumeric(numberOne)||!service.isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Please set a numeric value!");{
			return service.multiplicacao(numberOne, numberTwo);
		}
	}
	
	@RequestMapping("/division/{numberOne}/{numberTwo}")
	public Double division(
			@PathVariable("numberOne") String numberOne,
			@PathVariable("numberTwo") String numberTwo)throws Exception {
		
		if(!service.isNumeric(numberOne)|| !service.isNumeric(numberTwo))throw new UnsupportedMathOperationException("Please set a numeric value!");
			return service.divisao(numberOne, numberTwo);
	}

	@RequestMapping("/mean/{numberOne}/{numberTwo}")
	public Double mean(
			@PathVariable("numberOne") String numberOne,
			@PathVariable("numberTwo") String numberTwo) throws Exception {
		if(!service.isNumeric(numberOne)||!service.isNumeric(numberTwo)) throw new UnsupportedMathOperationException("Please set a numeric value!");{
			return service.media(numberOne, numberTwo);
		}
	}
	
	@RequestMapping("/squareroot/{number}")
	public Double squareroot(
			@PathVariable("number") String number) throws Exception {
		if(!service.isNumeric(number)) throw new UnsupportedMathOperationException("Please set a numeric value!");{
			return service.raizQuadrada(number);
		}
	}
	
	
	

	
}
