package br.com.diogo_alvarenga.Controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.diogo_alvarenga.Model.Greeting;

@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s!";
	//atomiclong é um tipo de variavel feita para ser usada por varias threads
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping("/greeting")//na linha de baixo, o "name"é o valor na url, e ele cairá na string name
	public Greeting greeting(@RequestParam(value = "name", defaultValue= "Word") String name) {
		return new Greeting(
				
				/*a linha abaixo incrementa um numero atomico a cada conexao de alguem
				 * isso faz com que varias pessoas possam acessar e assim irá gerar
				 * uma thread por conexao, e o valor nao irá se perder entre o
				 * passar de thread a thread, pois o tipo atomico o protege*/
				
				// 1. GERAÇÃO SEGURA: incrementAndGet() garante que a leitura, 
				// incremento e escrita sejam ATÔMICOS (ininterruptos) e 
				// nenhuma Thread perca o ID sequencial.
				counter.incrementAndGet(),
				
				String.format(template, name));//formata para retornar
				
				//assim é retornado um objeto Greeting com id e conteudo
	}
}
