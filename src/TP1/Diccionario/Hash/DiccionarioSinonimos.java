package TP1.Diccionario.Hash;
import java.util.*;

class DiccionarioSinonimos {
    private TrieNodo raiz;

    public DiccionarioSinonimos() {
        raiz = new TrieNodo();
    }

    // Insertar palabra en el TRIE
    public void insertarPalabra(String palabra) {
        TrieNodo nodo = raiz;
        for (char c : palabra.toCharArray()) {
            nodo = nodo.getHijos().computeIfAbsent(c, k -> new TrieNodo());
        }
        nodo.setEsPalabra(true);
    }

    // Agregar sinónimo a palabra existente
    public void agregarSinonimo(String palabra, String sinonimo) {
        TrieNodo nodo = buscarNodo(palabra);
        if (nodo != null && nodo.esPalabra()) {
            nodo.getSinonimos().add(sinonimo);
        } else {
            System.out.println("La palabra no existe en el diccionario.");
        }
    }

    // Mostrar todos los sinónimos de una palabra
    public void mostrarSinonimos(String palabra) {
        TrieNodo nodo = buscarNodo(palabra);
        if (nodo != null && nodo.esPalabra()) {
            System.out.println("Sinónimos de " + palabra + ": " + nodo.getSinonimos());
        } else {
            System.out.println("La palabra no existe en el diccionario.");
        }
    }

    // Listar todas las palabras del diccionario
    public void listarPalabras() {
        listarRecursivo(raiz, "");
    }

    private void listarRecursivo(TrieNodo nodo, String prefijo) {
        if (nodo.esPalabra()) {
            System.out.println(prefijo);
        }
        for (Map.Entry<Character, TrieNodo> entrada : nodo.getHijos().entrySet()) {
            listarRecursivo(entrada.getValue(), prefijo + entrada.getKey());
        }
    }

    // Función auxiliar para llegar al nodo de una palabra
    private TrieNodo buscarNodo(String palabra) {
        TrieNodo nodo = raiz;
        for (char c : palabra.toCharArray()) {
            nodo = nodo.getHijos().get(c);
            if (nodo == null) return null;
        }
        return nodo;
    }

    // Listar todas las palabras del diccionario
    public void listarPalabrasConSinonimos() {
        listarRecursivoSinonimos(raiz, "");
    }

    private void listarRecursivoSinonimos(TrieNodo nodo, String prefijo) {
        if (nodo.esPalabra()) {
            System.out.println(prefijo + " -> " + nodo.getSinonimos());
        }
        for (Map.Entry<Character, TrieNodo> entrada : nodo.getHijos().entrySet()) {
            listarRecursivoSinonimos(entrada.getValue(), prefijo + entrada.getKey());
        }
    }
}