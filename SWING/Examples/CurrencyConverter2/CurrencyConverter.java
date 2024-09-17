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
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        
        // Input field
        JLabel inputLabel = new JLabel("Amount:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(inputLabel, gbc);

        inputField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(inputField, gbc);

        // From currency
        JLabel fromLabel = new JLabel("From:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(fromLabel, gbc);

        fromCurrency = new JComboBox<>(new String[]{"USD", "COP", "MXN"});
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(fromCurrency, gbc);

        // To currency
        JLabel toLabel = new JLabel("To:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(toLabel, gbc);

        toCurrency = new JComboBox<>(new String[]{"USD", "COP", "MXN"});
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(toCurrency, gbc);

        // Calculate button
        JButton calculateButton = new JButton("Calculate");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        add(calculateButton, gbc);

        // Clear button
        JButton clearButton = new JButton("Clear");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        add(clearButton, gbc);

        // Result field
        JLabel resultLabel = new JLabel("Result:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        add(resultLabel, gbc);

        resultField = new JTextField(15);
        resultField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        add(resultField, gbc);
        
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
