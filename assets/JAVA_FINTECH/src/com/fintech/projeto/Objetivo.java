package com.fintech.projeto;

/**
 * Classe que abstrai um objetivo a ser realizado
 * @author Angelo Gabriel Souza Santana
 * @version 1.0
 */

public class Objetivo {
	
	
	//Cria uma variavel para armazenar o nome do ganho
	private String nome;
	
	//Cria uma variavel para armazena o valor dele
	private static double valorNecessario;
	
	//Cria uma variavel para armazenar a descrição
	private String descricao;
	
	//Cria uma variavel para armazenar uma data de previsão
	private String dtPrevisao;
	
	//Cria uma variavel para armazenar o  status do objetivo
	private boolean status;

	
	
	
	
	//Declaração do métodos construtores
	public Objetivo(){
		
	}
	public Objetivo(String nome, double valorNecessario,String dtPrevisao, boolean status) {
		this.setNome(nome);
		this.setValorNecessario(valorNecessario);
		this.setDtPrevisao(dtPrevisao);
		this.setStatus(status);
		
	}
	
	public Objetivo (String nome, double valorNecessario,String dtPrevisao, boolean status, String descricao) {
		this.setNome(nome);
		this.setValorNecessario(valorNecessario);
		this.setDtPrevisao(dtPrevisao);
		this.setStatus(status);
		this.setDescricao(descricao);
	}
	
	
	
	
	//Declaração dos métodos set e get
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNome() {
		return nome;
	}
	
	
	
	public void setValorNecessario(double valorNecessario) {
		Objetivo.valorNecessario = valorNecessario;
	}
	public double getValorNecessario() {
		return valorNecessario;
	}
	
	
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean getStatus() {
		return status;
	}
	
	
	
	public void setDtPrevisao(String dtPrevisao) {
		this.dtPrevisao = dtPrevisao;
	}
	public String getDtPrevisao() {
		return dtPrevisao;
	}
	

}
