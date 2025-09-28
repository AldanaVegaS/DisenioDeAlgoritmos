package TP1.BinomialHeap;

public class test {
    public static void main(String[] args) {
        HeapBinomial heap = new HeapBinomial();

        System.out.println("Heap inicial (vacio):");
        System.out.println("Está vacío? " + heap.esVacio());
        heap.printHeapVisual();
        System.out.println("------------------------------------------------------------------\n");


        /*PRUEBA INSERTAR ELEMENTOS */
        System.out.println("PRUEBA INSERTAR ELEMENTOS:");
        System.out.println("Intenta insertar 10");
        heap.insertar(10);
        heap.printHeapVisual();

        System.out.println("Intenta insertar 20");
        heap.insertar(20);
        heap.printHeapVisual();
        
        System.out.println("Intenta insertar 5");
        heap.insertar(5);
        heap.printHeapVisual();

        System.out.println("Intenta insertar 15");
        heap.insertar(15); 
        heap.printHeapVisual();

        System.out.println("Intenta insertar 1");
        heap.insertar(1); 
        heap.printHeapVisual();


        System.out.println("Intenta insertar 3");
        heap.insertar(3); 
        heap.printHeapVisual();


        System.out.println("Intenta insertar 4");
        heap.insertar(4); 
        heap.printHeapVisual();
        System.out.println("------------------------------------------------------------------\n");

        /*PRUEBA ENCONTRAR EL MINIMO */
        System.out.println("PRUEBA ENCONTRAR EL MINIMO:");
        heap.buscarMin();
        System.out.println("------------------------------------------------------------------\n");

        /*PRUEBA DISMINUIR CLAVE */
        System.out.println("PRUEBA DISMINUIR CLAVE:");
        NodoBinomial nodo = heap.buscarNodo(15);
        heap.disminuirClave(nodo, 2);
        heap.printHeapVisual();
        System.out.println("------------------------------------------------------------------\n");

        /*PRUEBA ELIMINAR ELEMENTO */
        System.out.println("PRUEBA ELIMINAR ELEMENTO:");
        System.out.println("Eliminar nodo con clave 20");
        heap.eliminar(20);
        heap.printHeapVisual();
        System.out.println("------------------------------------------------------------------\n");
        

        /*PRUEBA ELIMINAR EL MINIMO */
        System.out.println("PRUEBA ELIMINAR EL MINIMO:");
        System.out.println("Extraer min:");
        heap.extraerMin();
        heap.printHeapVisual();
        System.out.println("\n\n");

        System.out.println("Extraer min:");
        heap.extraerMin();
        heap.printHeapVisual();
        System.out.println("\n\n");

        System.out.println("Extraer min:");
        heap.extraerMin();
        heap.printHeapVisual();
        System.out.println("\n\n");

        System.out.println("Extraer min:");
        heap.extraerMin();
        heap.printHeapVisual();
        System.out.println("\n\n");

        System.out.println("Extraer min:");
        heap.extraerMin();
        heap.printHeapVisual();
        System.out.println("\n\n");

        System.out.println("Extraer min:");
        heap.extraerMin();
        heap.printHeapVisual();
        System.out.println("\n\n");

        System.out.println("Extraer min:");
        heap.extraerMin();
        heap.printHeapVisual();
        System.out.println("\n\n"); 

    }
}
