Claro, puedo ayudarte con un código básico en Java utilizando Swing para convertir entre dólares americanos, pesos colombianos y pesos mexicanos. El programa tendrá una interfaz gráfica simple con cuadros de texto, `JComboBox` para seleccionar la moneda, etiquetas y botones para calcular y reiniciar la conversión.

Aquí está el código de ejemplo:

```java
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
        setLayout(new GridLayout(5, 2, 10, 10));
        
        // Initialize components
        JLabel inputLabel = new JLabel("Amount:");
        inputField = new JTextField();
        
        JLabel fromLabel = new JLabel("From:");
        fromCurrency = new JComboBox<>(new String[]{"USD", "COP", "MXN"});
        
        JLabel toLabel = new JLabel("To:");
        toCurrency = new JComboBox<>(new String[]{"USD", "COP", "MXN"});
        
        JButton calculateButton = new JButton("Calculate");
        JButton clearButton = new JButton("Clear");
        
        JLabel resultLabel = new JLabel("Result:");
        resultField = new JTextField();
        resultField.setEditable(false);
        
        // Add components to frame
        add(inputLabel);
        add(inputField);
        add(fromLabel);
        add(fromCurrency);
        add(toLabel);
        add(toCurrency);
        add(calculateButton);
        add(clearButton);
        add(resultLabel);
        add(resultField);
        
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
```

### Explicación del Código

1. **Definición de Conversiones**: Se definen tasas de conversión para los diferentes pares de monedas. Estos valores son ejemplos y deberían actualizarse con las tasas actuales.

2. **Componentes de la Interfaz**:
   - **Cuadros de Texto** (`JTextField`): Para ingresar la cantidad y mostrar el resultado.
   - **Combo Boxes** (`JComboBox`): Para seleccionar la moneda de origen y destino.
   - **Botones** (`JButton`): Para calcular la conversión y borrar los campos.

3. **Listeners**:
   - **CalculateButtonListener**: Realiza la conversión basándose en la moneda seleccionada.
   - **ClearButtonListener**: Limpia todos los campos.

4. **Método de Conversión**: Convierte la cantidad de una moneda a otra utilizando tasas de conversión.

Este código crea una aplicación simple pero funcional para convertir entre dólares americanos, pesos colombianos y pesos mexicanos. Puedes ajustar las tasas de conversión y mejorar la interfaz según tus necesidades.
