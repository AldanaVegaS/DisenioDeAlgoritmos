package TP1.Grafo;

public class test2 {
    // Ejercicio 3.

    public static void main(String[] args) {
        
        Grafo grafo = new Grafo();

        // vertices del grafo
        grafo.insertarVertice("P7");
        grafo.insertarVertice("P6");
        grafo.insertarVertice("P5");
        grafo.insertarVertice("P4");
        grafo.insertarVertice("P3");
        grafo.insertarVertice("P2");
        grafo.insertarVertice("P1");

        // aristas del grafo
        grafo.insertarArco("P5", "P2", 0);
        grafo.insertarArco("P5", "P4", 0);
        grafo.insertarArco("P3", "P2", 0);
        grafo.insertarArco("P3", "P4", 0);
        grafo.insertarArco("P3", "P7", 0);
        grafo.insertarArco("P2", "P4", 0);
        grafo.insertarArco("P2", "P1", 0);
        grafo.insertarArco("P4", "P6", 0);
        grafo.insertarArco("P6", "P7", 0);

        System.out.println("El grafo es: \n" + grafo.toString());
        System.out.println("Recorrido DFS:" + grafo.listarEnProfundidad());
    }
}
