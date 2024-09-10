import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Temporizador3 extends JFrame {

    private final Timer temporizador;
    private boolean funcionando = false;

    private JButton jButton1;
    private JRadioButton jRadioButton1;

    public Temporizador3() {
        initComponents();

        ActionListener al = new ActionListener() {
            int i = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                double x = 50 + 10 * Math.sin(2 * Math.PI / 10 * i);
                jRadioButton1.setLocation((int) x, jRadioButton1.getY());
                i++;
            }
        };

        temporizador = new Timer(100, al); // Cada 100ms se llama al ActionListener
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
    }

    private void initComponents() {
        // Crear los componentes
        jButton1 = new JButton("Iniciar");
        jRadioButton1 = new JRadioButton();

        // Configurar el panel de contenido
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null); // Layout nulo para usar setLocation
        contentPanel.add(jButton1);
        contentPanel.add(jRadioButton1);

        // Configurar el tamaño y la posición de los componentes
        jButton1.setBounds(200, 270, 80, 25);
        jRadioButton1.setBounds(50, 100, 20, 20); // Posición inicial del JRadioButton

        // Configurar el JFrame
        setContentPane(contentPanel);
        setSize(500, 360);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        if (!funcionando) {
            temporizador.start();
            jButton1.setText("Detener");
            funcionando = true;
        } else {
            temporizador.stop();
            jButton1.setText("Iniciar");
            funcionando = false;
        }
    }

    public static void main(String args[]) {
        // Crear y mostrar la ventana
        new Temporizador3();
    }
}