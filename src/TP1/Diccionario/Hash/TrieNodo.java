package TP1.Diccionario.Hash;
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