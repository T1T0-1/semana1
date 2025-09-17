class Entrada {

int clave;

int valor;

public Entrada(int clave, int valor) this.clave = clave; this.valor = valor

}

{

class TablaHash {

private LinkedList<Entrada>[] tabla;

private int tamaño;

@SuppressWarnings("unchecked")

public TablaHash(int tamaño) {

this.tamaño = tamaño;

tabla = new LinkedList[tamaño];

for (int i = 0; i < tamaño; i++) { tabla[i] = new LinkedList<>();

}

}

// Función hash private int hash(int clave) { return clave % tamaño;

// Insertar (clave, valor)

public void insertar(int clave,

valor) {

int indice = hash(clave); for (Entrada entrada :

tabla[indice]) { 
