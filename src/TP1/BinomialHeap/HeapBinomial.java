package TP1.BinomialHeap;

public class HeapBinomial {
    public ArbolBinomial cabeza;

    public HeapBinomial() {
        this.cabeza = null;
    }

    public boolean esVacio() {
        return this.cabeza == null || this.cabeza.getRaiz() == null;
    }

    private ArbolBinomial merge(ArbolBinomial h1, ArbolBinomial h2) {//Une dos arboles binomiales de igual grado
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        if(h1.getRaiz().getKey() < h2.getRaiz().getKey()) {
            h2.getRaiz().setHermano(h1.getRaiz().getHijo());//El hijo actual de h1 pasa a ser hermano de h2
            h1.getRaiz().setHijo(h2.getRaiz());//h2 pasa a ser hijo de h1
            h2.getRaiz().setPadre(h1.getRaiz());//Actualizo el padre de h2
            return h1;
        }else {
            h1.getRaiz().setHermano(h2.getRaiz().getHijo());//El hijo actual de h2 pasa a ser hermano de h1
            h2.getRaiz().setHijo(h1.getRaiz());
            return h2;
        }
        
    }

    public void unir(HeapBinomial unHeap, HeapBinomial otroHeap) {
        if (unHeap.esVacio()) {
            this.cabeza = otroHeap.cabeza;
            return;
        }
        if (otroHeap.esVacio()) {
            this.cabeza = unHeap.cabeza;
            return;
        }

        //Elijo el primer nodo de la nueva lista
        NodoBinomial head;   // inicio de la lista resultante
        NodoBinomial tail;     // puntero al último agregado
        NodoBinomial curr1 = unHeap.cabeza.getRaiz();
        NodoBinomial curr2 = otroHeap.cabeza.getRaiz();
        // elegir el primer nodo
        if (curr1.getGrado() <= curr2.getGrado()) {
            head = curr1;
            curr1 = curr1.getHermano();
        } else {
            head = curr2;
            curr2 = curr2.getHermano();
        }

        tail = head;
        
        //Uno las dos listas de raices de los heaps
        while (curr1 != null && curr2 != null) {
            if (curr1.getGrado() <= curr2.getGrado()) {
                tail.setHermano(curr1);
                curr1 = curr1.getHermano();
            } else {
                tail.setHermano(curr2); // Enlazo el hermano del nodo de curr2 al nodo de curr1
                curr2 = curr2.getHermano();
            }
            tail = tail.getHermano();
        }        

        //Agrego los nodos restantes
        if(curr1 != null) {
            tail.setHermano(curr1);
        } else {
            tail.setHermano(curr2);
        }

        this.cabeza.setRaiz(head);


        //Ahora tengo que recorrer la lista y unir los arboles del mismo grado
        ArbolBinomial curr = this.cabeza;
        ArbolBinomial next = new ArbolBinomial(this.cabeza.getRaiz().getHermano());
        ArbolBinomial prev = null;


        while(next !=null){
            if(curr.getGrado() != next.getGrado()){//Si no son del mismo grado, avanzo
                prev = curr;
                curr = next;
                next = (next.getRaiz().getHermano() != null) ? new ArbolBinomial(next.getRaiz().getHermano()) : null;
            } else {//Si son del mismo grado, los uno
                if(next.getRaiz().getHermano() != null && next.getRaiz().getHermano().getGrado() == curr.getGrado()){//3 arboles del mismo grado
                    prev = curr;
                    curr = next;
                    next = (next.getRaiz().getHermano() != null) ? new ArbolBinomial(next.getRaiz().getHermano()) : null;
                } else {
                    curr.getRaiz().setHermano(next.getRaiz().getHermano());
                    curr = merge(curr, next);
                    if (prev == null) {
                        this.cabeza.setRaiz(curr.getRaiz());
                    } else {
                        prev.getRaiz().setHermano(curr.getRaiz());
                    }
                    next = (curr.getRaiz().getHermano() != null) ? new ArbolBinomial(curr.getRaiz().getHermano()) : null;    
                }
            }
            
        }
    }

    public NodoBinomial buscarMin() {
        if (this.cabeza == null) {
            return null;
        }

        NodoBinomial minNode = this.cabeza.getRaiz();
        NodoBinomial curr = this.cabeza.getRaiz().getHermano();

        while (curr != null) {
            if (curr.getKey() < minNode.getKey()) {
                minNode = curr;
            }
            curr = curr.getHermano();
        }

        System.out.println("El valor minimo es: " + minNode.getKey());
        return minNode;
    }

