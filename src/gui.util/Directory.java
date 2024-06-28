package gui.util;

import java.io.File;

public class Directory {
    public static void directory (String directoryPath){

        // Crie um objeto File para o diretório
        File directory = new File(directoryPath);

        // Verifique se o diretório existe; se não, crie-o
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Diretório criado com sucesso!");
            } else {
                System.out.println("Falha ao criar o diretório.");
            }
        }
    }
}
