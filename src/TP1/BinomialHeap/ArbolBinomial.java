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
}
