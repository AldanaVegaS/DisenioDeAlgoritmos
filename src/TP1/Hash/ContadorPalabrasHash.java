package TP1.Hash;
import java.util.HashMap;
import java.util.Map;

public class ContadorPalabrasHash {
    
    private HashMap<String, Integer> contador;

    public ContadorPalabrasHash() {
        contador = new HashMap<>();
    }

    public void imprimirConteo() {
        for (Map.Entry<String, Integer> entry : contador.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void contarPalabras(String texto) {
        // Implementar el conteo de palabras usando una tabla hash

        String[] palabras = texto.split("\\s+");

        for (String palabra : palabras) {
            palabra = palabra.toLowerCase();
            palabra = palabra.replaceAll("^[\\p{Punct}]+|[\\p{Punct}]+$", "");
            if (!palabra.isEmpty()) {
                contador.put(palabra, contador.getOrDefault(palabra, 0) + 1);
            }
        }
    }

}