package com.example;

public class ContasCredor {
    private String nome;
    private String titulos;
    private String parcela;
    private double quantidade;
    private double valor;

    public ContasCredor() {}

    public ContasCredor(String nome, String titulos, String parcela, double quantidade, double valor) {
        this.nome = nome;
        this.titulos = titulos;
        this.parcela = parcela;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulos() {
        return titulos;
    }

    public void setTitulos(String titulos) {
        this.titulos = titulos;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    } 
}

