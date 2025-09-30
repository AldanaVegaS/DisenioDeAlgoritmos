package TP1.Diccionario.Hash;

public class Main {
    public static void main(String[] args) {
        DiccionarioSinonimos diccionario = new DiccionarioSinonimos();

        //Insertar palabras
        diccionario.insertarPalabra("hola");
        diccionario.insertarPalabra("casa");
        diccionario.insertarPalabra("cosa");
        diccionario.insertarPalabra("perro");
        diccionario.insertarPalabra("gato");
        diccionario.insertarPalabra("pero");
        diccionario.insertarPalabra("hoy");
        diccionario.insertarPalabra("hombre");
        diccionario.insertarPalabra("hormiga");
        diccionario.insertarPalabra("cantar");
        diccionario.insertarPalabra("cantarle");
        diccionario.insertarPalabra("cantarles");
        diccionario.insertarPalabra("cosa");// palabra repetida

        System.out.println("Palabras en el diccionario:");
        diccionario.listarPalabras();

        //Agregar sinónimos
        diccionario.agregarSinonimo("casa", "hogar");
        diccionario.agregarSinonimo("casa", "vivienda");
        diccionario.agregarSinonimo("perro", "can");
        diccionario.agregarSinonimo("hoy", "actualmente");
        diccionario.agregarSinonimo("animal", "bestia"); // Palabra que no existe en el diccionario

        //Mostrar sinónimos
        diccionario.mostrarSinonimos("casa");
        diccionario.mostrarSinonimos("perro");
        diccionario.mostrarSinonimos("gato");//Palabra sin sinónimos
        diccionario.mostrarSinonimos("animal");//Palabra que no existe en el diccionario

        System.out.println("\nPalabras en el diccionario junto a sus sinonimos:");
        diccionario.listarPalabrasConSinonimos();

    }
}

