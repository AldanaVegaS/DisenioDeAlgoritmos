package TP1.ConjuntosDisjuntos;

public class ConjuntosDisjuntos {
    int[] padre;
    
    // Inicializa n elementos
    public ConjuntosDisjuntos(int n) {
        padre = new int[n+1];
        for (int i = 1; i <= n; i++) {
            padre[i] = i;
        }
    }

    // Find
    public int buscar(int x) {
        return padre[x];
    }

    // Union
    public void fusionar(int a, int b) {
        if (a == b) {
            return;
        }
        
        int repA = buscar(a);
        int repB = buscar(b);
        if (repA != repB) {
            int min = repA < repB ? repA : repB;
            int max = repA > repB ? repA : repB;
            // Cambiar todos los elementos del conjunto con representante 'max' al conjunto con representante '
            for (int i = 1; i < padre.length; i++) {
                if (padre[i] == max) {
                    padre[i] = min;
                }
            }
        }
    }


    public void printConjuntos() {
        for (int i = 1; i < padre.length; i++) {
            System.out.println("Elemento: " + i + " -> Conjunto: " + padre[i]);
        }
        System.out.println("");
    }
}



