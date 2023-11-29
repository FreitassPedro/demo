package com.example;

import java.util.HashMap;
import java.util.Map;

public class ContasCredor {
    private static Map<String, ContasCredor> mapa = new HashMap<>();
    private double valor;
    private Map<String, Double> titulos;
    //Acessar o mapa
    public static Map<String, ContasCredor> getMapa() {
        return mapa;
    }

    public ContasCredor(double valor) {
        this.valor = valor;
        this.titulos = new HashMap<>();
    }

    public double getValor() {
        return valor;
    }

    public Map<String, Double> getTitulos() {
        return titulos;
    }

    public void adicionarValor(double valor) {
        this.valor += valor;
    }

    public void adicionarTitulo(String titulo, double valor) {
        titulos.put(titulo, titulos.getOrDefault(titulo, 0.0) + valor);
    }

    public static void adicionarItem(String nome, double valor, String titulo) {
        ContasCredor credor = mapa.get(nome);
        if (credor == null) {
            credor = new ContasCredor(valor);
            mapa.put(nome, credor);
        } else {
            credor.adicionarValor(valor);
        }


        credor.adicionarTitulo(titulo, valor);
    }

    public static void imprimirTodosValores() {
        System.out.println("IMPRIMINDO TODOS VALORES - OPEN");
        for(Map.Entry<String, ContasCredor> entry : mapa.entrySet()) {
            String nome = entry.getKey();
            ContasCredor credor = entry.getValue();

            System.out.println("Credor: " + nome);
            System.out.println("Valor total: " + credor.getValor());
        System.out.println("IMPRIMINDO TODOS VALORES - CLOSE");
        }
        }
    }