// Definición de la clase Nodo
class Nodo {
    int valor;
    Nodo izquierdo, derecho;

    public Nodo(int item) {
        valor = item;
        izquierdo = derecho = null;
    }
}

// Definición de la clase ArbolBinario
class ArbolBinario {
    Nodo raiz;

    // Constructor para inicializar el árbol
    public ArbolBinario() {
        raiz = null;
    }

    // Método para encontrar el valor máximo en el árbol
    int encontrarMaximo(Nodo nodo) {
        if (nodo == null) {
            return Integer.MIN_VALUE; // Retorna el valor mínimo posible si el nodo es nulo
        }

        // Encuentra el máximo en el subárbol izquierdo, derecho y el nodo actual
        int maxIzquierdo = encontrarMaximo(nodo.izquierdo);
        int maxDerecho = encontrarMaximo(nodo.derecho);
        int maxActual = nodo.valor;

        // Encuentra el máximo de los tres valores
        return Math.max(maxActual, Math.max(maxIzquierdo, maxDerecho));
    }

    // Método principal para probar el código
    //Se usaron valores arbitrarios para probar
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        arbol.raiz = new Nodo(1);
        arbol.raiz.izquierdo = new Nodo(2);
        arbol.raiz.derecho = new Nodo(3);
        arbol.raiz.izquierdo.izquierdo = new Nodo(4);
        arbol.raiz.izquierdo.derecho = new Nodo(5);

        System.out.println("El valor máximo en el árbol es " + arbol.encontrarMaximo(arbol.raiz));
    }
}
