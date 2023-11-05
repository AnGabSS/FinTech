package com.fintech.projeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ObjetivoDAO {
	private static Connection conexao;
	
	public static List<Objetivo> getAllObjetivos() {
		 
	    List<Objetivo> lista = new ArrayList<Objetivo>();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	      conexao = ConexaoDB.obterConexao();
	    stmt = conexao.prepareStatement("SELECT * FROM T_SIP_OBJETIVOS");
	    rs = stmt.executeQuery();
	  
	    while (rs.next()) {
	    String nome = rs.getString("NM_OBJETIVO");
	    String dtPrevisao = rs.getString("DT_PREVISAO");
	    int statusInt = rs.getInt("STATUS");
	    boolean status = false;
	    if(statusInt != 0) {
	    	status = true;
	    }
	    double valorNecessario = rs.getDouble("NR_VALOR_NESC");
	    String descricao = rs.getString("DS_OBJETIVO");
	        

	    	Objetivo objetivo = new Objetivo(nome, valorNecessario, dtPrevisao, status, descricao);
	        
	        lista.add(objetivo);
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
	
	public static void cadastrarObjetivo(Usuario usuario, Objetivo objetivo) {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConexaoDB.obterConexao();
			String sql = "INSERT INTO T_SIP_OBJETIVOS(CD_OBJETIVO, CPF, NM_OBJETIVO, NR_VALOR_NESC, DS_OBJETIVO, DT_PREVISAO) VALUES(SQ_OBJETIVO.NEXTVAL, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getCPF());
			stmt.setString(2, objetivo.getNome());
			stmt.setDouble(3, objetivo.getValorNecessario());
			stmt.setString(4,objetivo.getDescricao());
			stmt.setString(5, objetivo.getDtPrevisao());
			stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close(); 
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
