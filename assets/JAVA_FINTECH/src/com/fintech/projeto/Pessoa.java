package com.fintech.projeto;

/**
 * Classe que abstrai uma pessoa no finTech
 * @author Angelo Gabriel Souza Santana
 * @version 1.0
 */


public class Pessoa {
	
	//Cria uma variavel para o Nome do usuario
	String nome;
	
	//Cria uma variavel para CPF
	String CPF;
	
	//Cria uma variavel para Data de nascimento
	String dtNascimento;
	
	//Cria uma variavel para sexo do usuario
	char sexo;
	
	//Cria uma variavel para saldo do usuario
	public static double saldo;

	
	//Declaração dos métodos construtores
	public Pessoa() {
		
	}
	
	public Pessoa(String nome, String CPF, String dtNascimento, char sexo, double saldo) {
		this.setNome(nome);
		this.setCPF(CPF);
		this.setDtNascimento(dtNascimento);
		this.setSexo(sexo);
	}
	
	
	
	
	//Declaração dos métodos set e get
	public String getNome() {
		return nome;
	}
	
	protected void setNome(String nome) {
		this.nome = nome;	
	}
	
	
	
	public String getCPF(){
		return CPF;
	}

	protected void setCPF(String CPF) {
		this.CPF = CPF;
		
	}
	
	
	
	public char getSexo() {
		return sexo;
	}

	protected void setSexo(char sexo) {
		this.sexo = sexo;
		
	}

	
	
	
	public String getDtNascimento () {
		return dtNascimento;
	}
	protected void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
		
	}
	
	
	
}
	
	