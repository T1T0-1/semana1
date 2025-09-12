import java.io.*;
import java.util.*;

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

    // Método polifásico de ordenación externa
    public static void polifasico(String archivoEntrada, String archivoSalida) throws IOException {
        // 1. Leer todos los datos del archivo original
        List<Integer> numeros = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(archivoEntrada))) {
            while (sc.hasNextInt()) {
                numeros.add(sc.nextInt());
            }
        }

        // 2. Crear "runs" iniciales (trozos ordenados en memoria)
        List<List<Integer>> runs = new ArrayList<>();
        int tamañoRun = 4; // simulamos que solo podemos manejar 4 números en memoria
        for (int i = 0; i < numeros.size(); i += tamañoRun) {
            List<Integer> run = new ArrayList<>(numeros.subList(i, Math.min(i + tamañoRun, numeros.size())));
            Collections.sort(run); // ordenar cada run en memoria
            runs.add(run);
        }

        // 3. Mezcla polifásica (iterativa)
        while (runs.size() > 1) {
            List<List<Integer>> nuevosRuns = new ArrayList<>();

            for (int i = 0; i < runs.size(); i += 2) {
                if (i + 1 < runs.size()) {
                    // Mezclar de dos en dos
                    nuevosRuns.add(mezclarRuns(runs.get(i), runs.get(i + 1)));
                } else {
                    // Si queda uno sin par, pasa directo
                    nuevosRuns.add(runs.get(i));
                }
            }
            runs = nuevosRuns;
        }

        // 4. Guardar el archivo final ordenado
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {
            for (int num : runs.get(0)) {
                writer.write(num + " ");
            }
        }
    }

   


