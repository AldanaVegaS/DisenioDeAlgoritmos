package TP1.ConjuntosDisjuntos;

public class test {
    public static void main(String[] args) {
        ConjuntosDisjuntos ds = new ConjuntosDisjuntos(10);

        System.out.println("Conjuntos iniciales:");
        ds.printConjuntos();

        ds.fusionar(1, 2);
        System.out.println("Después de fusionar 1 y 2:");
        ds.printConjuntos();
        ds.fusionar(2, 3);
        System.out.println("Después de fusionar 2 y 3:");
        ds.printConjuntos();
        ds.fusionar(4, 5);
        System.out.println("Después de fusionar 4 y 5:");
        ds.printConjuntos();
        ds.fusionar(5, 6);
        System.out.println("Después de fusionar 5 y 6:");
        ds.printConjuntos();
        ds.fusionar(1, 5);
        System.out.println("Después de fusionar 1 y 5:");
        ds.printConjuntos();

        System.out.println("El representante de 3 es: " + ds.buscar(3)); // Debe ser el mismo que el de 6
        System.out.println("El representante de 6 es: " + ds.buscar(6)); // Debe ser el mismo que el de 3
        System.out.println("El representante de 7 es: " + ds.buscar(7)); // Debe ser 7 (conjunto separado)
    }
}
