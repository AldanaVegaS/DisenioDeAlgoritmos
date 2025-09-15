package TP1.Diccionario;

public class Diccionario {
    private NodoTrie raiz;

    public Diccionario() {
        this.raiz = new NodoTrie();
    }

    public void insertarPalabra(String palabra) {
        NodoTrie nodoActual = raiz;
        for (char c : palabra.toCharArray()) {
            int indice = c - 'a';
            if (nodoActual.getHijos()[indice] == null) {
                nodoActual.getHijos()[indice] = new NodoTrie();
            }
            nodoActual = nodoActual.getHijos()[indice];
        }
        nodoActual.setEsPalabra(true);
    }

    public void agregarSinonimo(String palabra, String sinonimo) {
        NodoTrie nodo = buscarNodo(palabra);
        if (nodo != null && nodo.esPalabra()) {
            nodo.getSinonimos().add(sinonimo);
        } else {
            System.out.println("\nLa palabra no existe en el diccionario, no puede agregarse el sinónimo");
        }
    }

    private NodoTrie buscarNodo(String palabra) {
        NodoTrie nodo = raiz;
        for (char c : palabra.toCharArray()) {
            int idx = c - 'a';
            if (nodo.getHijos()[idx] == null) return null;
            nodo = nodo.getHijos()[idx];
        }
        return nodo;
    }

     public void mostrarSinonimos(String palabra) {
        NodoTrie nodo = buscarNodo(palabra);
        if (nodo != null && nodo.esPalabra()) {
            System.out.println("\nSinónimos de " + palabra + ": " + nodo.getSinonimos());
        } else {
            System.out.println("\nLa palabra "+palabra+" no existe en el diccionario.");
        }
    }



    // Listar todas las palabras
    public void listarPalabras() {
        String prefijo = "";
        listarRecursivo(raiz, prefijo);
    }

    private void listarRecursivo(NodoTrie nodo, String prefijo) {
        if (nodo.esPalabra()) {
            System.out.println(prefijo);
        }
        for (int i = 0; i < 26; i++) {
            if (nodo.getHijos()[i] != null) {
                char letra = (char) ('a' + i);
                listarRecursivo(nodo.getHijos()[i], prefijo + letra);
            }
        }
    }

    // Listar todas las palabras con sus sinonimos
    public void listarPalabrasConSinonimos() {
        String prefijo = "";
        listarRecursivoSinonimo(raiz,prefijo);
    }

    private void listarRecursivoSinonimo(NodoTrie nodo, String prefijo) {
        if (nodo.esPalabra()) {
            System.out.println(prefijo + " -> " + nodo.getSinonimos());
        }
        for (int i = 0; i < 26; i++) {
            if (nodo.getHijos()[i] != null) {
                char letra = (char) ('a' + i);
                listarRecursivoSinonimo(nodo.getHijos()[i], prefijo + letra);
            }
        }
    }
}
