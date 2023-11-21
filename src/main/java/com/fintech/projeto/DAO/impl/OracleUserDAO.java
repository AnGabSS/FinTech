package com.fintech.projeto.DAO.impl;

import com.fintech.projeto.DAO.UserDAO;
import com.fintech.projeto.beans.*;
import com.fintech.projeto.connection.*;
import com.fintech.projeto.exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OracleUserDAO implements UserDAO{

	private static Connection conexao;
	
		 public List<Usuario> listar() {
			 
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
	
	public void cadastrar(Usuario usuario) {
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




	@Override
	public void atualizar(Usuario user) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConexaoDB.obterConexao();
			String sql = "UPDATE T_SIP_USUARIOS SET NM_USUARIO = ?, DT_NASCIMENTO = ?, DS_SEXO = ?, NR_SALDO = ? WHERE CPF =?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, user.getNome());
			stmt.setString(2, user.getDtNascimento());
			stmt.setString(3, user.getSexo());
			stmt.setDouble(4, user.getSaldo());
			stmt.setString(5, user.getCPF());
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


	@Override
	public void remover(String CPF) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			conexao = ConexaoDB.obterConexao();
			String sql = "DELETE FROM T_SIP_USUARIO WHERE CPF = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, CPF);
			stmt.executeUpdate();
			
		}catch (SQLException e) {
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


	@Override
	public Usuario buscar(String CPF) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Usuario resultado = null;
		
		try {
			conexao = ConexaoDB.obterConexao();
			String sql = "SELECT * FROM T_SIP_USUARIO WHERE CPF = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, CPF);
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				CPF = rs.getString("CPF");
				String nome = rs.getString("NM_USUARIO");
				String dtNasc = rs.getString("DT_NASCIMENTO");
				String sexo = rs.getString("DS_SEXO");
				double saldo = rs.getDouble("NR_SALDO");
				
				resultado = new Usuario(CPF, nome, dtNasc, sexo, saldo);
				return resultado;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();			
			}
		}
		return resultado;
	}


	
}



	

