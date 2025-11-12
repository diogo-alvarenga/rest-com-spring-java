package br.com.diogo_alvarenga.serialization.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

//pedi para o chat comentar essa aula para estudar depois

//Classe final: não pode ser estendida por outras classes
//Extende AbstractJackson2HttpMessageConverter, que é usado pelo Spring
//para converter objetos Java em respostas HTTP usando um mapper (como JSON ou YAML)
public final class YamlJackson2HttppMessageConverter extends AbstractJackson2HttpMessageConverter {

 // Construtor protegido: só pode ser chamado dentro do mesmo pacote
 // ou por subclasses (não aplicável aqui, já que a classe é final)
 protected YamlJackson2HttppMessageConverter() { 
     // Chama o construtor da classe pai passando dois parâmetros:
     super(
         // 1. O mapper que será usado para serializar/deserializar objetos
         new YAMLMapper()
             // Configura o mapper para **ignorar campos nulos** ao serializar objetos
             // Exemplo: se um atributo for null, ele não aparecerá no YAML
             .setSerializationInclusion(JsonInclude.Include.NON_NULL),

         // 2. O tipo de mídia que esse converter suporta
         // Aqui estamos dizendo que o converter lida com "application/x-yaml"
         // Isso faz com que o Spring use esse converter automaticamente
         // para endpoints que produzem YAML ou quando a content negotiation indicar YAML
         MediaType.parseMediaType("application/yaml")
     );
 }
}


