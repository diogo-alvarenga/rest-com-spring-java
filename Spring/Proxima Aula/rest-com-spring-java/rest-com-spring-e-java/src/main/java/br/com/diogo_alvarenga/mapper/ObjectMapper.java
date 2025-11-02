package br.com.diogo_alvarenga.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class ObjectMapper {

	//Mapeia Entidade para DTO
	//e DTO para entidade
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	//converte de origin para dto
	//relembrando, os<> antes do tipo de retorno apenas declara que o metodo é generico
	public static<O,D> D parseObject(O origin, Class<D> destination) {
		
		return mapper.map(origin, destination);
	}
	
	
	public static<O,D> List<D> parseListObjects(List<O> origin, Class<D> destination){
		
		List<D> destinationObjects = new ArrayList<D>();
		for(Object o: origin) {
			destinationObjects.add(mapper.map(o, destination));
		}
		
		return destinationObjects;
	}
}
