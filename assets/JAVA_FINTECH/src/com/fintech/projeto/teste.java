package com.fintech.projeto;

import java.util.List;

public class teste {
	public static void main(String[] args) {
		
		Usuario cc = new Usuario("Angelo", "39486298734", "12/09/2004", "M", 20.00);
		Ganho salario = new Ganho("salario", 2000.00);
		Despesa gamepass = new Despesa("game pass", 19.00);
		Investimento criptomoeda = new Investimento("criptomoeda", -100.00);
		Objetivo casa = new Objetivo("casa", 100000.00, "31/09/2025", false);
		
		
		UserDAO.cadastrarUsuario(cc);
		GanhoDAO.cadastrarGanho(cc, salario);
		DespesaDAO.cadastrarDespesa(cc, gamepass);
		InvestimentoDAO.cadastrarInvestimento(cc, criptomoeda);
		ObjetivoDAO.cadastrarObjetivo(cc, casa);
		 
		List<Usuario> listaUser = UserDAO.getAllUsers();
		   for (Usuario user : listaUser) {
		     System.out.println(user.getCPF() + " " + user.getNome() + " " + user.getDtNascimento() + " " + user.getSexo() + " " + user.getSaldo());
		      }
		   
	    List<Ganho> listaGanho = GanhoDAO.getAllGanhos();
		   for (Ganho ganho : listaGanho) {
		     System.out.println(ganho.getNome() + " " + ganho.getValor() + " " + ganho.getDescricao());
		      }
		   
		List<Despesa> listaDespesa = DespesaDAO.getAllDespesas();
		   for (Despesa despesa : listaDespesa) {
		     System.out.println(despesa.getNome() + " " + despesa.getValor() + " " + despesa.getDescricao());
		      }
		   
		List<Investimento> listaInvestimento = InvestimentoDAO.getAllInvestimentos();
		   for (Investimento investimento : listaInvestimento) {
		     System.out.println(investimento.getNome() + " " + investimento.getCotas() + " " + investimento.getDescricao() + " " + investimento.getDataInsercao());
		      }
		   
		List<Objetivo> listaObjetivo = ObjetivoDAO.getAllObjetivos();
		   for (Objetivo objetivo : listaObjetivo) {
		     System.out.println(objetivo.getNome() + " " + objetivo.getValorNecessario() + " " + objetivo.getDtPrevisao() + " " + objetivo.getStatus() + " " + objetivo.getDescricao());
		      }
		    
		    
		    
	}
			
}


