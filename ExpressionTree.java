import java.util.Stack;

// Definición de la clase Nodo
class Nodo {
    String valor;
    Nodo izquierdo, derecho;

    public Nodo(String item) {
        valor = item;
        izquierdo = derecho = null;
    }
}

// Clase para manejar el árbol de expresión y su evaluación
class ArbolDeExpresion {
    Nodo raiz;

    // Método para construir el árbol de expresión a partir de una expresión en notación infija
    Nodo construirArbol(String[] expresion) {
        Stack<Nodo> nodos = new Stack<>();
        Stack<String> operadores = new Stack<>();

        for (String token : expresion) {
            if (token.matches("-?\\d+")) {
                nodos.push(new Nodo(token));
            } else if (token.equals("(")) {
                operadores.push(token);
            } else if (token.equals(")")) {
                while (!operadores.isEmpty() && !operadores.peek().equals("(")) {
                    nodos.push(construirSubArbol(operadores.pop(), nodos.pop(), nodos.pop()));
                }
                if (!operadores.isEmpty() && operadores.peek().equals("(")) {
                    operadores.pop();
                }
            } else if (token.matches("[+\\-*/]")) {
                while (!operadores.isEmpty() && precedencia(token) <= precedencia(operadores.peek())) {
                    nodos.push(construirSubArbol(operadores.pop(), nodos.pop(), nodos.pop()));
                }
                operadores.push(token);
            }
        }

        while (!operadores.isEmpty()) {
            nodos.push(construirSubArbol(operadores.pop(), nodos.pop(), nodos.pop()));
        }

        return nodos.pop();
    }

    // Método para construir un subárbol dado un operador y dos operandos
    Nodo construirSubArbol(String operador, Nodo derecho, Nodo izquierdo) {
        Nodo nodo = new Nodo(operador);
        nodo.derecho = derecho;
        nodo.izquierdo = izquierdo;
        return nodo;
    }

    // Método para evaluar el árbol de expresión
    int evaluar(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }

        // Si el nodo es una hoja (número)
        if (nodo.izquierdo == null && nodo.derecho == null) {
            return Integer.parseInt(nodo.valor);
        }

        // Evaluar subárboles
        int valorIzquierdo = evaluar(nodo.izquierdo);
        int valorDerecho = evaluar(nodo.derecho);

        // Aplicar operador
        switch (nodo.valor) {
            case "+":
                return valorIzquierdo + valorDerecho;
            case "-":
                return valorIzquierdo - valorDerecho;
            case "*":
                return valorIzquierdo * valorDerecho;
            case "/":
                if (valorDerecho == 0) {
                    throw new UnsupportedOperationException("División por cero");
                }
                return valorIzquierdo / valorDerecho;
        }
        return 0;
    }

    // Método para determinar la precedencia de los operadores
    int precedencia(String operador) {
        switch (operador) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
        }
        return -1;
    }

    // Método principal para probar el código
    public static void main(String[] args) {
        ArbolDeExpresion arbol = new ArbolDeExpresion();
        String expresion = "((3+4)*5)+((2-5)+(8*2))";
        String[] tokens = expresion.replaceAll("\\(", " ( ").replaceAll("\\)", " ) ").split("\\s+");

        arbol.raiz = arbol.construirArbol(tokens);
        System.out.println("El resultado de la expresión es " + arbol.evaluar(arbol.raiz));
    }
}