    public void extraerMin() {
        if (this.esVacio()) {
            System.out.println("El heap esta vacio");
            return;
        }

        // Encuentra el nodo con la clave mínima y su predecesor
        NodoBinomial minNode = this.cabeza.getRaiz();
        NodoBinomial curr = minNode.getHermano();
        NodoBinomial prevMin = null;
        NodoBinomial prev = minNode;

        while (curr != null) {
            if (curr.getKey() < minNode.getKey()) {
                minNode = curr;
                prevMin = prev;
            }
            prev = curr;
            curr = curr.getHermano();
        }
        
        // Elimina minNode de la lista de raíces
        if (prevMin == null) {
            this.cabeza.setRaiz(minNode.getHermano());
        } else {
            prevMin.setHermano(minNode.getHermano());
        }

        //Crear un nuevo heap con los hijos de minNode

        if (minNode.getHijo() == null) {
            return;
        }

        HeapBinomial tempHeap = new HeapBinomial();
        NodoBinomial child = minNode.getHijo();
        NodoBinomial prevChild = null;

        while (child != null) {
            NodoBinomial next = child.getHermano();
            child.setHermano(prevChild);
            child.setPadre(null);
            prevChild = child;
            child = next;
        }

        // ahora prev es la nueva cabeza
        if (prevChild != null) {
            tempHeap.cabeza = new ArbolBinomial(prevChild);
        }

        // Unir el heap temporal con el heap actual
        this.unir(this, tempHeap);
    }



    public void insertar(int key) {
        NodoBinomial nuevo = new NodoBinomial(key);
        ArbolBinomial temp = new ArbolBinomial(nuevo);
        if (this.cabeza == null) {
            this.cabeza = temp;
        } else {
            HeapBinomial tempHeap = new HeapBinomial();
            tempHeap.cabeza = temp;
            HeapBinomial unHeap = new HeapBinomial();
            unHeap.cabeza = this.cabeza;
            this.unir(unHeap, tempHeap);  // aquí se une con el heap actual
        }
}


    public void disminuirClave(NodoBinomial nodo, int nuevoValor) {
        if(nodo.getKey() < nuevoValor) {
            System.out.println("El nuevo valor es mayor que el valor actual.");
            return;
        }

        nodo.setKey(nuevoValor);
        NodoBinomial actual = nodo;
        NodoBinomial padre = actual.getPadre();


        while (padre != null && actual.getKey() < padre.getKey()) {
            // Intercambiar claves
            int temp = actual.getKey();
            actual.setKey(padre.getKey());
            padre.setKey(temp);
            actual = padre;
            padre = actual.getPadre();
        }
    }   


    public void eliminar(NodoBinomial node) {
        disminuirClave(node, Integer.MIN_VALUE);
        extraerMin();
    }




    public void printHeap() {
        System.out.println("Binomial Heap:");
        if (cabeza == null) {
            System.out.println("Empty heap");
            return;
        }
        
        NodoBinomial current = cabeza.getRaiz();
        while (current != null) {
            System.out.println("Árbol Binomial B" + current.getGrado() + ":");
            printTree(current, 0);
            current = current.getHermano();
            System.out.println();
        }
    }
    
    // Método recursivo para imprimir un árbol binomial
    private void printTree(NodoBinomial node, int level) {
        if (node == null) return;
        
        // Imprimir espacios para la indentación
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        
        System.out.println("└─ " + node.getKey() + " (grado: " + node.getGrado() + ")");
        
        // Imprimir hijos recursivamente
        printTree(node.getHijo(), level + 1);
        
        // Imprimir hermanos en el mismo nivel
        if (node.getHermano() != null && level > 0) {
            printTree(node.getHermano(), level);
        }
    }
    
    // Método alternativo para imprimir en formato más visual
    public void printHeapVisual() {
        System.out.println("Visualización del Binomial Heap:");
        if (cabeza == null) {
            System.out.println("Heap vacío");
            return;
        }
        
        NodoBinomial current = cabeza.getRaiz();
        int treeCount = 1;
        
        while (current != null) {
            System.out.println("Árbol " + treeCount + " (B" + current.getGrado() + "):");
            printTreeVisual(current, "");
            current = current.getHermano();
            treeCount++;
            System.out.println();
        }
    }
    
    private void printTreeVisual(NodoBinomial node, String prefix) {
        if (node == null) return;
        
        System.out.println(prefix + "└── " + node.getKey());
        
        if (node.getHijo() != null) {
            printTreeVisual(node.getHijo(), prefix + "    ");
        }
        
        if (node.getHermano() != null && !prefix.isEmpty()) {
            printTreeVisual(node.getHermano(), prefix);
        }
    }
 
    public NodoBinomial buscarNodo(int key) {
        if (this.cabeza == null) {
            return null;
        }else{
           return this.cabeza.buscarNodo(key);
        }

    }

}
