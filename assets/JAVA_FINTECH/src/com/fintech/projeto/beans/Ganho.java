package com.fintech.projeto.beans;

/**
 * Classe que abstrai um ganho que será adicionado a conta no finTech
 * @author Angelo Gabriel Souza Santana
 * @version 1.0
 */

public class Ganho extends Movimentacao {

	//Declaração dos métodos construtores
	public Ganho(){
		
	}
	public Ganho(String nome, double valor) {
		this.setNome(nome);
		this.setValor(valor);
		
	} 
	
	public Ganho(String nome, double valor, String descricao) {
		this.setNome(nome);
		this.setValor(valor);
		this.setDescricao(descricao);
	}
	
	//Declaração da operação para adicionar ganho ao saldo total
	public double addGanhoSaldo(Usuario usuario) {
		usuario.saldo = usuario.saldo + this.valor;
		return usuario.saldo;
	}
}
