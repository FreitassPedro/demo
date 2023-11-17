package com.example;
/* **************************************************************

Feito por Pedro Freitas
Como forma de estudos

Programa para renomear arquivos (comprovantes pdf) banco Sicredi
****************************************************************
 */
//import static com.example.Types.fileInfos.renameFiles;
import static com.example.DataCSV.pegarDados;

import java.io.FileNotFoundException;;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        //renameFiles();
        pegarDados();
    }

    //DIRETÓRIOS
    public static String directoryFiles() {
        String username = System.getProperty("user.name");
        return "C:\\Users\\" + username + "\\Desktop\\docs\\";
    }
    public static String directoryCSV() {
        String username = System.getProperty("user.name");
        return "C:\\Users\\" + username + "\\Desktop\\projetoExcel\\relatorioCSV2.csv";
    }
}
