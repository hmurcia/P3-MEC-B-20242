```
CARTAS QUE AGITAN LOS DADOS

   En este ejercicio se utiliza una baraja de cartas para simular el lanzamiento
   de un par de dados. Baraja las cartas, luego comienza a repartirlas boca
   arriba para formar la pila A. Detente tan pronto como aparezca una carta con
   un valor de 1 a 6. Este número representa el lanzamiento de un dado.

   Tan pronto como consigas un número de dado en la pila A, empieza a repartir
   una nueva pila B. De nuevo, detente tan pronto como aparezca una carta con un
   valor de 1 a 6. Esto representa el lanzamiento de otro dado. Suma los dos
   números, y escribe la suma en una hoja de papel. La suma se obtiene tan
   aleatoriamente como si hubieras tirado un par de dados.

   Después de registrar los resultados del primer "lanzamiento" de dados
   imaginarios, aparta los dos montones y repite el reparto en dos montones más,
   para obtener un segundo "lanzamiento" de dados. Escribe los resultados de
   este segundo "tiro" debajo del número anterior.

   Continúa haciendo "tiradas" de esta manera hasta que todo el mazo haya sido
   usado. Suma todos los "lanzamientos". Debido a que cada tiro fue tan
   aleatorio como un lanzamiento de dos dados, parece imposible que el profesor
   Picanumba pueda predecir la suma de todos los tiros.

   ¿Cuál es la suma final?

   Solución
   La suma de todos los dados "lanzados" es 84.
```

Para resolver el problema de "Cartas que agitan los dados" en Java usando Swing y aplicando conceptos de orientación a objetos, diseñaremos un programa que simule el proceso descrito. Aquí tienes una implementación detallada:

### Estructura del Proyecto

Las clases que necesitaremos son:

- `Carta`: para representar una carta de la baraja.
- `Baraja`: para manejar la baraja de cartas.
- `SimuladorDados`: para gestionar la lógica del juego y las tiradas.
- `VentanaPrincipal`: para la interfaz gráfica.

### 1. Clase `Carta`

```java
public class Carta {
    private int valor;

    public Carta(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
```

### 2. Clase `Baraja`

```java
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class Baraja {
    private List<Carta> cartas;

    public Baraja() {
        cartas = new ArrayList<>();
        // Crear 52 cartas del 1 al 13 (4 veces cada una)
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < 4; j++) { // Cuatro palos
                cartas.add(new Carta(i));
            }
        }
        barajar();
    }

    public void barajar() {
        Collections.shuffle(cartas);
    }

    public Carta repartirCarta() {
        if (!cartas.isEmpty()) {
            return cartas.remove(cartas.size() - 1); // Saca la última carta
        }
        return null; // No hay cartas
    }

    public boolean hayCartas() {
        return !cartas.isEmpty();
    }
}
```

### 3. Clase `SimuladorDados`

```java
public class SimuladorDados {
    private Baraja baraja;

    public SimuladorDados() {
        baraja = new Baraja();
    }

    public int lanzarDados() {
        int suma = 0;

        // Lanzar el primer dado
        suma += lanzarDado();
        // Lanzar el segundo dado
        suma += lanzarDado();

        return suma;
    }

    private int lanzarDado() {
        while (true) {
            Carta carta = baraja.repartirCarta();
            if (carta == null) {
                return 0; // No hay más cartas
            }
            int valor = carta.getValor();
            if (valor >= 1 && valor <= 6) {
                return valor; // Retornar el valor del dado
            }
        }
    }

    public boolean hayCartas() {
        return baraja.hayCartas();
    }
}
```

### 4. Clase `VentanaPrincipal`

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal {
    private SimuladorDados simulador;
    private JTextArea resultadosArea;

    public VentanaPrincipal() {
        simulador = new SimuladorDados();
        crearVentana();
    }

    private void crearVentana() {
        JFrame frame = new JFrame("Cartas que Agitan los Dados");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        resultadosArea = new JTextArea();
        resultadosArea.setEditable(false);
        frame.add(new JScrollPane(resultadosArea), BorderLayout.CENTER);

        JButton btnLanzar = new JButton("Lanzar Dados");
        btnLanzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLanzamientos();
            }
        });

        frame.add(btnLanzar, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void realizarLanzamientos() {
        resultadosArea.setText(""); // Limpiar resultados
        int sumaTotal = 0;

        while (simulador.hayCartas()) {
            int resultado = simulador.lanzarDados();
            resultadosArea.append("Resultado: " + resultado + "\n");
            sumaTotal += resultado;
        }

        resultadosArea.append("Suma Total: " + sumaTotal);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }
}
```

### Explicación del Código

1. **Carta**: Representa una carta con un valor entre 1 y 13.

2. **Baraja**: Maneja la creación y barajado de 52 cartas. Proporciona un método para repartir cartas y verifica si quedan cartas.

3. **SimuladorDados**: Contiene la lógica para simular el lanzamiento de los dados utilizando las cartas. Se encarga de contar la suma de los resultados.

4. **VentanaPrincipal**: Crea la interfaz gráfica donde el usuario puede lanzar los dados. Muestra los resultados de cada lanzamiento y la suma total.

### Ejecución

Al ejecutar el programa, se abrirá una ventana donde se puede hacer clic en "Lanzar Dados". Esto simulará el lanzamiento de dados y mostrará los resultados en la interfaz.
