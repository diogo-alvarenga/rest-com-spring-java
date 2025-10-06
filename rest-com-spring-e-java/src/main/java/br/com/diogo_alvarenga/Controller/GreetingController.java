package br.com.diogo_alvarenga.Controller;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.diogo_alvarenga.Model.Greeting;

@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping("/greeting")//na linha de baixo, o "name"é o valor na url, e ele cairá na string name
	public Greeting greeting(@RequestParam(value = "name", defaultValue= "Word") String name) {
		return new Greeting(counter.incrementAndGet(),String.format(template, name));
	}
}
