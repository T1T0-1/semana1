/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mergesortguiprojec;

/**
 *
 * @author LAB-USR-AREQUIPA
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MergeSortGUI extends JFrame {
    private int[] array = {38, 27, 43, 3, 9, 82, 10};
    private final JLabel originalLabel;
    private JLabel sortedLabel;
    private JButton sortButton;

    public MergeSortGUI() {
        super("Merge Sort Visualizador");
        setLayout(new FlowLayout());

        originalLabel = new JLabel("Arreglo original: " + arrayToString(array));
        sortedLabel = new JLabel("Arreglo ordenado: ");
        sortButton = new JButton("Ordenar");

        add(originalLabel);
        add(sortButton);
        add(sortedLabel);

        sortButton.addActionListener((ActionEvent e) -> {
            mergeSort(array, 0, array.length - 1);
            sortedLabel.setText("Arreglo ordenado: " + arrayToString(array));
            sortButton.setEnabled(false);  // deshabilitar botón después de ordenar
        });

        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num).append(" ");
        }
        return sb.toString();
    }

    // Merge Sort
    public void mergeSort(int[] arr, int izquierda, int derecha) {
        if (izquierda < derecha) {
            int medio = (izquierda + derecha) / 2;
            mergeSort(arr, izquierda, medio);
            mergeSort(arr, medio + 1, derecha);
            merge(arr, izquierda, medio, derecha);
        }
    }

    public void merge(int[] arr, int izquierda, int medio, int derecha) {
        int n1 = medio - izquierda + 1;
        int n2 = derecha - medio;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[izquierda + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[medio + 1 + j];

        int i = 0, j = 0, k = izquierda;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MergeSortGUI().setVisible(true);
        });
    }
}