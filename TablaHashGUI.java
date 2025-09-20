import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

class Entrada {
    int clave;
    int valor;

    public Entrada(int clave, int valor) {
        this.clave = clave;
        this.valor = valor;
    }
}

class TablaHash {
    private final LinkedList<Entrada>[] tabla;
    private final int tamaño;

    @SuppressWarnings("unchecked")
    public TablaHash(int tamaño) {
        this.tamaño = tamaño;
        tabla = new LinkedList[tamaño];
        for (int i = 0; i < tamaño; i++) {
            tabla[i] = new LinkedList<>();
        }
    }

    // Función hash
    private int hash(int clave) {
        return clave % tamaño;
    }

    // Insertar (clave, valor)
    public void insertar(int clave, int valor) {
        int indice = hash(clave);
        for (Entrada entrada : tabla[indice]) {
            if (entrada.clave == clave) {
                entrada.valor = valor; // Actualiza si ya existe
                return;
            }
        }
        tabla[indice].add(new Entrada(clave, valor));
    }

    // Buscar valor por clave
    public Integer buscar(int clave) {
        int indice = hash(clave);
        for (Entrada entrada : tabla[indice]) {
            if (entrada.clave == clave) {
                return entrada.valor;
            }
        }
        return null; // No encontrado
    }

    // Eliminar por clave
    public boolean eliminar(int clave) {
        int indice = hash(clave);
        for (Entrada entrada : tabla[indice]) {
            if (entrada.clave == clave) {
                tabla[indice].remove(entrada);
                return true;
            }
        }
        return false;
    }

    // Mostrar tabla hash
    public String mostrar() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamaño; i++) {
            sb.append("[").append(i).append("]: ");
            for (Entrada entrada : tabla[i]) {
                sb.append("(").append(entrada.clave).append(" -> ").append(entrada.valor).append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

public class TablaHashGUI {
    private static TablaHash hash;

    public static void main(String[] args) {
        hash = new TablaHash(5); // tabla de tamaño 5

        // Crear ventana JFrame
        JFrame frame = new JFrame("Tabla Hash");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        // Crear área de texto para mostrar la tabla
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Panel para los controles
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // Campos de texto para clave y valor
        JTextField claveField = new JTextField();
        JTextField valorField = new JTextField();
        panel.add(new JLabel("Clave:"));
        panel.add(claveField);
        panel.add(new JLabel("Valor:"));
        panel.add(valorField);

        // Botones
        JButton insertarBtn = new JButton("Insertar");
        JButton buscarBtn = new JButton("Buscar");
        JButton eliminarBtn = new JButton("Eliminar");
        panel.add(insertarBtn);
        panel.add(buscarBtn);
        panel.add(eliminarBtn);
        frame.add(panel, BorderLayout.NORTH);

        // Acción del botón "Insertar"
        insertarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int clave = Integer.parseInt(claveField.getText());
                    int valor = Integer.parseInt(valorField.getText());
                    hash.insertar(clave, valor);
                    textArea.setText("Tabla Hash después de insertar:\n" + hash.mostrar());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingresa valores válidos para clave y valor.");
                }
            }
        });

        // Acción del botón "Buscar"
        buscarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int clave = Integer.parseInt(claveField.getText());
                    Integer valor = hash.buscar(clave);
                    if (valor != null) {
                        textArea.setText("Valor encontrado: " + valor);
                    } else {
                        textArea.setText("Clave no encontrada.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingresa una clave válida.");
                }
            }
        });

        // Acción del botón "Eliminar"
        eliminarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int clave = Integer.parseInt(claveField.getText());
                    boolean eliminado = hash.eliminar(clave);
                    if (eliminado) {
                        textArea.setText("Clave " + clave + " eliminada.\n" + hash.mostrar());
                    } else {
                        textArea.setText("Clave no encontrada para eliminar.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingresa una clave válida.");
                }
            }
        });

        // Mostrar la ventana
        frame.setVisible(true);
    }
}