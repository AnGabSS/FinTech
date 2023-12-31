package com.fintech.projeto.beans;

/**
 * Classe que abstrai uma pessoa no finTech
 * @author Angelo Gabriel Souza Santana
 * @version 1.0
 */


public class Pessoa {
    
    //Cria uma variavel para o Nome do usuario
    String nome;
    
    //Cria uma variavel para CPF
    String CPF;
    
    //Cria uma variavel para Data de nascimento
    String dtNascimento;
    
    //Cria uma variavel para sexo do usuario
    String sexo;
    
    //Cria uma variavel para saldo do usuario
    public double saldo;

    //Declaração dos métodos construtores
    public Pessoa() {
        
    }
    
    public Pessoa(String nome, String CPF, String dtNascimento, String sexo, double saldo) {
        this.setNome(nome);
        this.setCPF(CPF);
        this.setDtNascimento(dtNascimento);
        this.setSexo(sexo);
    }

    //Declaração dos métodos set e get
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;    
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String string) {
        this.sexo = string;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }
}	