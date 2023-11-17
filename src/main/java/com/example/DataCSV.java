package com.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import com.example.LocalizarPdf;

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
            System.out.println(dataPagamento);
            System.out.println(nomeCredor);
            System.out.println(valorPagamentoLiquido);

            // double valorPagamentoSomado = Double.parseDouble(dadosDividos[4]);
            String resposta = LocalizarPdf.localizarArquivosPdf(nomeCredor, valorPagamentoLiquido, numeroTitulo,
                    numeroParcela);
            System.out.println("Resposta: " + resposta);
            System.out.println();
            // String nomeDestinatario, String valorTotal, String titulo, String parcela
        }
        sc.close();
    }


    private static String removerAcentos(String str) {
        String nome = Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        nome = nome.replace("\"", "");
        nome = nome.trim();
        return nome.toUpperCase();
    }

    private static String formatarMoeda(String valorEntrada) {
        String valorFormatado = valorEntrada.replace(",", ".");

        try {
            // Converte a string para um número
            double valor = Double.parseDouble(valorFormatado);

            // Formata o valor como moeda brasileira
            NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            return formatoMoeda.format(valor);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter a string para um número: " + e.getMessage());
            return "Erro ao formatar moeda";
        }
}
}
