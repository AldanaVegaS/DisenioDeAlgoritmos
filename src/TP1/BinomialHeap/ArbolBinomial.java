package TP1.BinomialHeap;

public class ArbolBinomial {
    private NodoBinomial raiz;

    public ArbolBinomial(NodoBinomial raiz) {
        this.raiz = raiz;
    }

    public NodoBinomial getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoBinomial raiz) {
        this.raiz = raiz;
    }

    public int getGrado() {
        return raiz.getGrado();
    }


    // Agregar en HeapBinomial.java
    public NodoBinomial buscarNodo(int key) {
        if (this.raiz == null) {
            return null;
        }else{
           return buscarNodoRecursivo(this.raiz, key);
        }
    }

    private NodoBinomial buscarNodoRecursivo(NodoBinomial node, int key) {
        if (node == null) return null;
        
        if (node.getKey() == key) return node;
        
        NodoBinomial found = buscarNodoRecursivo(node.getHijo(), key);
        if (found != null) return found;
        
        return buscarNodoRecursivo(node.getHermano(), key);
    }
}
