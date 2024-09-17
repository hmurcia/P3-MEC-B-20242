import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverter extends JFrame {

    // Constants for conversion rates (example rates, should be updated with current values)
    private static final double USD_TO_COP = 4100; // 1 USD to COP
    private static final double USD_TO_MXN = 18;   // 1 USD to MXN
    private static final double COP_TO_USD = 1 / USD_TO_COP; // 1 COP to USD
    private static final double MXN_TO_USD = 1 / USD_TO_MXN; // 1 MXN to USD

    // UI components
    private JTextField inputField;
    private JTextField resultField;
    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;

    public CurrencyConverter() {
        // Set up frame
        setTitle("Currency Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // Use absolute positioning

        // Create and set up panels
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(10, 10, 370, 250);
        panel.setBorder(BorderFactory.createTitledBorder("Currency Converter"));

        // Create and position components
        JLabel inputLabel = new JLabel("Amount:");
        inputLabel.setBounds(10, 30, 80, 25);
        panel.add(inputLabel);

        inputField = new JTextField();
        inputField.setBounds(100, 30, 150, 25);
        panel.add(inputField);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setBounds(10, 70, 80, 25);
        panel.add(fromLabel);

        fromCurrency = new JComboBox<>(new String[]{"USD", "COP", "MXN"});
        fromCurrency.setBounds(100, 70, 150, 25);
        panel.add(fromCurrency);

        JLabel toLabel = new JLabel("To:");
        toLabel.setBounds(10, 110, 80, 25);
        panel.add(toLabel);

        toCurrency = new JComboBox<>(new String[]{"USD", "COP", "MXN"});
        toCurrency.setBounds(100, 110, 150, 25);
        panel.add(toCurrency);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(10, 150, 120, 25);
        panel.add(calculateButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(150, 150, 120, 25);
        panel.add(clearButton);

        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setBounds(10, 190, 80, 25);
        panel.add(resultLabel);

        resultField = new JTextField();
        resultField.setBounds(100, 190, 150, 25);
        resultField.setEditable(false);
        panel.add(resultField);

        // Add panel to frame
        add(panel);

        // Add action listeners
        calculateButton.addActionListener(new CalculateButtonListener());
        clearButton.addActionListener(new ClearButtonListener());

        setVisible(true);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double amount = Double.parseDouble(inputField.getText());
                String from = (String) fromCurrency.getSelectedItem();
                String to = (String) toCurrency.getSelectedItem();
                double result = convert(amount, from, to);
                resultField.setText(String.format("%.2f", result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            }
        }

        private double convert(double amount, String from, String to) {
            if (from.equals(to)) {
                return amount;
            }

            double amountInUSD = 0;
            switch (from) {
                case "USD":
                    amountInUSD = amount;
                    break;
                case "COP":
                    amountInUSD = amount * COP_TO_USD;
                    break;
                case "MXN":
                    amountInUSD = amount * MXN_TO_USD;
                    break;
            }

            switch (to) {
                case "USD":
                    return amountInUSD;
                case "COP":
                    return amountInUSD * USD_TO_COP;
                case "MXN":
                    return amountInUSD * USD_TO_MXN;
                default:
                    throw new IllegalArgumentException("Unexpected value: " + to);
            }
        }
    }

    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            inputField.setText("");
            resultField.setText("");
            fromCurrency.setSelectedIndex(0);
            toCurrency.setSelectedIndex(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyConverter());
    }
}
