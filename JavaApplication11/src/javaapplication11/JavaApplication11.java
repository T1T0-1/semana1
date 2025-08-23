/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication11;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author LAB-USR-AREQUIPA
 */
public class JavaApplication11 {

    /**
     * @param lista
     * @param elemento
     * @return 
     */
     // Método para buscar un elemento en el arreglo
      public static boolean buscarElemento(int [] lista,int elemento){
        for (int i : lista){
            if (i == elemento){
                return true;
            }
        }
        return false;
    }
     public static void main(String[] args) {
           
        
         // Método para buscar un elemento en el arreglo
        Scanner scanner = new Scanner (System.in);
        System.out.println("Cuantos Numeros desea ingresar");
         int cantidad = scanner.nextInt();
         
          // Método para buscar un elemento en el arreglo
         int[] arreglo = new int[cantidad];
         
          // Método para buscar un elemento en el arreglo
            System.out.println("Ingrese los " + cantidad + " números:");
        for (int i = 0; i < cantidad; i++) {
            System.out.print("Elemento " + (i + 1) + ": ");
            arreglo[i] = scanner.nextInt();
        }
        
         // Método para buscar un elemento en el arreglo
        System.out.println("\nArreglo original:");
        System.out.println(Arrays.toString(arreglo));
        
         // Método para buscar un elemento en el arreglo
        Arrays.sort(arreglo);
        
        
         // Método para buscar un elemento en el arreglo
        System.out.println("Arreglo ordenado:");
        System.out.println(Arrays.toString(arreglo));
        
        
         // Método para buscar un elemento en el arreglo
        System.out.println("\nIngrese el número a buscar en la base:");
        int elemento = scanner.nextInt();
        
        
         // Método para buscar un elemento en el arreglo
        boolean encontrar = buscarElemento(arreglo, elemento);
        
        
        
         // Método para buscar un elemento en el arreglo
         System.out.println("\nEl elemento " + elemento);
        if (encontrar) {
            System.out.println("Sí se encuentra.");
        } else {
            System.out.println("No se encontró.");
        }

        scanner.close();
        }
}
        
         //System.out.println("ingrese el numero a buscar en la base");
        //Scanner numero = new Scanner (System.in);
        //int [] arreglo = {10,15,20,30}; 
        //int elemento = numero.nextInt ();
        //boolean encontrar = buscarElemento (arreglo,elemento);
        //System.out.println("El elemento " + elemento );
        //if (encontrar) {
        //    System.out.println("si se encuentra");}
       // else {
        //    System.out.println("no se encontro");
      //  }
    // }
     
