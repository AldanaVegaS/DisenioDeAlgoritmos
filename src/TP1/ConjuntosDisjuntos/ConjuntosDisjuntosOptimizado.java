package TP1.ConjuntosDisjuntos;

public class ConjuntosDisjuntosOptimizado {
    private int[] padre;
    private int[] rango;
    
    public ConjuntosDisjuntosOptimizado(int n) {
        padre = new int[n+1];
        rango = new int[n+1];
        for (int i = 1; i <= n; i++) {
            padre[i] = i;
            rango[i] = 0;
        }
    }
    
    public int buscar(int x) {
        if (padre[x] != x) {
            x = buscar(padre[x]);
        }
        return x;
    }
    
    public int buscarPC(int x) {// Con path compression
        if (x == padre[x]) {
            return x;
        }
        int ultp = buscarPC(padre[x]);
        padre[x] = ultp; 
        return padre[x];
    }

    public void fusionar(int a, int b) {
        if (a < b)
            padre[b] = a;
        else
            padre[a] = b;
    }
    
    public void fusionarUnionByRank(int a, int b) {
        int raizA = buscarPC(a);
        int raizB = buscarPC(b);
        
        if (raizA == raizB) return;
        
        // Union by rank: el árbol más pequeño se une al más grande
        if (rango[raizA] < rango[raizB]) {
            padre[raizA] = raizB;
        } else if (rango[raizA] > rango[raizB]) {
            padre[raizB] = raizA;
        } else {
            padre[raizB] = raizA;
            rango[raizA]++;
        }
    }


    public void printConjuntos() {
        for (int i = 1; i < padre.length; i++) {
            System.out.println("Elemento: " + i + " -> Conjunto: " + buscar(i)+" (Padre: "+padre[i]+", Rango: "+rango[i]+")");
        }
        System.out.println("");
    }
}
