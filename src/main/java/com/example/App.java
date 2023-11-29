package com.example;
/* **************************************************************

Feito por Pedro Freitas
Como forma de estudos

Programa para renomear arquivos (comprovantes pdf) banco Sicredi
****************************************************************
 */
import static com.example.Types.fileInfos.renameFiles;
import static com.example.DataCSV.pegarDados;

import java.io.FileNotFoundException;;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        //renameFiles();
        pegarDados();
    }

    //DIRETÓRIO COMPROVANTES
    public static String directoryFiles() {
        String username = System.getProperty("user.name");
        return "C:\\Users\\" + username + "\\Desktop\\docsTeste\\";
    }
    //DIRETÓRIO DO ARQUIVO CSV
    public static String directoryCSV() {
        String username = System.getProperty("user.name");
        return "C:\\Users\\" + username + "\\Desktop\\projetoExcel\\relatorioCSVTeste.csv";
    }
}
