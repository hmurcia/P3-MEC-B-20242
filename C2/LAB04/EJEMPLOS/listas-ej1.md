Para resolver un enunciado utilizando Java Swing y aplicando la orientación a objetos, es importante estructurar el programa de forma clara y modular. Aquí tenemos un ejemplo de una aplicación simple de gestión de contactos, que ilustra cómo aplicar la orientación a objetos y crear métodos puros.

### Ejemplo: Gestor de Contactos

#### 1. Estructura del proyecto

Vamos a crear las siguientes clases:

- `Contacto`: para representar un contacto.
- `GestorContactos`: para manejar la lista de contactos.
- `VentanaPrincipal`: para la interfaz gráfica utilizando Swing.

#### 2. Clase `Contacto`

```java
public class Contacto {
    private String nombre;
    private String telefono;

    public Contacto(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return nombre + " - " + telefono;
    }
}
```

#### 3. Clase `GestorContactos`

```java
import java.util.ArrayList;
import java.util.List;

public class GestorContactos {
    private List<Contacto> contactos;

    public GestorContactos() {
        this.contactos = new ArrayList<>();
    }

    public void agregarContacto(Contacto contacto) {
        contactos.add(contacto);
    }

    public List<Contacto> obtenerContactos() {
        return new ArrayList<>(contactos); // Retornar una copia para evitar modificaciones externas
    }

    public void eliminarContacto(Contacto contacto) {
        contactos.remove(contacto);
    }
}
```

#### 4. Clase `VentanaPrincipal`

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal {
    private GestorContactos gestor;
    private DefaultListModel<Contacto> modeloLista;
    private JList<Contacto> listaContactos;
    private JTextField campoNombre;
    private JTextField campoTelefono;

    public VentanaPrincipal() {
        gestor = new GestorContactos();
        modeloLista = new DefaultListModel<>();
        crearVentana();
    }

    private void crearVentana() {
        JFrame frame = new JFrame("Gestor de Contactos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        listaContactos = new JList<>(modeloLista);
        frame.add(new JScrollPane(listaContactos), BorderLayout.CENTER);

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(3, 2));

        panelFormulario.add(new JLabel("Nombre:"));
        campoNombre = new JTextField();
        panelFormulario.add(campoNombre);

        panelFormulario.add(new JLabel("Teléfono:"));
        campoTelefono = new JTextField();
        panelFormulario.add(campoTelefono);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarContacto();
            }
        });

        panelFormulario.add(btnAgregar);
        frame.add(panelFormulario, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void agregarContacto() {
        String nombre = campoNombre.getText().trim();
        String telefono = campoTelefono.getText().trim();
        if (!nombre.isEmpty() && !telefono.isEmpty()) {
            Contacto nuevoContacto = new Contacto(nombre, telefono);
            gestor.agregarContacto(nuevoContacto);
            modeloLista.addElement(nuevoContacto);
            campoNombre.setText("");
            campoTelefono.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal());
    }
}
```

### Explicación

1. **Contacto**: Clase simple que encapsula los datos de un contacto. Contiene métodos de acceso (getters) y un método `toString` para representar el contacto en una lista.

2. **GestorContactos**: Maneja una lista de contactos. Tiene métodos para agregar, eliminar y obtener contactos. Devuelve una copia de la lista para mantener la encapsulación.

3. **VentanaPrincipal**: La interfaz gráfica que permite a los usuarios agregar contactos a través de un formulario. Usa un `DefaultListModel` para manejar la lista de contactos que se muestra en la interfaz.

4. **Métodos Puros**: Se utilizan métodos que no alteran el estado del objeto (como `obtenerContactos` en `GestorContactos`) y encapsulan lógica dentro de métodos que manejan eventos, promoviendo la reutilización y la claridad del código.

Puede adaptarse este ejemplo según el enunciado específico que se tenga en mente.
