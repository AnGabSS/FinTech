package com.fintech.projeto.DAO;

import com.fintech.projeto.beans.*;
import com.fintech.projeto.connection.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	private static Connection conexao;
	
		 public static List<Usuario> getAllUsers() {
			 
			    List<Usuario> lista = new ArrayList<Usuario>();
			    PreparedStatement stmt = null;
			    ResultSet rs = null;
			    try {
			      conexao = ConexaoDB.obterConexao();
			    stmt = conexao.prepareStatement("SELECT * FROM T_SIP_USUARIO");
			    rs = stmt.executeQuery();
			  
			    while (rs.next()) {
			    String CPF = rs.getString("CPF");
			    String nome = rs.getString("NM_USUARIO");
			    String dtNascimento = rs.getString("DT_NASCIMENTO");
			    String sexo = rs.getString("DS_SEXO");
			    double saldo = rs.getDouble("NR_SALDO");
			        

			        Usuario user = new Usuario(nome, CPF, dtNascimento, sexo, saldo);
			        
			        lista.add(user);
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

	
//----------------------------------------------------------------------------------otherDAO------------------------------------------------------------------------------------------------------------	
	
	public static void cadastrarUsuario(Usuario usuario) {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConexaoDB.obterConexao();
			String sql = "INSERT INTO T_SIP_USUARIO(CPF, NM_USUARIO, DT_NASCIMENTO, DS_SEXO, NR_SALDO) VALUES (?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getCPF());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getDtNascimento());
			stmt.setString(4, usuario.getSexo());
			stmt.setDouble(5, usuario.getSaldo());
			
			stmt.executeUpdate();
			System.out.println("usuario cadastrado");
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



	

