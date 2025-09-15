package TP1.Diccionario;

import java.util.ArrayList;
import java.util.List;

public class NodoTrie {
    private NodoTrie[] hijos;
    private boolean esPalabra;// Indica si el nodo representa el final de una palabra
    private List <String> sinonimos = new ArrayList<>();

    public NodoTrie() {
        this.hijos = new NodoTrie[26]; // Asumiendo solo letras minúsculas a-z
        this.esPalabra = false;
    }

    public NodoTrie[] getHijos() {
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
