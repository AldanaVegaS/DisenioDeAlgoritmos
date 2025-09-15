package TP1.ConjuntosDisjuntos;

public class test2 {
    public static void main(String[] args) {
        int n = 7;
        ConjuntosDisjuntosOptimizado ds = new ConjuntosDisjuntosOptimizado(n);

        System.out.println("Estado inicial:");
        ds.printConjuntos();

        // Unir algunos conjuntos
        ds.fusionar(1, 2);
        System.out.println("Después de fusionar 1 y 2:");
        ds.printConjuntos();

        ds.fusionar(2, 3);
        System.out.println("Después de fusionar 2 y 3:");
        ds.printConjuntos();
        
        ds.fusionar(4, 5);
        System.out.println("Después de fusionar 4 y 5:");
        ds.printConjuntos();

        ds.fusionar(6, 7);
        System.out.println("Después de fusionar 6 y 7:");
        ds.printConjuntos();

        ds.fusionar(5, 6);
        System.out.println("Después de fusionar 5 y 6:");
        ds.printConjuntos();

        ds.fusionar(3, 7);
        System.out.println("Después de fusionar 3 y 7");
        ds.printConjuntos();

        // Buscar representantes
        System.out.println("\nRepresentantes:");
        for (int i = 1; i <= n; i++) {
            System.out.println("Elemento " + i + " -> representante: " + ds.buscar(i));
        }
    }
    
}
