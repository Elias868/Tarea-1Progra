import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MultiFunctionCalculatorGUI extends JFrame {
    private ArrayList<String> history = new ArrayList<>();
    private JTextArea display;
    private JTextArea historyDisplay;

    public MultiFunctionCalculatorGUI() {
        setTitle("Calculadora Multifuncional");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);55
        setLayout(new BorderLayout());

        display = new JTextArea();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        add(display, BorderLayout.NORTH);

        historyDisplay = new JTextArea();
        historyDisplay.setEditable(false);
        historyDisplay.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(historyDisplay);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        add(panel, BorderLayout.SOUTH);

        JButton sumButton = new JButton("Sumar");
        sumButton.addActionListener(e -> performOperation("Sumar"));
        panel.add(sumButton);

        JButton subtractButton = new JButton("Restar");
        subtractButton.addActionListener(e -> performOperation("Restar"));
        panel.add(subtractButton);

        JButton multiplyButton = new JButton("Multiplicar");
        multiplyButton.addActionListener(e -> performOperation("Multiplicar"));
        panel.add(multiplyButton);

        JButton divideButton = new JButton("Dividir");
        divideButton.addActionListener(e -> performOperation("Dividir"));
        panel.add(divideButton);

        JButton historyButton = new JButton("Mostrar historial");
        historyButton.addActionListener(e -> showHistory());
        panel.add(historyButton);

        JButton resetButton = new JButton("Reiniciar calculadora");
        resetButton.addActionListener(e -> resetCalculator());
        panel.add(resetButton);

        JButton helpButton = new JButton("Ayuda");
        helpButton.addActionListener(e -> showHelp());
        panel.add(helpButton);

        JButton exitButton = new JButton("Salir");
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton);
    }

    private void performOperation(String operation) {
        try {
            String input1 = JOptionPane.showInputDialog("Ingrese el primer número:");
            double num1 = Double.parseDouble(input1);
            String input2 = JOptionPane.showInputDialog("Ingrese el segundo número:");
            double num2 = Double.parseDouble(input2);
            double result = 0;

            switch (operation) {
                case "Sumar":
                    result = num1 + num2;
                    break;
                case "Restar":
                    result = num1 - num2;
                    break;
                case "Multiplicar":
                    result = num1 * num2;
                    break;
                case "Dividir":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        JOptionPane.showMessageDialog(this, "Error: No se puede dividir por cero.");
                        return;
                    }
                    break;
            }

            String resultString = "Resultado de " + operation + ": " + num1 + " y " + num2 + " = " + result;
            display.setText(resultString);
            history.add(resultString);
            historyDisplay.append(resultString + "\n");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Entrada no válida.");
        }
    }

    private void showHistory() {
        if (history.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El historial está vacío.");
        } else {
            historyDisplay.setText("Historial de operaciones:\n");
            for (String entry : history) {
                historyDisplay.append(entry + "\n");
            }
        }
    }

    private void resetCalculator() {
        history.clear();
        display.setText("");
        historyDisplay.setText("");
        JOptionPane.showMessageDialog(this, "La calculadora ha sido reiniciada.");
    }

    private void showHelp() {
        String helpMessage = "Manual de ayuda:\n" +
                "1. Sumar: Ingrese dos números para obtener su suma.\n" +
                "2. Restar: Ingrese dos números para obtener su resta.\n" +
                "3. Multiplicar: Ingrese dos números para obtener su multiplicación.\n" +
                "4. Dividir: Ingrese dos números para obtener su división.\n" +
                "5. Mostrar historial: Muestra todas las operaciones realizadas.\n" +
                "6. Reiniciar calculadora: Limpia el historial de operaciones.\n" +
                "7. Ayuda: Muestra este manual de ayuda.\n" +
                "8. Salir: Cierra la calculadora.";
        JOptionPane.showMessageDialog(this, helpMessage);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MultiFunctionCalculatorGUI calculator = new MultiFunctionCalculatorGUI();
            calculator.setVisible(true);
        });
    }
}
