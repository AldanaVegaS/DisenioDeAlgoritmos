package TP1.Diccionario;

import java.util.*;
//IMPLEMENTACION CON HASHMAP

class TrieNodo {
    private Map<Character, TrieNodo> hijos;
    private boolean esPalabra;
    private List<String> sinonimos;

    TrieNodo() {
        hijos = new HashMap<>();
        esPalabra = false;
        sinonimos = new ArrayList<>();
    }

    public Map<Character, TrieNodo> getHijos() {
        return hijos;
    }

    public boolean esPalabra() {
        return esPalabra;
    }

    public void setEsPalabra(boolean esPalabra) {
        this.esPalabra = esPalabra;
    }

    public List<String> getSinonimos() {
        return sinonimos;
    }
}

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

public class Main {
    public static void main(String[] args) {
        DiccionarioSinonimos diccionario = new DiccionarioSinonimos();

        //Insertar palabras
        diccionario.insertarPalabra("hola");
        diccionario.insertarPalabra("casa");
        diccionario.insertarPalabra("cosa");
        diccionario.insertarPalabra("perro");
        diccionario.insertarPalabra("gato");
        diccionario.insertarPalabra("pero");
        diccionario.insertarPalabra("hoy");
        diccionario.insertarPalabra("hombre");
        diccionario.insertarPalabra("hormiga");
        diccionario.insertarPalabra("cantar");
        diccionario.insertarPalabra("cantarle");
        diccionario.insertarPalabra("cantarles");
        diccionario.insertarPalabra("cosa");// palabra repetida

        System.out.println("Palabras en el diccionario:");
        diccionario.listarPalabras();

        //Agregar sinónimos
        diccionario.agregarSinonimo("casa", "hogar");
        diccionario.agregarSinonimo("casa", "vivienda");
        diccionario.agregarSinonimo("perro", "can");
        diccionario.agregarSinonimo("hoy", "actualmente");
        diccionario.agregarSinonimo("animal", "bestia"); // Palabra que no existe en el diccionario

        //Mostrar sinónimos
        diccionario.mostrarSinonimos("casa");
        diccionario.mostrarSinonimos("perro");
        diccionario.mostrarSinonimos("gato");//Palabra sin sinónimos
        diccionario.mostrarSinonimos("animal");//Palabra que no existe en el diccionario

        System.out.println("\nPalabras en el diccionario junto a sus sinonimos:");
        diccionario.listarPalabrasConSinonimos();

    }
}

