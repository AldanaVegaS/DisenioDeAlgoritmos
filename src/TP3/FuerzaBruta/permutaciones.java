package TP3.FuerzaBruta;

import java.util.ArrayList;
import java.util.List;

public class permutaciones {

    public static void main(String[] args) {
        char letras[] = {'A','B','C','D','E'};
        int permutacionesValidas = 0;
        
        List <String> resultado = new ArrayList();

        System.out.println("PERMUTACIONES CON FUERZA BRUTA:");
        permutacionesValidas=permutaciones(letras, 0, resultado,permutacionesValidas);
        System.out.println("Permutaciones totales: "+resultado.size());
        System.out.println("Lista de permutaciones: "+resultado.toString());
        System.out.println("Permutaciones totales sin vocales juntas: "+permutacionesValidas);


        System.out.println("\n--------------------------------------------------");


        resultado = new ArrayList();
        permutacionesValidas = 0;
        System.out.println("PERMUTACIONES CON PODA:");
        permutacionesValidas=permutacionesPoda(letras, 0, resultado,permutacionesValidas);
        System.out.println("Permutaciones totales: "+resultado.size());
        System.out.println("Lista de permutaciones: "+resultado.toString());
        System.out.println("Permutaciones totales sin vocales juntas: "+permutacionesValidas);
    }


    /*Genera todas las permutaciones posibles y cuenta aquellas que no tienen vocales juntas */
    public static int permutaciones(char letras[], int inicio, List <String> resultado, int permutacionesValidas){
        if(inicio==letras.length){
            resultado.add(new String(letras));
            if(!vocalesJuntas(letras, letras.length-1)){
                permutacionesValidas++;  
            }
            return permutacionesValidas;
        }

        for (int i = inicio; i < letras.length; i++) {
            intercambiar(letras, inicio, i);
            permutacionesValidas = permutaciones(letras, inicio + 1, resultado, permutacionesValidas);
            intercambiar(letras, inicio, i);
        }
        return permutacionesValidas;
    }


    public static int permutacionesPoda(char letras[], int inicio, List <String> resultado, int permutacionesValidas){
        if(inicio+1==letras.length){
            if(!vocalesJuntas(letras, letras.length-1 )){
                permutacionesValidas++;
                resultado.add(new String(letras));
            }
            return permutacionesValidas;
        }

        for (int i = inicio; i < letras.length; i++) {
            intercambiar(letras, inicio, i);
            if(!vocalesJuntas(letras, inicio)){
                permutacionesValidas = permutacionesPoda(letras, inicio + 1, resultado, permutacionesValidas);
            }
            intercambiar(letras, inicio, i);
        }
        return permutacionesValidas;
    }


    private static void intercambiar(char letras[], int inicio, int i){
        char tmp = letras[inicio];
        letras[inicio]=letras[i];
        letras[i]=tmp;
    }

    private static boolean vocalesJuntas(char letras[], int limite){
        for (int i = 0; i < limite; i++) {
            if(esVocal(letras[i]) && esVocal(letras[i+1])){
                return true;
            }
        }
        return false;
    }

    private static boolean esVocal(char letra){
        return letra == 'A' || letra == 'E' || letra == 'I' || letra == 'O' || letra == 'U';
    }
}
