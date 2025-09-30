package TP1.Grafo;

class NodoVert
{
    private Object elem;
    private NodoVert sigVertice;
    private NodoAdy primerAdy;
    
    // Contructores.
    
    public NodoVert(Object elem, NodoVert sigVertice, NodoAdy primerAdy)
    {
        this.elem = elem;
        this.sigVertice = sigVertice;
        this.primerAdy = primerAdy;
    }
    
    // Interfaz.
    public Object getElem()
    {
        return this.elem;
    }
    
    public void setElem (Object nuevoElem)
    {
        this.elem = nuevoElem;
    }
    
    public NodoVert getSigVertice()
    {
        return this.sigVertice;
    }
    
    public void setSigVertice (NodoVert nuevoSigVert)
    {
        this.sigVertice = nuevoSigVert;
    }
    
    public NodoAdy getPrimerAdy()
    {
        return this.primerAdy;
    }
    
    public void setPrimerAdy (NodoAdy nuevoPrimerAdy)
    {
        this.primerAdy = nuevoPrimerAdy;
    }
    
}
