import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class ScientificCalculatorUI extends JFrame implements ActionListener {

    private JTextField display;
    private double num1 = 0, num2 = 0, result = 0;
    private String operator = "";

    public ScientificCalculatorUI() {

        setTitle("Scientific Calculator");
        setSize(400, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        display = new JTextField();
        display.setBounds(20, 20, 345, 50);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display);

        String buttons[] = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", ".", "=", "+",
            "sin", "cos", "tan",
            "log", "ln", "sqrt", "x^y",
            "C"
        };

        int x = 20, y = 90;

        for (int i = 0; i < buttons.length; i++) {
            JButton btn = new JButton(buttons[i]);
            btn.setBounds(x, y, 80, 50);
            btn.setFont(new Font("Arial", Font.BOLD, 14));
            btn.addActionListener(this);
            add(btn);

            x += 85;

            if ((i + 1) % 4 == 0) { 
                x = 20;
                y += 60;
            }
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String txt = e.getActionCommand();

        try {
            if (txt.matches("[0-9]") || txt.equals(".")) {
                display.setText(display.getText() + txt);
            }

            else if (txt.equals("C")) {
                display.setText("");
                num1 = num2 = result = 0;
                operator = "";
            }

            else if (txt.equals("+") || txt.equals("-") || txt.equals("*") || txt.equals("/") || txt.equals("x^y")) {
                num1 = Double.parseDouble(display.getText());
                operator = txt;
                display.setText("");
            }

            else if (txt.equals("=")) {
                num2 = Double.parseDouble(display.getText());

                switch (operator) {
                    case "+" -> result = num1 + num2;
                    case "-" -> result = num1 - num2;
                    case "*" -> result = num1 * num2;
                    case "/" -> result = num1 / num2;
                    case "x^y" -> result = Math.pow(num1, num2);
                }

                display.setText(String.valueOf(result));
            }

            // scientific functions
            else if (txt.equals("sin")) {
                result = Math.sin(Math.toRadians(Double.parseDouble(display.getText())));
                display.setText(format(result));
            }
            else if (txt.equals("cos")) {
                result = Math.cos(Math.toRadians(Double.parseDouble(display.getText())));
                display.setText(format(result));
            }
            else if (txt.equals("tan")) {
                result = Math.tan(Math.toRadians(Double.parseDouble(display.getText())));
                display.setText(format(result));
            }
            else if (txt.equals("log")) {
                result = Math.log10(Double.parseDouble(display.getText()));
                display.setText(format(result));
            }
            else if (txt.equals("ln")) {
                result = Math.log(Double.parseDouble(display.getText()));
                display.setText(format(result));
            }
            else if (txt.equals("sqrt")) {
                result = Math.sqrt(Double.parseDouble(display.getText()));
                display.setText(format(result));
            }

        } catch (Exception ex) {
            display.setText("Error");
        }
    }

    private String format(double value) {
        DecimalFormat df = new DecimalFormat("#.########");
        return df.format(value);
    }

    public static void main(String[] args) {
        new ScientificCalculatorUI();
    }
}
