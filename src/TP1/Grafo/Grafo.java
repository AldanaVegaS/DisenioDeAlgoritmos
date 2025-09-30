package TP1.Grafo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Grafo
{
    // Implementación dinamica mediante listas de adyacencia.
    
    NodoVert inicio;
    
    // Constructor.
    public Grafo(){
        // Crea un grafo vacio.
        this.inicio = null;
    }
    
    // Interfaz.
    public boolean insertarVertice (Object elem){
        /* Insertar el elem pasado por parametro a la estructura. Devuelve true
           en caso que se lo haya insertado correctamente, caso contrario, devuelve false. */
        
        boolean realizado = false;
        
        if (ubicarVertice(this.inicio, elem) == null){
            // Si no se encuentra el vertice, se lo agrega a la estructura.
            this.inicio = new NodoVert (elem, this.inicio, null);
            realizado = true;
        }
        
        return realizado;
    }
    
    private NodoVert ubicarVertice (NodoVert aux, Object elem){
        /* Metodo privado que busca y devuelve el vertice con el elem pasado
           por parametro en caso que este exista, caso contrario devuelve null. 
           La busqueda comienza desde el vertice aux */
        
        NodoVert vertActual = aux, verticeBuscado = null;
        boolean seguir = true;
        
        while (vertActual != null && seguir){

            if (vertActual.getElem().equals (elem)){
                verticeBuscado = vertActual;
                seguir = false;
            }
            else
                vertActual = vertActual.getSigVertice();
        }
        
        return verticeBuscado;
    }
    
    public boolean eliminarVertice(Object elemEliminar)
    {
        /* Elimina el elem pasado por parametro en caso que este exista, caso
            contrario, devuelve false. */
 
        boolean realizado = false;
        
        if (this.inicio != null)
            realizado = eliminarVerticeAux(elemEliminar);
        
        return realizado;
    }
    
    private boolean eliminarVerticeAux (Object buscado)
    {
        /* Metodo privado encargado de eliminar el vertice con el elem en
           la lista de vertices.*/
        
        boolean realizado = true;
        NodoVert verticeAux = this.inicio;
        
        if (this.inicio.getElem().equals(buscado)) // Caso especial: eliminar inicio.
            this.inicio = this.inicio.getSigVertice();
        else{
        
            NodoVert vertAnterior = null;
            // Se busca en la lista de vertices al buscado.
            while (verticeAux != null && !(verticeAux.getElem().equals(buscado))){
                vertAnterior = verticeAux;
                verticeAux = verticeAux.getSigVertice();
            }
            
            if (verticeAux != null) // Se ha encontrado el vertice a eliminar.
                // Se enlaza vertice anterior con el siguiente del vertice a eliminar.
                vertAnterior.setSigVertice(verticeAux.getSigVertice());
            else
                // El vertice no existe en el grafo.
                realizado = false;
        }
        
        if (realizado) // Se elimina vertice de la lista de ady de los demas vertices.
            eliminarVerticeDeListaDeAdy (verticeAux);
        
        return realizado;
    }
    
    private void eliminarVerticeDeListaDeAdy (NodoVert eliminado){
        /* Metodo encargado de recorrer la lista de adyacencia del eliminado
           y eliminar los arcos que apuntan a el. */
        
        NodoAdy adyacente = eliminado.getPrimerAdy();
        
        while (adyacente != null){
            // Se elimina arco.
            desconectarArco (adyacente.getVertice(), eliminado);
            adyacente = adyacente.getSigAdyacente();
        }
    }
    
    private void desconectarArco (NodoVert vertActual, NodoVert buscado){
        /* Metodo privado que elimina el vertice buscado en la lista de adyacencia de vertActual. */
        
        NodoAdy adyacActual = vertActual.getPrimerAdy();
        
        if (adyacActual.getVertice().getElem().equals(buscado.getElem())) // Caso especial: buscado es el primer adyacente.
            vertActual.setPrimerAdy(adyacActual.getSigAdyacente());
        else{
        
            NodoAdy adyacAnterior = null;
            // Se recorre la lista de adyacencia hasta encontrar al vertice buscado.
            while (adyacActual != null && ! (adyacActual.getVertice().getElem().equals(buscado.getElem()))){
                adyacAnterior = adyacActual;
                adyacActual = adyacActual.getSigAdyacente();
            }
            
            if (adyacActual != null) // Se encontro el vertice, por lo tanto, se lo elimina.
                adyacAnterior.setSigAdyacente(adyacActual.getSigAdyacente());
        }   
    }
    
    public boolean existeVertice (Object elem){
        /* Dado un elem, devuelve verdadero si esta en la estructura, caso
           contrario, devuelve false. */
        
        boolean existe = false;
        
        if (ubicarVertice (this.inicio, elem) != null)
            existe = true;
        
        return existe;
    }
    
    public boolean insertarArco (Object origen, Object destino, int etiqueta){
        /*  Metodo que agrega un arco entre los vertices origen y destino. Si se
            realiza la insercion con exito devuelve true, caso contrario, false. */
        
        boolean realizado = false;
        
        if (this.inicio != null && !origen.equals(destino))
            realizado = insertarArcoAux (origen, destino, etiqueta);
        
        return realizado;
    }
    
    private boolean insertarArcoAux (Object origen, Object destino, int etiqueta){
        /* Metodo privado que busca los vertices involucrados e inserta un arco
           entre ellos en caso que se cumpla que:
           - Los vertices existan.
           - No exista una etiqueta igual entre ellos.*/
        
        boolean realizado = false;
        NodoVert aux1 = buscarPrimeraAparicionVertice (origen, destino), nodoOrigen, nodoDestino;
        
        if (aux1 != null){ // Se encontro uno de los vertices.

            // Se identifica el vertice encontrado y se busca el otro vertice involucrado.
            if (aux1.getElem().equals(origen)){
                nodoOrigen = aux1;
                nodoDestino = ubicarVertice (aux1.getSigVertice(), destino);
            }else{
                nodoDestino = aux1;
                nodoOrigen = ubicarVertice (aux1.getSigVertice(), origen);
            }
                
            if (nodoDestino != null && nodoOrigen != null) // Se encontro ambos vertices.
                realizado = insertarEtiqueta (nodoOrigen, nodoDestino, etiqueta);
        } 
        
        return realizado;
    }       
    
    private boolean insertarEtiqueta (NodoVert vertice1, NodoVert vertice2, int etiqueta){
        /* Metodo privado encargado de insertar una etiqueta en caso que:
            - La etiqueta no este repetida.*/
        
        boolean realizado = true;
        NodoAdy aux = vertice1.getPrimerAdy(), buscado = null;
        
        // Se recorre lista de adyacencia de uno de los vertices.
        while (aux != null && buscado == null){
            if (aux.getVertice().equals (vertice2))
                buscado = aux;
            aux = aux.getSigAdyacente();
        }
        
        if (buscado != null){ // Existe un arco entre ambos vertices
            realizado = buscado.agregarEtiqueta(etiqueta); // Se agrega etiqueta si no exista una igual.
            
            if (realizado){
                aux = vertice2.getPrimerAdy();
                buscado = null;
            
                // Se recorre la lista de adyacencia del otro vertice.
                while (aux != null && buscado == null){
                    if (aux.getVertice().equals (vertice1))
                        buscado = aux;
                    aux = aux.getSigAdyacente();
                }
                
                buscado.agregarEtiqueta(etiqueta);
            }
        }
        else{
            vertice1.setPrimerAdy (new NodoAdy (vertice2, vertice1.getPrimerAdy(), etiqueta));
        }
            
        return realizado;
    }
    
    private NodoVert buscarPrimeraAparicionVertice (Object origen, Object destino){
        /* Metodo privado que retorna el primer vertice que contiene a alguno de los
           elementos pasado por parametro.*/
        
        NodoVert buscado = null, vertActual = this.inicio;
        Object elemActual;
        
        // Se busca en la lista de vertices.
        while (vertActual != null && buscado == null)
        {
            elemActual = vertActual.getElem();
            if (elemActual.equals(origen) || elemActual.equals(destino))
                buscado = vertActual;
            else
                vertActual = vertActual.getSigVertice();
        }
        
        return buscado;
    }
    
    public boolean eliminarArco (Object origen, Object destino, int etiqueta){
        /* Metodo que elimina de la estructura el arco que une a los vertices
           origen y destino */
        
        boolean realizado = false;
        
        if (this.inicio != null && !origen.equals(destino)){
            
            NodoVert aux = buscarPrimeraAparicionVertice (origen, destino);
            
            if (aux != null){ // Uno de los vertices fue encontrado.
                // Se identifica que vertice se encontro.
                if (aux.getElem().equals(origen))
                    realizado = eliminarArcoAux (aux, destino, etiqueta);
                else
                    realizado = eliminarArcoAux (aux, origen, etiqueta);
            }
        } 
        return realizado;
    }
    
    private boolean eliminarArcoAux (NodoVert encontrado, Object buscado, int etiqueta){
        /* Metodo privado que elimina de la lista de adyacencia de "encontrado"
           al "buscado" en caso que este exista.*/
        
        boolean realizado = true;
        NodoAdy adyacActual = encontrado.getPrimerAdy();
        
        if (adyacActual != null){
        
            if (adyacActual.getVertice().getElem().equals(buscado)){
                // Caso especial: el primer adyacente es el buscado.
                realizado = adyacActual.eliminarEtiqueta(etiqueta);
                if (realizado && adyacActual.esEtiquetaVacia()) // Si se elimino la ultima etiqueta, se elimina el nodo adyac.
                    encontrado.setPrimerAdy(adyacActual.getSigAdyacente());
            }
            else{
                NodoAdy adyacAnterior = null;
                // Se busca el vertice.
                while (adyacActual != null && !(adyacActual.getVertice().getElem().equals(buscado))){
                    adyacAnterior = adyacActual;
                    adyacActual = adyacActual.getSigAdyacente();
                }

                if (adyacActual != null){ // Se encontro el vertice, por lo tanto se elimina el arco. 
                    realizado = adyacActual.eliminarEtiqueta(etiqueta);
                    if (realizado && adyacActual.esEtiquetaVacia()) // Si se elimino la ultima etiqueta, se elimina el nodo adyac.
                        adyacAnterior.setSigAdyacente(adyacActual.getSigAdyacente()); 
                }
                else
                    realizado = false;
            } 
            
            if (realizado)
                // Operacion inversa: Se elimina en la lista de adyac. del arco del eliminado a la etiqueta del encontrado.
                desconectarArcoConEtiqueta (adyacActual.getVertice(), encontrado, etiqueta);
        }
        else
            realizado = false;
        
        return realizado;
    }
    
    private boolean desconectarArcoConEtiqueta(NodoVert vertActual, NodoVert buscado, int etiqueta){
        // Metodo encargado de eliminar la etiqueta pasada por parametro.
        
        boolean realizado;
        NodoAdy adyacActual = vertActual.getPrimerAdy();
        
        if (adyacActual != null){
            
             if (adyacActual.getVertice().equals(buscado)){
                // Caso especial: el primer adyacente es el buscado.
                realizado = adyacActual.eliminarEtiqueta(etiqueta);
                if (realizado && adyacActual.esEtiquetaVacia()) // Si se elimino la ultima etiqueta, se elimina el nodo adyac.
                    vertActual.setPrimerAdy(adyacActual.getSigAdyacente());
            }
            else{
                NodoAdy adyacAnterior = null;
                // Se busca el vertice.
                while (adyacActual != null && !(adyacActual.getVertice().equals(buscado))){
                    adyacAnterior = adyacActual;
                    adyacActual = adyacActual.getSigAdyacente();
                }

                if (adyacActual != null){ // Se encontro el vertice, por lo tanto se elimina el arco. 
                    realizado = adyacActual.eliminarEtiqueta(etiqueta);
                    if (realizado && adyacActual.esEtiquetaVacia()) // Si se elimino la ultima etiqueta, se elimina el nodo adyac.
                        adyacAnterior.setSigAdyacente(adyacActual.getSigAdyacente()); 
                }
                else
                    realizado = false;
            } 
        }
        else
            realizado = false;
        
        return realizado;
    }
    
    public boolean existeArco (Object origen, Object destino)
    {
        /* Metodo que verifica si existe un arco que une a los dos vertices pasados
           por parametro */
        
        boolean existe = false;
        
        if (this.inicio != null && !origen.equals(destino)){
            NodoVert aux = buscarPrimeraAparicionVertice (origen, destino);
            if (aux != null) // Uno de los vertices fue encontrado.
            {
                if (aux.getElem().equals(origen))
                    existe = verificarArco (aux, destino);
                else
                    existe = verificarArco(aux, origen);
            }
        }
        
        return existe;
    }
    
    private boolean verificarArco (NodoVert aux, Object buscado){
        // Metodo privado encargado de verificar si existe el arco que une a los vertices.
        
        boolean seguir = true;
        NodoAdy adyacActual = aux.getPrimerAdy();
        
        // Se recorre lista de adyacencia.
        while (adyacActual != null && seguir){
        
            if (adyacActual.getVertice().getElem().equals (buscado))
                seguir = false;
            else
                adyacActual = adyacActual.getSigAdyacente();
        }
            
        return !seguir;
    }
    
    public boolean esVacio(){
        // Metodo que verifica si la estructura esta vacia.
        return (this.inicio == null);
    }      
    
    public Object obtenerElem(Object clave){
        // Metodo que retorna el elemento cuya clave es la pasada por parametro.
        
        NodoVert nodoBuscado = ubicarVertice(this.inicio, clave);
        Object elem = null;
        
        if (nodoBuscado != null)
            elem = nodoBuscado.getElem();
        
        return elem;
    }
    
    public boolean verificarCaminoConMaxArcos(Object origen, Object destino, int maxArcos){
        /* Metodo que verifica si existe un camino entre los vertices origen y
           destino en, como maximo, maxArcos. */
        
        boolean verificacion = false;
        
        if (this.inicio != null && !origen.equals(destino) && maxArcos > 0){
        
            Lista visitados = new Lista();
            NodoVert encontrado = buscarPrimeraAparicionVertice (destino, origen);
        
            if (encontrado != null){ // Uno de los vertices fue encontrado.
            
                // Se identifica al vertice encontrado.
                if (encontrado.getElem().equals(origen))
                    verificacion = recorrerConMaxArcos (encontrado, destino, visitados, maxArcos, 0);
                else
                    verificacion = recorrerConMaxArcos (encontrado, origen, visitados, maxArcos, 0);
            }
        }
            
        return verificacion;
    }
    
    private boolean recorrerConMaxArcos (NodoVert aux, Object buscado, Lista visitados, int maxArcos, int cantArcos){
        /* Metodo encargado de encontrar el vertice buscado, a su vez que verifica,
           si existe camino menor o igual a maxArcos. */
        
        boolean verificacion = false;
        
        if (aux != null){
        
            if (aux.getElem().equals(buscado))
                verificacion = true; // Caso base, se encontro elemento.
            else{
                /* Si es menor al maximo, se sigue recorriendo la estructura,
                   caso contrario, se ha superado el maximo*/
                if (cantArcos < maxArcos){
                
                    visitados.insertar (aux.getElem(), 1); // Se agrega vertice a la lista de visitados.
                    NodoAdy adyac = aux.getPrimerAdy();
                  
                    while (adyac != null && !verificacion){
      
                        // Mientras no se encuentre el vertice buscado.
                        if (visitados.localizar(adyac.getVertice().getElem()) < 0)
                            verificacion = recorrerConMaxArcos (adyac.getVertice(), buscado, visitados, maxArcos, cantArcos + 1);      

                        adyac = adyac.getSigAdyacente();
                    }
                }
            }
        }
        
        return verificacion; 
    }      
       
    public Lista caminoConMenorPeso (Object origen, Object destino)
    {
        /* Metodo que devuelve una lista con el camino con menor peso de la estructura
           entre los vertices origen y destino */
        
        Lista camino = new Lista();
        
        if (this.inicio != null && !origen.equals(destino))
        {
            // Se busca el vertice destino.
            NodoVert encontrado = ubicarVertice(this.inicio, destino);
            if (encontrado != null){ // El vertice fue encontrado.
                
                Lista visitados = new Lista();
                PesoCamino peso = new PesoCamino();
                camino = caminoMenorPesoAux (encontrado, origen, visitados, camino, peso, 0);
                
            }
        }
         
        return camino;         
    }
    
    private Lista caminoMenorPesoAux (NodoVert aux, Object buscado, Lista vis, Lista camino, PesoCamino pesoCam, int pesoActual){
        /* Metodo privado encargado de encontrar el vertice buscado, a su vez que se determina el camino con menor peso.*/
        
        vis.insertar(aux.getElem(), 1);
        
        if (aux.getElem().equals(buscado) && (pesoCam.getPesoCamino() == 0 || pesoActual < pesoCam.getPesoCamino())){
            // Caso base: se encontro el elemento. Se guarda el camino y su peso en caso sea el menor.
            pesoCam.setPesoCamino(pesoActual);
            camino = vis.clone();
        }
        else{
        
            NodoAdy ady = aux.getPrimerAdy();
            int res, pesoMenor;
            
            while (ady != null){
            
                // Se recorre los vertices adyacentes de aux.
                pesoMenor = pesoCam.getPesoCamino();
                res = pesoActual + ady.buscarEtiquetaMenor();
                
                if (vis.localizar (ady.getVertice().getElem()) < 0 && (pesoMenor == 0 || res < pesoMenor)){
                    /* Se recorre el adyacente en caso que no haya sido visitado
                       y res sea menor al peso del camino encontrado */
                    camino = caminoMenorPesoAux (ady.getVertice(), buscado, vis, camino, pesoCam, res);
                    vis.eliminar(1);
                }
                
                ady = ady.getSigAdyacente();
            }
        } 
        
        return camino;
    }
    
    public Lista listarEnProfundidad()
    {
        Lista visitados = new Lista();
        NodoVert vertActual = this.inicio;
        
        while (vertActual != null)
        {
            if (visitados.localizar (vertActual.getElem())<0)
                listarEnProfundidad (vertActual, visitados);
            
            vertActual = vertActual.getSigVertice();
        }
        
        return visitados;
    }
    
    private void listarEnProfundidad (NodoVert vertActual, Lista visitados){
    
        if (vertActual != null){
        
            visitados.insertar (vertActual.getElem(), visitados.longitud() + 1);
            NodoAdy adyacActual = vertActual.getPrimerAdy();
            
            while (adyacActual != null){
                
                if (visitados.localizar (adyacActual.getVertice().getElem()) < 0)
                    listarEnProfundidad (adyacActual.getVertice(), visitados);
                            
                adyacActual = adyacActual.getSigAdyacente();
            }
        }
    }
  
    public Lista listarEnAnchura() {
        // Lista los vertices del grafo en orden de recorrido en anchura (BFS).

        Lista visitados = new Lista();
        NodoVert vertActual = this.inicio;

        // recorremos todos los componentes conexos
        while (vertActual != null) {
            if (visitados.localizar(vertActual.getElem()) < 0) {
                listarEnAnchura(vertActual, visitados);
            }
            vertActual = vertActual.getSigVertice();
        }

        return visitados;
    }

    private void listarEnAnchura(NodoVert inicio, Lista visitados) {
        Queue<NodoVert> cola = new LinkedList<>();

        // marcar y encolar el primer vértice
        visitados.insertar(inicio.getElem(), visitados.longitud() + 1);
        cola.add(inicio);

        while (!cola.isEmpty()) {
            NodoVert v = cola.poll();

            // recorrer adyacentes de v
            NodoAdy ady = v.getPrimerAdy();
            while (ady != null) {
                NodoVert vecino = ady.getVertice();

                // si no fue visitado
                if (visitados.localizar(vecino.getElem()) < 0) {
                    visitados.insertar(vecino.getElem(), visitados.longitud() + 1);
                    cola.add(vecino);
                }

                ady = ady.getSigAdyacente();
            }
        }
    }

    public String toString()
    {
        // Metodo que genera una cadena con la estructura actual del grafo.
        String grafo;
        
        if (this.inicio != null)
            grafo = toStringAux ();
        else
            grafo = "Grafo vacio.";
        
        return grafo;
    }
    
    private String toStringAux ()
    {
        String cadena = "";
        NodoVert vertActual = this.inicio;
        
        // Se recorre la lista de vertices.
        while (vertActual != null)
        {
            cadena += "VERTICE: \n" + vertActual.getElem().toString() + "\nLISTA DE ADYACENCIA: \n " +
                    stringListaDeAdyacencia(vertActual) + "\n";
            vertActual = vertActual.getSigVertice();
        }
        
        return cadena;
    }
    
    private String stringListaDeAdyacencia (NodoVert vertActual)
    {
        // Metodo encargado de generar un string con los adyacentes del vertActual.
        
        String cadena = "";
        NodoAdy adyacActual = vertActual.getPrimerAdy();
        
        // Se recorre la lista de adyacencia.
        while (adyacActual != null){
        
            cadena += "\n" + adyacActual.getVertice().getElem().toString();
            adyacActual = adyacActual.getSigAdyacente();
        }
        
        return cadena;
    }
    
}
