package TP1.Grafo;

class NodoAdy
{
    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private Lista etiquetas;
    
    // Constructor.
    public NodoAdy (NodoVert vertice, NodoAdy sigAdy, int etiqueta){
        this.vertice = vertice;
        this.sigAdyacente = sigAdy;
        this.etiquetas = new Lista();
        this.etiquetas.insertar(etiqueta, 1);
    }
    
    // Interfaz.
    public NodoVert getVertice(){
        return this.vertice;
    }
    
    public void setVertice (NodoVert nuevoVertice){
        this.vertice = nuevoVertice;
    }
    
    public NodoAdy getSigAdyacente(){
        return this.sigAdyacente;
    }
    
    public void setSigAdyacente (NodoAdy nuevoAdy){
        this.sigAdyacente = nuevoAdy;
    }
    
    public boolean agregarEtiqueta (int nuevaEtiqueta){
        // Metodo que agrega la etiqueta pasada por parametro de la lista interna de etiquetas.
        
        boolean realizado = false;
        
        if (this.etiquetas.localizar(nuevaEtiqueta) < 0){
            this.etiquetas.insertar(nuevaEtiqueta, 1);
            realizado = true;
        }
        
        return realizado;
    }
    
    public boolean eliminarEtiqueta (int etiqueta){
        // Metodo que elimina la etiqueta pasada por parametro de la lista interna de etiquetas.
        int pos = this.etiquetas.localizar(etiqueta);
        boolean realizado = false;
        
        if (pos > 0){
            this.etiquetas.eliminar(pos);
            realizado = true;
        }
        
        return realizado; 
    }
    
    public boolean esEtiquetaVacia (){
        // Metodo que verifica la existencia de una etiqueta dentro de la lista de etiquetas. 
        return this.etiquetas.esVacia();
    }
    
    public int buscarEtiquetaMenor(){
        // Metodo encargado de buscar la etiqueta con menor valor dentro de la lista interna de etiquetas.
        int max = this.etiquetas.longitud(), i=2, elemMin = (int)this.etiquetas.recuperar(1);
        
        while (i<= max){
            elemMin = Math.min((int)this.etiquetas.recuperar(i), elemMin);
            i++;
        }
        
        return elemMin;
    }
    
    public String toStringEtiquetas(){
        return this.etiquetas.toString();
    }
}
