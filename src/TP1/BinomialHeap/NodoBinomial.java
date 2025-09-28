package TP1.BinomialHeap;

public class NodoBinomial {
    private int key;
    private int grado;
    private NodoBinomial padre;
    private NodoBinomial hermano;
    private NodoBinomial hijo;

    public NodoBinomial(int key) {
        this.key = key;
        this.grado = 0;
        this.padre = null;
        this.hermano = null;
        this.hijo = null;
    }

    public int getKey() {
        return key;
    }

    public int getGrado() {
        return grado;
    }

    public NodoBinomial getPadre() {
        return padre;
    }

    public NodoBinomial getHermano() {
        return hermano;
    }
    
    public NodoBinomial getHijo() {
        return hijo;
    }

    public void setHermano(NodoBinomial hermano) {
        this.hermano = hermano;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setHijo(NodoBinomial hijo) {
        this.hijo = hijo;
        this.grado = 1 + hijo.getGrado();
    }

    public void setPadre(NodoBinomial padre) {
        this.padre = padre;
    }
}
