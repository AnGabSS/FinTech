package com.fintech.projeto.DAO;

import com.fintech.projeto.beans.*;
import com.fintech.projeto.connection.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GanhoDAO {
	private static Connection conexao;
	
	public static List<Ganho> getAllGanhos() {
		 
	    List<Ganho> lista = new ArrayList<Ganho>();
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	      conexao = ConexaoDB.obterConexao();
	    stmt = conexao.prepareStatement("SELECT * FROM T_SIP_GANHOS");
	    rs = stmt.executeQuery();
	  
	    while (rs.next()) {
	    String nome = rs.getString("NM_LUCRO");
	    String descricao = rs.getString("DS_LUCRO");
	    double valor = rs.getDouble("NR_VALOR");
	        

	        Ganho ganho = new Ganho(nome, valor, descricao);
	        
	        lista.add(ganho);
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
	
	public static void cadastrarGanho(Usuario usuario, Ganho ganho) {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConexaoDB.obterConexao();
			String sql = "INSERT INTO T_SIP_GANHOS(CD_GANHO, CPF, NM_LUCRO, NR_VALOR, DS_LUCRO) VALUES (SQ_LUCRO.NEXTVAL, ?, ?, ?, ?)";
			String sql1 = "UPDATE T_SIP_USUARIO SET NR_SALDO = ? WHERE CPF = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getCPF());
			stmt.setString(2, ganho.getNome());
			stmt.setDouble(3, ganho.getValor());
			stmt.setString(4, ganho.getDescricao());
			stmt.executeUpdate();
			
			stmt.close();
			ganho.addGanhoSaldo(usuario);
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
