package com.example;

import org.apache.commons.text.similarity.JaroWinklerSimilarity;

import java.io.File;

public class LocalizarPdf {
    public String nomeProcurar(String nome) {
        return nome;
    }

    public static String localizarArquivosPdf(String nomeDestinatarioCSV, String valorTotal, String titulo,
            String parcela) {
        JaroWinklerSimilarity similarity = new JaroWinklerSimilarity();

        String diretorio = App.directoryFiles();

        File diretorioArquivos = new File(diretorio);
        
        // Verifica se o diretório existe
        if (diretorioArquivos.exists() && diretorioArquivos.isDirectory()) {
            String resposta = "Nada encontrado";
            File[] arquivos = diretorioArquivos.listFiles();
            String valorDataArquivo = "";
            String nomeDestinatarioArquivo = "";

            String valorRecortadoArquivo;
            // Itera pelos arquivos no diretório

            for (File arquivo : arquivos) {
                String nomeArquivo = arquivo.getName();
                System.out.println("TESTE 1 --------------------------------------------");
                System.out.println("Arquivo 1: " + nomeArquivo);
                System.out.println("Valor a ser encontrado: " + valorTotal);

                // Se CONTEM String valor total
                System.out.println(nomeArquivo.contains(valorTotal));
                System.out.println("--------------------------------------------");
                try {
                    String[] partes = nomeArquivo.split(" - ");
                    valorDataArquivo = partes[0];
                    nomeDestinatarioArquivo = partes[1];

                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }

                double score = similarity.apply(nomeDestinatarioArquivo, nomeDestinatarioCSV.trim());

                double limiteSimilaridade = 0.68;

                if (arquivo.isFile() && arquivo.getName().contains(nomeDestinatarioCSV)
                        && arquivo.getName().contains(valorTotal)) {
                    // adicionarTitulo(arquivo, titulo, parcela);
                    return "returnNomeValor";
                } else if (arquivo.isFile() && score >= limiteSimilaridade && valorDataArquivo.contains(valorTotal)) {
                    // adicionarTitulo(arquivo, titulo, parcela);
                    return "returnSimilaridade"; // Arquivo encontrado, não é necessário continuar a busca
                } else if (arquivo.isFile() && score >= limiteSimilaridade) {
                    System.out.println(valorDataArquivo + " : " + valorTotal);
                    resposta = "semReturnApenasSimilaridade";
                } else if (arquivo.isFile() && arquivo.getName().contains(valorTotal)) {
                    System.out.println("valor: " + nomeDestinatarioArquivo);
                    resposta = "semReturnApenasValor";
                }
            }

            return resposta;
        } else {
            System.out.println("O diretório especificado não existe ou não é um diretório válido.");
        }
        return "Não encontrado";
    }

    public static void adicionarTitulo(File arquivo, String titulo, String parcela) {
        String[] nomeTratar = arquivo.getName().split(".pdf");
        nomeTratar[0] = nomeTratar[0].replace("--", "-");
        System.out.println("nome tratar: " + nomeTratar[0]);
        if (!titulo.equals("null")) {
            String nomeFinal = nomeTratar[0] + "_" + titulo + "." + parcela + ".pdf";

            File arquivoFinal = new File(App.directoryFiles(), nomeFinal);

            arquivo.renameTo(arquivoFinal);
        }

    }
}