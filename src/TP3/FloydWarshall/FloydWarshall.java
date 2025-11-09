package TP3.FloydWarshall;

public class FloydWarshall {

    private double[][] w;

    public FloydWarshall(double[][] w) {
        this.w = w;
    }

    public void floydWarshall(){
        int V = w.length;

        System.out.println("Matriz inicial de distancias:");
        printMatrix();
        System.out.println();

        // Agrega todos los vértices uno por uno al conjunto de vértices intermedios.
        for (int k = 0; k < V; k++) {

            // Elige todos los vértices uno por uno como vértices de origen.
            for (int i = 0; i < V; i++) {

                for (int j = 0; j < V; j++) {

                    if (Double.isFinite(w[i][k]) && Double.isFinite(w[k][j])) {
                        w[i][j] = Math.min(w[i][j], w[i][k] + w[k][j]);
                    }
                }
            }

            System.out.println("Nodo k = " + k);
            printMatrix();
            System.out.println();
        }
    }

    public void printMatrix() {
        int n = w.length;

        System.out.print("    ");
        for (int j = 0; j < n; j++) {
            System.out.printf("%-6c", 'A' + j);
        }
        System.out.println();

        // Filas con nombre del nodo
        for (int i = 0; i < n; i++) {
            System.out.printf("%-4c", 'A' + i); // nombre de la fila
            for (int j = 0; j < n; j++) {
                if (Double.isInfinite(w[i][j])) {
                    System.out.printf("%-6s", "INF");
                } else {
                    System.out.printf("%-6.1f", w[i][j]);
                }
            }
            System.out.println();
        }
    }

}
