package com.fintech.projeto;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoDB {

    public static Connection obterConexao(){
        Connection conexao = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");

            conexao = DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL",
                    "RM552218", "120904");

        } catch (Exception e){
            e.printStackTrace();
        }
        return conexao;
    }

    public static void main(String[] args) {
        ConexaoDB.obterConexao();
    }
}
