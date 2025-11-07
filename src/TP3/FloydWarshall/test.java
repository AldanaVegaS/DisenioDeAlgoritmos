package TP3.FloydWarshall;


public class test {
    
    public static void main(String[] args) {
        
        double INF = Double.POSITIVE_INFINITY;
        double graph[][] = new double[][] {
            {0, 3, INF, 7},
            {8, 0, 2, INF},
            {5, INF, 0, 1},
            {2, INF, INF, 0}
        };

        FloydWarshall fw = new FloydWarshall(graph);
        fw.floydWarshall();

        System.out.println("Matriz de distancias más cortas entre cada par de vértices:");
        fw.printMatrix();
    }

}
