/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication15;

import java.util.Arrays;
import java.util.Scanner;
 * @author Familia
 */
public class JavaApplication15 {

 public static boolean buscarElemento(int [] lista,int elemento){
        for (int i : lista){
            if (i == elemento){
                return true;
            }
        }
        return false;
    }
     public static void main(String[] args) {
           
        
     try ( // Método para buscar un elemento en el arreglo
             Scanner scanner = new Scanner (System.in)) {
         System.out.println("Cuantos Numeros desea ingresar");
         int cantidad = scanner.nextInt();
         
        
         int[] arreglo = new int[cantidad];
         
         
         System.out.println("Ingrese los " + cantidad + " números:");
         for (int i = 0; i < cantidad; i++) {
             System.out.print("Elemento " + (i + 1) + ": ");
             arreglo[i] = scanner.nextInt();
         }
         
         System.out.println("\nArreglo original:");
         System.out.println(Arrays.toString(arreglo))
         
         Arrays.sort(arreglo);
         
         
         System.out.println("Arreglo ordenado:");
         System.out.println(Arrays.toString(arreglo));
         
         System.out.println("\nIngrese el número a buscar en la base:");
         int elemento = scanner.nextInt();
         
         
         boolean encontrar = buscarElemento(arreglo, elemento);
         
         
         System.out.println("\nEl elemento " + elemento);
         if (encontrar) {
             System.out.println("Sí se encuentra.");
         } else {
             System.out.println("No se encontró.");
         }
     }
        }
}

