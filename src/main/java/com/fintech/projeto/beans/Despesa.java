package com.fintech.projeto.beans;

/**
 * Classe que abstrai uma despesa que será adicionado a conta no finTech
 * @author Angelo Gabriel Souza Santana
 * @version 1.0
 */


public class Despesa extends Movimentacao {
	
	//Declaração do métodos construtores
	public Despesa(){
		
	}
	public Despesa(String nome, double valor) {
		this.setNome(nome);
		this.setValor(valor);
		
	}
	
	public Despesa(String nome, double valor, String descricao) {
		this.setNome(nome);
		this.setValor(valor);
		this.setDescricao(descricao);
	}
	

	public Despesa(String cod, String nome, double valor, String descricao) {
		this.setCod(cod);
		this.setNome(nome);
		this.setValor(valor);
		this.setDescricao(descricao);
	}
	
public Despesa(String cod , String string, double valor) {
	this.setCod(cod);
	this.setNome(nome);
	this.setValor(valor);
	}

	
	//Declaração para subtrair valor da despesa do saldo total da conta
	public double addDespesaSaldo(Usuario usuario) {
		usuario.saldo = usuario.saldo - this.valor;
		return usuario.saldo;
	}
}
