package com.fintech.projeto.teste;

import java.util.List;

import com.fintech.projeto.DAO.DespesaDAO;
import com.fintech.projeto.DAO.impl.OracleDespesaDAO;
import com.fintech.projeto.DAO.impl.OracleGanhoDAO;
import com.fintech.projeto.DAO.impl.OracleInvestimentoDAO;
import com.fintech.projeto.DAO.impl.OracleObjetivoDAO;
import com.fintech.projeto.DAO.impl.OracleUserDAO;
import com.fintech.projeto.beans.Despesa;
import com.fintech.projeto.beans.Ganho;
import com.fintech.projeto.beans.Investimento;
import com.fintech.projeto.beans.Objetivo;
import com.fintech.projeto.beans.Usuario;
import com.fintech.projeto.exception.DBException;
import com.fintech.projeto.factory.DAOFactory;

public class teste {
	public static void main(String[] args) throws DBException {
		
		Usuario cc = new Usuario("Angelo", "39486298734", "12/09/2004", "M", 20.00);
		//Ganho salario = new Ganho("salario", 2000.00);
		Despesa gamepass = new Despesa("game pass", 19.00);
		Despesa internet = new Despesa("internet", 200);
		Despesa cinema = new Despesa("Cinema", 20);
		
		
		//Investimento criptomoeda = new Investimento("criptomoeda", -100.00);
		//Objetivo casa = new Objetivo("casa", 100000.00, "31/09/2025", false);
		
		DespesaDAO despesaDAO = DAOFactory.getDespesaDAO();
		List<Despesa> listaDespesa = despesaDAO.listar();
		   for (Despesa despesa : listaDespesa) {
		     System.out.println(despesa.getCod()+ " " +despesa.getNome() + " " + despesa.getValor() + " " + despesa.getDescricao());
		      }
		
		//gamepass.setNome("Game_Pass");
		//System.out.println(gamepass.getCod() + " " +gamepass.getNome() + " " + gamepass.getValor());
		
		//despesaDAO.listar();
		//despesaDAO.listar();		
		
		
		
		
		/*OracleUserDAO.cadastrarUsuario(cc);
		OracleGanhoDAO.cadastrarGanho(cc, salario);
		OracleDespesaDAO.cadastrarDespesa(cc, gamepass);
		OracleInvestimentoDAO.cadastrarInvestimento(cc, criptomoeda);
		OracleObjetivoDAO.cadastrarObjetivo(cc, casa);
		 
		List<Usuario> listaUser = OracleUserDAO.getAllUsers();
		   for (Usuario user : listaUser) {
		     System.out.println(user.getCPF() + " " + user.getNome() + " " + user.getDtNascimento() + " " + user.getSexo() + " " + user.getSaldo());
		      }
		   
	    List<Ganho> listaGanho = OracleGanhoDAO.getAllGanhos();
		   for (Ganho ganho : listaGanho) {
		     System.out.println(ganho.getNome() + " " + ganho.getValor() + " " + ganho.getDescricao());
		      }
		   
		List<Despesa> listaDespesa = OracleDespesaDAO.getAllDespesas();
		   for (Despesa despesa : listaDespesa) {
		     System.out.println(despesa.getNome() + " " + despesa.getValor() + " " + despesa.getDescricao());
		      }
		   
		List<Investimento> listaInvestimento = OracleInvestimentoDAO.getAllInvestimentos();
		   for (Investimento investimento : listaInvestimento) {
		     System.out.println(investimento.getNome() + " " + investimento.getCotas() + " " + investimento.getDescricao() + " " + investimento.getDataInsercao());
		      }
		   
		List<Objetivo> listaObjetivo = OracleObjetivoDAO.getAllObjetivos();
		   for (Objetivo objetivo : listaObjetivo) {
		     System.out.println(objetivo.getNome() + " " + objetivo.getValorNecessario() + " " + objetivo.getDtPrevisao() + " " + objetivo.getStatus() + " " + objetivo.getDescricao());
		      }
		    */
		    
		    
	}
			
}


