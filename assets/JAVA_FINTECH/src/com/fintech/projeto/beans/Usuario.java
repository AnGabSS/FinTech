package com.fintech.projeto.beans;


public class Usuario extends Pessoa {
	
	double saldo;
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, String CPF, String dtNascimento, String sexo, double saldo) {
		this.setNome(nome);
		this.setCPF(CPF);
		this.setDtNascimento(dtNascimento);
		this.setSexo(sexo);
		this.setSaldo(saldo);
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public double getSaldo() {
		return saldo;
	}

}
