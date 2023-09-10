package com.fintech.projeto;

public class teste {
	public static void main(String[] args) {
		
		Usuario cc = new Usuario("Angelo", "39486298734", "12/09/2004", 'M', 20.00);
		Ganho ganho = new Ganho("salario", 2000.00);
		Despesa despesa = new Despesa("game pass", 19.00);
		Investimento criptomoeda = new Investimento("criptomoeda", -100.00);
		Objetivo casa = new Objetivo("casa", 100000.00, "31/09/2025", false);
		
		
		System.out.println(cc.getSaldo() + " + " + ganho.getValor());
		ganho.addGanhoSaldo();
		System.out.println(cc.getSaldo());
		
		System.out.println("--------------------");
		
		System.out.println(cc.getSaldo() + " - " + despesa.getValor());
		despesa.addDespesaSaldo();
		System.out.println(cc.getSaldo());
		
		System.out.println("--------------------");
		
		System.out.println(cc.getSaldo() + " + " + criptomoeda.getCotas());
		criptomoeda.addInvestimentoSaldo();
		System.out.println(cc.getSaldo());
		
		System.out.println("--------------------");
		
		System.out.println("Nome: " + cc.getNome());
		System.out.println("CPF: " + cc.getCPF());
		System.out.println("Data de nascimento: " + cc.getDtNascimento());
		System.out.println("Sexo: " + cc.getSexo());
		System.out.println("Saldo disponível: " + cc.getSaldo());
		
		System.out.println("--------------------");
		
		System.out.println("nome: " + casa.getNome());
		System.out.println("Valor necessário: " + casa.getValorNecessario());
		System.out.println("Descrição: " + casa.getDescricao());
		System.out.println("Previsão: " + casa.getDtPrevisao());
		System.out.println("Status: " + casa.getStatus());
		
			
	}

}
