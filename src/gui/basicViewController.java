package gui;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import Model.Calculate;

import java.net.URL;
import java.util.ResourceBundle;

public class basicViewController implements Initializable {
    @FXML
    private TextField display; // Campo de texto para exibir números e resultados

    @FXML
    private TextArea history; // Campo de texto para exibir o histórico das operações

    private String currentOperator = ""; // Armazena o operador atual (+, -, *, /)
    private double firstNumber = 0; // Armazena o primeiro número digitado
    private boolean start = true; // Indica se é o início de uma nova operação

    private Calculate model = new Calculate(); // Instância do modelo

    @FXML
    private void handleButtonClick(javafx.event.ActionEvent event) {
        // Obtém o botão que foi clicado
        Button button = (Button) event.getSource();
        // Obtém o texto do botão
        String buttonText = button.getText();

        // Verifica se o botão clicado é um número
        if ("0123456789.".contains(buttonText)) {
            // Se for o início de uma nova operação, limpa o display
            if (start) {
                display.setText("");
                start = false;
            }
            // Adiciona o número ao display
            display.setText(display.getText() + buttonText);
        } else if (buttonText.equals("C")) {
            // Se o botão "C" for clicado, limpa o display e reseta as variáveis
            display.setText("");
            start = true;
            firstNumber = 0;
            currentOperator = "";
        } else if (buttonText.equals("=")) {
            // Se o botão "=" for clicado, realiza a operação
            if (!currentOperator.isEmpty()) {
                double secondNumber = Double.parseDouble(display.getText());
                double result = model.calculate(firstNumber, secondNumber, currentOperator);

                display.setText(String.valueOf(result));

                // Adiciona a operação ao histórico
                String operation = firstNumber + " " + currentOperator + " " + secondNumber + " = " + result;
                history.appendText(operation + "\n");

                start = true;
                currentOperator = "";
            }
        } else {
            // Se um operador (+, -, X, /) for clicado, armazena o primeiro número e o operador
            firstNumber = Double.parseDouble(display.getText());
            currentOperator = buttonText;
            start = true;
        }
    }

    @Override
    public void initialize(URL uri, ResourceBundle rb) {
        Constraints.setTextFieldDouble(display);
    }
}
