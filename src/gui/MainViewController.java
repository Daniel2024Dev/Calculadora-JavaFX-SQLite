package gui;

import Application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainViewController {

    @FXML
    private MenuItem MenuItemCalculate;

    @FXML
    public void onMenuItemCalculateAction(){
        loadView("/gui/basicView.fxml");
    }


    @FXML
    private MenuItem MenuItemAbout;

    @FXML
    public void onMenuItemAboutAction(){
        loadView("/gui/About.fxml");
    }

    private synchronized void loadView(String absoluteName){
        try {
            // Cria um objeto FXMLLoader para carregar o layout a partir de um arquivo FXML.
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            // Carrega o conteúdo do arquivo FXML em um novo VBox.
            VBox newVBox = loader.load();
            // Obtém a cena principal da aplicação.
            Scene mainScene = Main.getMainScene();
            // Obtém o VBox principal da cena, que está contido dentro de um ScrollPane.
            VBox mainVBox = (VBox) ((ScrollPane)mainScene.getRoot()).getContent();
            // Obtém o primeiro item do VBox principal, que presumivelmente é o menu principal.
            Node mainMenu = mainVBox.getChildren().get(0);
            // Limpa todos os filhos do VBox principal.
            mainVBox.getChildren().clear();
            // Adiciona o menu principal de volta ao VBox principal.
            mainVBox.getChildren().add(mainMenu);
            // Adiciona todos os filhos do novo VBox carregado ao VBox principal.
            mainVBox.getChildren().addAll(newVBox.getChildren());


        }
        catch (IOException e){
            // Mostra um alerta em caso de exceção de entrada/saída (IOException) ao carregar a nova visualização.
            Alerts.showAlert("IOException","Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

}

