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
    private LinkedList<Entrada>[] tabla;
    private int tamaño;

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
    public void mostrar() {
        for (int i = 0; i < tamaño; i++) {
            System.out.print("[" + i + "]: ");
            for (Entrada entrada : tabla[i]) {
                System.out.print("(" + entrada.clave + " -> " + entrada.valor + ") ");
            }
            System.out.println();
        }
    }
}

public class TablaHashDesdeCero {
    public static void main(String[] args) {
        TablaHash hash = new TablaHash(5); // tabla de tamaño 5

        // Insertar elementos
        hash.insertar(1, 100);
        hash.insertar(6, 200); // colisión con clave 1 (porque 6 % 5 == 1)
        hash.insertar(11, 300); // otra colisión
        hash.insertar(3, 150);

        System.out.println("Tabla Hash inicial:");
        hash.mostrar();

        // Buscar elementos
        System.out.println("\nBuscar clave 6: " + hash.buscar(6));
        System.out.println("Buscar clave 10: " + hash.buscar(10));

        // Eliminar un elemento
        System.out.println("\nEliminar clave 6: " + hash.eliminar(6));
        hash.mostrar();
    }
}