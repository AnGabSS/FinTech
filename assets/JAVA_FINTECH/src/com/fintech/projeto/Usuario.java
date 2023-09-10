package com.fintech.projeto;

public class Usuario extends Pessoa {
	
	static double saldo;
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, String CPF, String dtNascimento, char sexo, double saldo) {
		this.setNome(nome);
		this.setCPF(CPF);
		this.setDtNascimento(dtNascimento);
		this.setSexo(sexo);
		this.setSaldo(saldo);
	}
	
	public void setSaldo(double saldo) {
		Usuario.saldo = saldo;
	}
	public double getSaldo() {
		return saldo;
	}

}
