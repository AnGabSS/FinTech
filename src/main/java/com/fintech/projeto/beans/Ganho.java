package com.fintech.projeto.beans;

import java.util.Date;

/**
 * Classe que abstrai um ganho que será adicionado a conta no finTech
 * @author Angelo Gabriel Souza Santana
 * @version 1.0
 */

public class Ganho extends Movimentacao {
	
	Date dtInsercao;

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
	
	public Ganho(String cod, String nome, double valor, String descricao) {
		this.setCod(cod);
		this.setNome(nome);
		this.setValor(valor);
		this.setDescricao(descricao);
	}
	
	public Ganho(String cod, String nome, double valor, String descricao, Date dtInsercao) {
		this.setCod(cod);
		this.setNome(nome);
		this.setValor(valor);
		this.setDescricao(descricao);
		this.setDtInsercao(dtInsercao);
	}
	
	
	public void setDtInsercao(java.util.Date dtInsercao) {
		this.dtInsercao = dtInsercao;
	}
	
	public java.util.Date dtInsercao() {
		return dtInsercao;
	}
	
	//Declaração da operação para adicionar ganho ao saldo total
	public double addGanhoSaldo(Usuario usuario) {
		usuario.saldo = usuario.saldo + this.valor;
		return usuario.saldo;
	}
}
