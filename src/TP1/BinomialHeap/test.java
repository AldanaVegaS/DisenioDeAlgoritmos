package TP1.BinomialHeap;

public class test {
    public static void main(String[] args) {
        HeapBinomial heap = new HeapBinomial();

        System.out.println("Heap inicial (vacio):");
        heap.printHeapVisual();
        System.out.println("\n\n");

        /*PRUEBA INSERTAR ELEMENTOS */
        heap.insertar(10);
        System.out.println("Se inserto 10");
        heap.printHeapVisual();
        System.out.println("\n\n");


        heap.insertar(20);
        System.out.println("Se inserto 20");
        heap.printHeapVisual();
        System.out.println("\n\n");
        
        heap.insertar(5);
        System.out.println("Se inserto 5");
        heap.printHeapVisual();
        System.out.println("\n\n");

        heap.insertar(15); 
        System.out.println("Se inserto 15");
        heap.printHeapVisual();
        System.out.println("\n\n");

        heap.insertar(1); 
        System.out.println("Se inserto 1");
        heap.printHeapVisual();
        System.out.println("\n\n");


        heap.insertar(3); 
        System.out.println("Se inserto 3");
        heap.printHeapVisual();
        System.out.println("\n\n");


        heap.insertar(4); 
        System.out.println("Se inserto 4");
        heap.printHeapVisual();
        System.out.println("\n\n");


        /*PRUEBA ENCONTRAR EL MINIMO */
        heap.buscarMin();

        /*PRUEBA DISMIMUIR CLAVE */


        /*PRUEBA ELEIMINAR ELEMENTO */
        
        

        /*PRUEBA ELIMINAR EL MINIMO */
        heap.extraerMin();
        heap.printHeapVisual();
        System.out.println("\n\n");

        heap.extraerMin();
        heap.printHeapVisual();
        System.out.println("\n\n");

        heap.extraerMin();
        heap.printHeapVisual();
        System.out.println("\n\n");

        heap.extraerMin();
        heap.printHeapVisual();
        System.out.println("\n\n");

        heap.extraerMin();
        heap.printHeapVisual();
        System.out.println("\n\n");

        heap.extraerMin();
        heap.printHeapVisual();
        System.out.println("\n\n");

        heap.extraerMin();
        heap.printHeapVisual();
        System.out.println("\n\n");

    }
}
