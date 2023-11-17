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
            File[] arquivos = diretorioArquivos.listFiles();

            // Itera pelos arquivos no diretório

            for (File arquivo : arquivos) {
                String nomeDestinatarioArquivo = "";

                String[] partes = arquivo.getName().split(" -- ");
                String valorDataArquivo = partes[0];
                nomeDestinatarioArquivo = partes[1];


                double score = similarity.apply(nomeDestinatarioArquivo, nomeDestinatarioCSV.trim());

                double limiteSimilaridade = 0.68;

                if (score >= limiteSimilaridade) {
                    System.out.println("Comprovante: " + nomeDestinatarioArquivo);
                    System.out.println("Similaridade: " + score);
                }
                if (arquivo.isFile() && score >= limiteSimilaridade && valorDataArquivo.contains(valorTotal)) {
                    System.out.println("PDF ENCONTRADO: NOME E VALOR -------- lançar!");
                    adicionarTitulo(arquivo, titulo, parcela);
                    return "nomeValorSimilaridade"; // Arquivo encontrado, não é necessário continuar a busca
                } else if (arquivo.isFile() && arquivo.getName().contains(nomeDestinatarioCSV)) {
                    System.out.println("PDF ENCONTRADO: NOME E VALOR CONTAINS");
                    return "nomeValorContains";
                } else if (arquivo.isFile() && score >= limiteSimilaridade) {
                    System.out.println("PDF ENCONTRADO: NOME");
                    return "nome";
                } else if (arquivo.isFile() && arquivo.getName().contains(valorTotal)) {
                    System.out.println("PDF ENCONTRADO: VALOR");
                    return "valor";
                } else {
                    return "nada";
                }
            }

            // Se chegou até aqui, o arquivo não foi encontrado
            System.out.println("COMPROVANTE: Não encontrado");
            return "Não encontrado";
        } else {
            System.out.println("O diretório especificado não existe ou não é um diretório válido.");
        }
        return "Não encontrado 2";
    }

    

    public static void adicionarTitulo(File arquivo, String titulo, String parcela) {
        String[] nomeTratar = arquivo.getName().split(".pdf");
        nomeTratar[0] = nomeTratar[0].replace("--", "-");
        if (!titulo.equals("null")) {
            String nomeFinal = nomeTratar[0] + " - " + titulo + "." + parcela + ".pdf";

            File arquivoFinal = new File(App.directoryFiles(), nomeFinal);

            arquivo.renameTo(arquivoFinal);
        }

    }
}