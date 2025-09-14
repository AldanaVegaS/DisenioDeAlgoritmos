package TP1.BinomialHeap;

public class HeapBinomial {
    public ArbolBinomial cabeza;

    public HeapBinomial() {
        this.cabeza = null;
    }

    public boolean esVacio() {
        return this.cabeza.getRaiz() == null;
    }

    private ArbolBinomial merge(ArbolBinomial h1, ArbolBinomial h2) {//Une dos arboles binomiales de igual grado
        //System.out.println("DENTRO DE MERGE");
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        //System.out.println("Comparando las raices: h1=" + h1.getRaiz().getKey() + " y h2=" + h2.getRaiz().getKey());

        if(h1.getRaiz().getKey() < h2.getRaiz().getKey()) {
            //System.out.println("h1 es menor que h2");
            h2.getRaiz().setHermano(h1.getRaiz().getHijo());//El hijo actual de h1 pasa a ser hermano de h2
            h1.getRaiz().setHijo(h2.getRaiz());//h2 pasa a ser hijo de h1
            h2.getRaiz().setPadre(h1.getRaiz());//Actualizo el padre de h2
            return h1;
        }else {
            //System.out.println("h2 es menor que h1");
            h1.getRaiz().setHermano(h2.getRaiz().getHijo());//El hijo actual de h2 pasa a ser hermano de h1
            h2.getRaiz().setHijo(h1.getRaiz());
            return h2;
        }
        
    }

    public void unir(HeapBinomial unHeap, HeapBinomial otroHeap) {
        /* System.out.println("\nDENTRO DE UNION");
        System.out.println("Los heaps que quiero unir son:");
        unHeap.printHeap();
        otroHeap.printHeap(); */

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
            //System.out.println("DENTRO DEL WHILE DE UNION PARA GENERAR LA LISTA ORDENADA POR GRADO");
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
        this.printHeap();


        //Ahora tengo que recorrer la lista y unir los arboles del mismo grado
        ArbolBinomial curr = this.cabeza;
        ArbolBinomial next = new ArbolBinomial(this.cabeza.getRaiz().getHermano());
        ArbolBinomial prev = null;

        while(next != null){
            /* System.out.println("DENTRO DEL WHILE DE UNION PARA UNIR ARBOLES DEL MISMO GRADO");
            System.out.println("El actual es: " + curr.getRaiz().getKey() + " con grado: " + curr.getGrado());
            System.out.println("El siguiente es: " + next.getRaiz().getKey() + " con grado: " + next.getGrado());
            System.out.println("El previo es: " + (prev != null ? prev.getRaiz().getKey() : "null")); */
            if(curr.getGrado() == next.getGrado()){//Mismo grado, hay que unirlos
                //System.out.println("TIENEN EL MISMO GRADO");
                curr.getRaiz().setHermano(next.getRaiz().getHermano());
                curr = merge(curr, next);
                if (prev == null) {
                    this.cabeza.setRaiz(curr.getRaiz());
                } else {
                    prev.getRaiz().setHermano(curr.getRaiz());
                }
                //System.out.println("Luego del merge, el nuevo arbol es con raiz: " + curr.getRaiz().getKey() + " y grado: " + curr.getGrado());
            } else {
                //System.out.println("NO TIENEN EL MISMO GRADO");
                if (curr.getRaiz().getGrado() > next.getRaiz().getGrado()) {
                    // Fix: intercambiar current y next
                    prev = curr;
                    curr = next;
                }
                curr = next;
            }
            if (curr.getRaiz().getHermano() != null) {
               next = (curr.getRaiz().getHermano() != null) ? new ArbolBinomial(curr.getRaiz().getHermano()) : null;
            } else {
                next = null;
            }
        } 
    }

    public NodoBinomial buscarMin() {
        if (this.cabeza == null) {
            //System.out.println("El heap esta vacio");
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
        //System.out.println("DENTRO DE ELIMINAR MIN");
        if (this.cabeza == null) {
            //System.out.println("El heap esta vacio");
            return;
        }

        // Encuentra el nodo con la clave mínima y su predecesor
        //System.out.println("BUSCANDO EL MINIMO Y ASU PREDECESOR");
        NodoBinomial minNode = this.cabeza.getRaiz();
        NodoBinomial curr = minNode.getHermano();
        NodoBinomial prevMin = null;
        NodoBinomial prev = minNode;

        while (curr != null) {
            //System.out.println("El actual es: " + curr.getKey() + " y el minimo es: " + minNode.getKey());
            if (curr.getKey() < minNode.getKey()) {
                minNode = curr;
                prevMin = prev;
                //System.out.println("El nuevo minimo es: " + minNode.getKey());
            }
            prev = curr;
            curr = curr.getHermano();
        }
        //System.out.println("El valor minimo es: " + minNode.getKey()+ " y el previo es: " + (prevMin != null ? prevMin.getKey() : "null"));
        
        // Elimina minNode de la lista de raíces
        //System.out.println("ELIMINANDO EL MINIMO DE LA LISTA DE RAICES");
        if (prevMin == null) {
            this.cabeza.setRaiz(minNode.getHermano());
        } else {
            prevMin.setHermano(minNode.getHermano());
        }
        this.printHeap();

        //Crear un nuevo heap con los hijos de minNode
        //System.out.println("CREANDO UN HEAP TEMPORAL CON LOS HIJOS DEL MINIMO");

        if (minNode.getHijo() == null) {
            //System.out.println("El minimo no tiene hijos, solo se elimina.");
            //System.out.println("El valor minimo " + minNode.getKey() + " ha sido eliminado.");
            return;
        }

        HeapBinomial tempHeap = new HeapBinomial();
        NodoBinomial child = minNode.getHijo();
        NodoBinomial prevChild = null;

        //System.out.println("El hijo del minimo es: " + (child != null ? child.getKey() : "null"));
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
        //tempHeap.printHeap();

        // Unir el heap temporal con el heap actual
        this.unir(this, tempHeap);

        System.out.println("El valor minimo " + minNode.getKey() + " ha sido eliminado.");
    }



    public void insertar(int key) {
        //System.out.println("DENTRO DE INSERTAR");
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


    public void disminuirClave(ArbolBinomial arbol, int valorActual, int nuevoValor) {
        

        System.out.println("La clave ha sido decrementada a " + nuevoValor);
    }   


    public void eliminar(ArbolBinomial node) {
        //disminuirClave(node, Integer.MIN_VALUE);
        extraerMin();
        System.out.println("El nodo ha sido eliminado.");
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
    
}
