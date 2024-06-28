package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import java.io.IOException;

import Model.propertyReport_Sqlite;

public class Main extends Application {
    // Declaração de uma variável estática para armazenar a cena principal
    private static Scene mainScene;
    @Override
    public void start(Stage primaryStage) {
        try {
            // Cria um FXMLLoader para carregar o arquivo FXML especificado
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
            // Carrega o arquivo FXML em um ScrollPane
            ScrollPane scrollPane = loader.load();
            // Configura o ScrollPane para ajustar automaticamente seu tamanho à altura e largura do conteúdo
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            // Cria uma nova cena usando o ScrollPane como raiz
            mainScene = new Scene(scrollPane);
            // Define a cena principal no palco (janela) principal
            primaryStage.setScene(mainScene);
            // Define o título da janela principal
            primaryStage.setTitle("calculadora");
            // Exibe a janela principal
            primaryStage.show();
        } catch (IOException e) {
            // Em caso de erro ao carregar o arquivo FXML, imprime o stack trace do erro
            e.printStackTrace();
        }
    }

    public static Scene getMainScene(){
        return mainScene;
    }

    public static void main(String[] args) {
        launch(args);
        propertyReport_Sqlite.createTable(); // Chama o método para criar a tabela 'calculate', se necessário


    }
}