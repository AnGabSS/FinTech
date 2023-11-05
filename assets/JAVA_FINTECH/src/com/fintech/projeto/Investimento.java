package com.fintech.projeto;

import java.sql.Date;

/**
 * Classe que abstrai um investimento que será adicionado a conta no finTech
 * @author Angelo Gabriel Souza Santana
 * @version 1.0
 */


public class Investimento {
	
	//Declaração da variavel para armazenar o nome
	private String nome;
	
	//Declaração da variavel para armazenar a quantidade de cotas
	private static double cotas;
	
	//Declaração da variavel para armazenar a descrição
	private String descricao;
	
	private java.sql.Date dataInsercao;
	
	
	//Declaração dos métodos construtores
	public Investimento() {
		
	}
	
	public Investimento(String nome, double cotas) {
		this.setNome(nome);
		this.setCotas(cotas);
	}
	
	public Investimento(String nome, double cotas, String descricao) {
		this.setNome(nome);
		this.setCotas(cotas);
		this.setDescricao(descricao);
	}
	
	//Declaração dos métodos set e get
	
	public Investimento(String nome, double cotas, String descricao, Date dataInsercao) {
		this.setNome(nome);
		this.setCotas(cotas);
		this.setDescricao(descricao);
		this.setDataInsercao(dataInsercao);
	}

	private void setDataInsercao(Date dataInsercao) {
		this.dataInsercao = dataInsercao;
		
	}
	
	public java.sql.Date getDataInsercao(){
		return dataInsercao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	
	
	public void setCotas(double cotas) {
		Investimento.cotas = cotas;
	}
	public double getCotas() {
		return cotas;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
		
	}
	
	public double addInvestimentoSaldo(Usuario usuario) {
		usuario.saldo = usuario.saldo + (Investimento.cotas);
		return usuario.saldo;
	}
}
