package br.com.diogo_alvarenga.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class GenderSerializer extends JsonSerializer<String>{

	@Override
	public void serialize(String gender, JsonGenerator gen, SerializerProvider serializers) throws IOException {

		//se gender for "Male", retorna M, se nao, retorna F
		String formatedGender = "Male".equals(gender) ? "M": "F";
	
		gen.writeString(formatedGender);
	}

}
