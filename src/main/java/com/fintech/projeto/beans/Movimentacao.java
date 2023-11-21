package com.fintech.projeto.beans;

/**
 * Classe que abstrai uma movimentação no saldo
 * @author Angelo Gabriel Souza Santana
 * @version 1.0
 */

public class Movimentacao {
	
	//Cria uma variavel para armazenar o nome do ganho
	String nome;
	
	//Cria uma variavel para armazena o valor dele
	double valor;
	
	//Cria uma variavel para armazenar a descrição
	String descricao;
	
	String cod;
	
	
	//Declaração dos métodos set e get
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getValor() {
		return valor;
	}
	
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setCod(String cod) {
		this.cod = cod;
	}
	
	public String getCod() {
		return cod;
	}
}
