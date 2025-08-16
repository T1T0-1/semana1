/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication10;

import java.util.Scanner;

/**
 *
 * @author LAB-USR-AREQUIPA
 */
public class JavaApplication10 {

    /**
     * @param lista
     * @param elemento
     * @return 
     */
    public static boolean buscarElemento(int [] lista,int elemento){
        for (int i : lista){
            if (i == elemento){
                return true;
            }
        }
        return false;
    }
     public static void main(String[] args) {
        // TODO code application logic here
         System.out.println("ingrese el numero a buscar en la base");
        Scanner numero = new Scanner (System.in);
        int [] arreglo = {10,15,20,30}; 
        int elemento = numero.nextInt ();
        boolean encontrar = buscarElemento (arreglo,elemento);
        System.out.println("El elemento " + elemento );
        if (encontrar) {
            System.out.println("si se encuentra");}
        else {
            System.out.println("no se encontro");
        }
     }
     }

