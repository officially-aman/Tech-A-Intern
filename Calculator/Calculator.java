import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField displayField;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton equalsButton;
    private JButton clearButton;
    private String currentInput;
    private double result;
    private char operation;

    Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setBounds(250, 150, 100, 50);
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(Integer.toString(i));
            numberButtons[i].addActionListener(this);
            buttonPanel.add(numberButtons[i]);
        }

        operationButtons = new JButton[4];
        operationButtons[0] = new JButton("+");
        operationButtons[1] = new JButton("-");
        operationButtons[2] = new JButton("*");
        operationButtons[3] = new JButton("/");
        for (int i = 0; i < 4; i++) {
            operationButtons[i].addActionListener(this);
            buttonPanel.add(operationButtons[i]);
        }

        equalsButton = new JButton("=");
        equalsButton.addActionListener(this);
        clearButton = new JButton("C");
        clearButton.addActionListener(this);

        buttonPanel.add(clearButton);
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(equalsButton);

        add(buttonPanel, BorderLayout.CENTER);

        currentInput = "";
        result = 0;
        operation = ' ';
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            currentInput += command;
            displayField.setText(currentInput);
        } else if (command.charAt(0) == 'C') {
            currentInput = "";
            result = 0;
            operation = ' ';
            displayField.setText("");
        } else if (command.charAt(0) == '=') {
            double input = Double.parseDouble(currentInput);
            switch (operation) {
                case '+':
                    result += input;
                    break;
                case '-':
                    result -= input;
                    break;
                case '*':
                    result *= input;
                    break;
                case '/':
                    if (input != 0) {
                        result /= input;
                    } else {
                        displayField.setText("Error: Division by zero");
                        return;
                    }
                    break;
                default:
                    result = input;
            }
            displayField.setText(Double.toString(result));
            currentInput = "";
            operation = ' ';
        } else {
            if (!currentInput.isEmpty()) {
                result = Double.parseDouble(currentInput);
                operation = command.charAt(0);
                currentInput = "";
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}
