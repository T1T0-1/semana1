/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mergesort;

/**
 *
 * @author Familia
 */
public class MergeSort {
    // Método principal para probar MergeSort
    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10};

        System.out.println("Arreglo original:");
        imprimirArreglo(array);

        mergeSort(array, 0, array.length - 1);

        System.out.println("\nArreglo ordenado:");
        imprimirArreglo(array);
    }

    // Implementación de MergeSort
    public static void mergeSort(int[] arr, int izquierda, int derecha) {
        if (izquierda < derecha) {
            int medio = (izquierda + derecha) / 2;

            // Ordenar primera mitad
            mergeSort(arr, izquierda, medio);

            // Ordenar segunda mitad
            mergeSort(arr, medio + 1, derecha);

            // Combinar ambas mitades
            merge(arr, izquierda, medio, derecha);
        }
    }

    // Función que combina dos mitades ordenadas
    public static void merge(int[] arr, int izquierda, int medio, int derecha) {
        int n1 = medio - izquierda + 1;
        int n2 = derecha - medio;

        // Arreglos temporales
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copiar datos
        for (int i = 0; i < n1; i++)
            L[i] = arr[izquierda + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[medio + 1 + j];

        // Índices iniciales
        int i = 0, j = 0;
        int k = izquierda;

        // Combinar arreglos
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

        // Copiar elementos restantes
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

    // Método para imprimir un arreglo
    public static void imprimirArreglo(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}