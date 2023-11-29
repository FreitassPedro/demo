package com.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import com.example.ContasCredor;

public class DataCSV {
    public static void pegarDados() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader(App.directoryCSV()));
        // Pular a primeira linha
        if (sc.hasNextLine()) {
            sc.nextLine();
        }
        while (sc.hasNextLine()) {
            String dados = sc.nextLine();
            System.out.println(dados);
            String[] dadosDividos = dados.split(";");

            String dataPagamento = dadosDividos[0].replace("/", ".");
            String nomeCredor = removerAcentos(dadosDividos[1]);
            String numeroTitulo = dadosDividos[2];
            String numeroParcela = dadosDividos[3];
            String valorPagamentoLiquido = formatarMoeda(dadosDividos[4]);

            String resposta = LocalizarPdf.localizarArquivosPdf(nomeCredor, valorPagamentoLiquido, numeroTitulo,
                    numeroParcela);
            
            //Retorna se encontrar apenas Similaridade com o nome mas o valor for diferente
            if (resposta.equals("semReturnApenasSimilaridade")) {
                verificarValores(nomeCredor, dadosDividos[4], numeroTitulo);
            }
            System.out.println("Resposta: " + resposta);
            System.out.println();
        }
        sc.close();
        ContasCredor.imprimirTodosValores();
    }


    private static String removerAcentos(String str) {
        String nome = Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        nome = nome.replace("\"", "");
        nome = nome.trim();
        return nome.toUpperCase();
    }
    
    //Formtar valor inserido, por exemplo,  7312,06 vira 7.312,06
    private static String formatarMoeda(String valorEntrada) {
        double valorPendencia = Double.parseDouble(valorEntrada.replace(",", "."));
        String precoString;
        if (valorPendencia >= 1000) {
            precoString = String.format(Locale.forLanguageTag("pt-BR"), "%,.2f", valorPendencia);
        } else {
            precoString = String.format(Locale.forLanguageTag("pt-BR"), 
            "%.2f", valorPendencia).replace(".", ",");
        }
        return precoString;
}

    public static void verificarValores(String nome, String valor, String titulo) {
        valor = valor.replace(",", ".");
        Double valorDouble = Double.parseDouble(valor);
    
        ContasCredor.adicionarItem("Banana", 2000.2, "19212");
        ContasCredor.adicionarItem("Banana", 500.00, "10000");
        ContasCredor.adicionarItem(nome, valorDouble, titulo);
    }

    
}
