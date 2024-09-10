import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Temporizador2 extends JFrame {

    Timer timer;
    TimerTask timertask;
    Boolean funcionando = false;

    private JButton jButton1;
    private JLabel jLabel1;

    public Temporizador2() {
        initComponents();
    }

    private void initComponents() {
        jButton1 = new JButton("Iniciar");
        jButton1.setSize(70, 25);
        jLabel1 = new JLabel("Texto en movimiento");

        // Añadir ActionListener al botón
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        // Añadir componentes al JFrame
        setLayout(null); // Se establece un layout nulo para manejar posiciones manualmente
        jButton1.setBounds(150, 150, 100, 25); // Se establece una posición fija para el botón
        jLabel1.setBounds(50, 50, 150, 30); // Se establece una posición fija para el JLabel
        add(jButton1);
        add(jLabel1);

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        if (!funcionando) {
            timer = new Timer();
            timertask = new TimerTask() {
                int i = 5;

                @Override
                public void run() {
                    // Mueve el JLabel en la coordenada x mientras mantiene la y constante
                    jLabel1.setLocation(i, jLabel1.getY());
                    i++;
                }
            };
            timer.scheduleAtFixedRate(timertask, 0, 100); // Ejecuta el código del TimerTask cada 100ms
            funcionando = true;
            jButton1.setText("Detener");
        } else {
            if (timer != null) {
                timer.cancel(); // Cancela el Timer
            }
            funcionando = false;
            jButton1.setText("Iniciar");
        }
    }

    public static void main(String args[]) {
        // Crear y mostrar la ventana
        new Temporizador2();
    }
}