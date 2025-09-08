/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package polifa;

/**
 *
 * @author Familia
 */
public class Polifa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        public class Polifasico {

    public static void main(String[] args) throws IOException {
        String archivoEntrada = "datos.txt";
        String archivoSalida = "ordenado.txt";

        // Crear un archivo de prueba con números desordenados
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoEntrada))) {
            writer.write("38 27 43 3 9 82 10 5 14 20 1");
        }

        // Ejecutar el método polifásico
        polifasico(archivoEntrada, archivoSalida);

        // Mostrar el resultado
        System.out.println("Archivo ordenado:");
        try (BufferedReader br = new BufferedReader(new FileReader(archivoSalida))) {
            System.out.println(br.readLine());
        }
    }
        // TODO code application logic here
    }
    
}
