package br.com.diogo_alvarenga.Model;

public record Greeting(long id, String content) {
	/*
	 *records criam as variaveis do parametro, construtores 
	 *e getters, MAS NÂO SETTERS, pois as variaveis sao
	 *todas privates, portanto, imutaveis */
}
