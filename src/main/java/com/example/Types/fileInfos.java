package com.example.Types;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

import static com.example.Types.Types.*;

public class fileInfos {
    public static String directory() {
        String username = System.getProperty("user.name");
        return "C:\\Users\\" + username + "\\Desktop\\doc\\";
        // return "C:\\Users\\" + username + "\\Desktop\\projetoExcel\\comprovantes\\";
    }
    public static String fileText(String path) throws IOException {
        try (PDDocument document = PDDocument.load(new File(path))) {
            if (!document.isEncrypted()) {
                PDFTextStripper stripper = new PDFTextStripper();
                return stripper.getText(document);
            }
            document.close();
            return null;
        }
    }

    //APARTIR DO FILETEXT, COLETA QUAL O TIPO DO COMPROVANTE, POSSUINDO APENAS 2 TIPOS
    public static Integer fileType(String path) throws IOException {
        String text = fileText(path);
        if (text.startsWith("Ass")) {
            return 1;
        } else if (text.startsWith("Comprovante de Recebimento") || text.startsWith("Comprovante de Pagamento")) {
            return 3;
        } else {
            return 2;
        }
    }

    //RENOMEIA O ARQUIVO
    private static final String novoNome = "comprovante";
    public static void renameFiles() {
        File diretorio = new File(directory());
            File[] arquivos = diretorio.listFiles();
        int contadorComprovante = 0;
        if (arquivos != null) {
            for (int i = 0; i < arquivos.length; i++) {
                File arquivo = arquivos[i];
                if (arquivo.isFile()) {
                    String nomeComprovanteTemporario = novoNome + " " + i + ".pdf";
                    File novoArquivo = new File(arquivo.getParent(), nomeComprovanteTemporario);

                    int contador = 2;
                    //TRANSFORMA NOME DO ARQUIVO EM "COMPROVANTE (i)"
                    while (novoArquivo.exists()) {
                        String nomeSemExtensao = nomeComprovanteTemporario.substring(0, nomeComprovanteTemporario.lastIndexOf('.'));
                        String extensao = nomeComprovanteTemporario.substring(nomeComprovanteTemporario.lastIndexOf('.'));
                        nomeComprovanteTemporario = nomeSemExtensao + " (" + contador + ")" + extensao;
                        novoArquivo = new File(arquivo.getParent(), nomeComprovanteTemporario);
                        contador++;
                    }

                    boolean renomeadoComSucesso = arquivo.renameTo(novoArquivo);

                    if (renomeadoComSucesso) {
                        String nomeComInfos =  directory() + nomeComprovanteTemporario;
                        String nomeFinalRenomeado = ""; 
                        try {
                            int fileType = fileInfos.fileType(novoArquivo.getPath());
                            if (fileType == 1) {
                                nomeFinalRenomeado = pegarInfosTipo1(nomeComInfos);
                            } else if (fileType == 2) {
                                nomeFinalRenomeado = pegarInfosTipo2(nomeComInfos);
                            }
                            else if (fileType == 3) {
                                nomeFinalRenomeado = pegarInfosTipo3(nomeComInfos);
                            }
                            File arquivoFinal = new File(directory(), nomeFinalRenomeado);
                            System.out.println("Arquivo " + nomeComprovanteTemporario + " renomeado com sucesso para " + nomeFinalRenomeado);
                            novoArquivo.renameTo(arquivoFinal);
                            contadorComprovante++;
                        } catch (Exception e) {
                            System.out.println("Algo deu errado" + nomeComprovanteTemporario + ": " + e.getMessage());
                        }
                    } else {
                        System.out.println("Falha ao renomear o arquivo " + arquivo.getName());
                    }
                }
            }
        }
        System.out.println();
        System.out.println(contadorComprovante + " Comprovantes foram renomeados! Operação concluída.");
    }
}
    