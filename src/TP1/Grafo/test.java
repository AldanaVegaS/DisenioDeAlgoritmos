package TP1.Grafo;

public class test {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        // vertices del grafo
        grafo.insertarVertice("1");
        grafo.insertarVertice("2");
        grafo.insertarVertice("3");
        grafo.insertarVertice("4");
        grafo.insertarVertice("5");
        grafo.insertarVertice("6");
        grafo.insertarVertice("7");
        grafo.insertarVertice("8");
        grafo.insertarVertice("9");

        // aristas del grafo
        grafo.insertarArco("1", "2", 0);
        grafo.insertarArco("1", "3", 0);
        grafo.insertarArco("1", "4", 0);
        grafo.insertarArco("2", "5", 0);
        grafo.insertarArco("2", "6", 0);
        grafo.insertarArco("3", "6", 0);
        grafo.insertarArco("3", "8", 0);
        grafo.insertarArco("3", "7", 0);
        grafo.insertarArco("4", "7", 0);
        grafo.insertarArco("5", "1", 0);
        grafo.insertarArco("6", "2", 0);
        grafo.insertarArco("6", "4", 0);
        grafo.insertarArco("7", "2", 0);
        grafo.insertarArco("7", "3", 0);
        grafo.insertarArco("8", "9", 0);
        grafo.insertarArco("9", "6", 0);

        System.out.println("El grafo es: \n" + grafo.toString());
        System.out.println("Recorrido BFS:" + grafo.listarEnAnchura());
        System.out.println("Recorrido DFS:" + grafo.listarEnProfundidad());

    }
}
