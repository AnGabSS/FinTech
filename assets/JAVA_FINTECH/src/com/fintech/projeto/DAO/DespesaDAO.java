package com.fintech.projeto.DAO;

import com.fintech.projeto.beans.*;
import com.fintech.projeto.connection.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DespesaDAO {
	
	private static Connection conexao;
	
	public static List<Despesa> getAllDespesas() {
		 
	    List<Despesa> lista = new ArrayList<Despesa>();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	      conexao = ConexaoDB.obterConexao();
	    stmt = conexao.prepareStatement("SELECT * FROM T_SIP_DESPESAS");
	    rs = stmt.executeQuery();
	  
	    while (rs.next()) {
	    	String nome = rs.getString("NM_DESPESA");
		    String descricao = rs.getString("DS_DESPESA");
		    double valor = rs.getDouble("NR_VALOR");
	        

	    	Despesa despesa = new Despesa(nome, valor, descricao);
	        
	        lista.add(despesa);
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }finally {
	      try {
	        stmt.close();
	        rs.close();
	        conexao.close();
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    }
	    return lista;
	  }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public static void cadastrarDespesa(Usuario usuario, Despesa despesa) {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConexaoDB.obterConexao();
			String sql = "INSERT INTO T_SIP_DESPESAS(CD_DESPESA, CPF, NM_DESPESA, NR_VALOR, DS_DESPESA) VALUES (SQ_DESPESA.NEXTVAL, ?, ?, ?, ?)";
			String sql1 = "UPDATE T_SIP_USUARIO SET NR_SALDO = ? WHERE CPF = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getCPF());
			stmt.setString(2, despesa.getNome());
			stmt.setDouble(3, despesa.getValor());
			stmt.setString(4, despesa.getDescricao());
			despesa.addDespesaSaldo(usuario);
			stmt.executeUpdate();
			stmt.close();
			
			stmt = conexao.prepareStatement(sql1);
			stmt.setDouble(1, usuario.getSaldo());
			stmt.setString(2, usuario.getCPF());
			stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close(); 
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
