package gui.util;

import javafx.scene.control.TextField;

public class Constraints {
    // Método estático que configura um TextField para aceitar apenas valores double
    public static void setTextFieldDouble(TextField txt) {
        // Adiciona um listener para a propriedade de texto do TextField
        txt.textProperty().addListener((obs, oldValue, newValue) -> {
            // Verifica se o novo valor não é nulo e se não corresponde ao padrão de um número double
            if (newValue != null && !newValue.matches("\\d*([\\.]\\d*)?")) {
                // Se o novo valor não é um número double, restaura o valor antigo
                txt.setText(oldValue);
            }
        });
    }
}
