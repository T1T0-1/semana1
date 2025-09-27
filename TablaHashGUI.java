package tabla;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
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

    private int hash(int clave) {
        return clave % tamaño;
    }

    public void insertar(int clave, int valor) {
        int indice = hash(clave);
        for (Entrada entrada : tabla[indice]) {
            if (entrada.clave == clave) {
                entrada.valor = valor; // actualizar si existe
                return;
            }
        }
        tabla[indice].add(new Entrada(clave, valor));
    }

    public Integer buscar(int clave) {
        int indice = hash(clave);
        for (Entrada entrada : tabla[indice]) {
            if (entrada.clave == clave) {
                return entrada.valor;
            }
        }
        return null;
    }

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

    // 🔹 Getters para recorrer toda la tabla desde ArchivoBD
    public int getTamaño() {
        return tamaño;
    }

    public LinkedList<Entrada> getLista(int indice) {
        return tabla[indice];
    }
}

class ArchivoBD {
    private static final String FILE = "datos.txt";

    // Guardar toda la tabla hash en el archivo
    public static void guardarTabla(TablaHash hash) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
            for (int i = 0; i < hash.getTamaño(); i++) {
                for (Entrada entrada : hash.getLista(i)) {
                    bw.write(entrada.clave + "," + entrada.valor);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cargar datos desde archivo
    public static void cargarTabla(TablaHash hash) {
        File f = new File(FILE);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                int clave = Integer.parseInt(partes[0]);
                int valor = Integer.parseInt(partes[1]);
                hash.insertar(clave, valor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class TablaHashGUI {
    private static TablaHash hash;

    public static void main(String[] args) {
        hash = new TablaHash(5); // tamaño de la tabla hash

        // Al iniciar, cargar datos guardados
        ArchivoBD.cargarTabla(hash);

        JFrame frame = new JFrame("Tabla Hash con archivo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JTextField claveField = new JTextField();
        JTextField valorField = new JTextField();
        panel.add(new JLabel("Clave:"));
        panel.add(claveField);
        panel.add(new JLabel("Valor:"));
        panel.add(valorField);

        JButton insertarBtn = new JButton("Insertar");
        JButton buscarBtn = new JButton("Buscar");
        JButton eliminarBtn = new JButton("Eliminar");
        panel.add(insertarBtn);
        panel.add(buscarBtn);
        panel.add(eliminarBtn);
        frame.add(panel, BorderLayout.NORTH);

        // Botón Insertar
        insertarBtn.addActionListener((ActionEvent e) -> {
            try {
                int clave = Integer.parseInt(claveField.getText());
                int valor = Integer.parseInt(valorField.getText());
                hash.insertar(clave, valor);
                ArchivoBD.guardarTabla(hash); // Guardar cambios
                textArea.setText("Tabla Hash después de insertar:\n" + hash.mostrar());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Por favor, ingresa valores válidos.");
            }
        });

        // Botón Buscar
        buscarBtn.addActionListener((ActionEvent e) -> {
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
        });

        // Botón Eliminar
        eliminarBtn.addActionListener((ActionEvent e) -> {
            try {
                int clave = Integer.parseInt(claveField.getText());
                boolean eliminado = hash.eliminar(clave);
                if (eliminado) {
                    ArchivoBD.guardarTabla(hash); // Guardar cambios
                    textArea.setText("Clave " + clave + " eliminada.\n" + hash.mostrar());
                } else {
                    textArea.setText("Clave no encontrada.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Por favor, ingresa una clave válida.");
            }
        });

        // Mostrar ventana
        textArea.setText("Tabla Hash inicial:\n" + hash.mostrar());
        frame.setVisible(true);
    }
}