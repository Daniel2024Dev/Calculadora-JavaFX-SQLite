package Model;

public class Calculate {
    // Realiza a operação com base no operador fornecido
    public double calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "x":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("Divisão por zero");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Operador inválido: " + operator);
        }
    }
}
