package TP2.GaleShapley;
import java.util.BitSet;

public class test {

    public static void main(String[] args){

        BitSet[] forbidden = new BitSet[3];
        for (int i = 0; i < 3; i++) {
            forbidden[i] = new BitSet(3);
        }

        // El hombre 1 no puede estar con la mujer 1
        forbidden[1].set(1, true);
        // El hombre 1 no puede estar con la mujer 2
        forbidden[1].set(2, true);
        // El hombre 0 no puede estar con la mujer 3
        forbidden[1].set(3, true);
        // El hombre 0 no puede estar con la mujer 3
        forbidden[0].set(3, true);

        int[][] rankM = {
            {0, 1, 2}, // Preferencias del hombre 0
            {1, 0, 2}, // Preferencias del hombre 1
            {0, 1, 2}  // Preferencias del hombre 2
        };

        int[][] rankW = {
            {2, 0, 1}, // Preferencias de la mujer 0
            {0, 1, 2}, // Preferencias de la mujer 1
            {2, 0, 1}  // Preferencias de la mujer 2
        };

        GaleShapleyAlgorithm gsa = new GaleShapleyAlgorithm(forbidden, rankM, rankW);
        int[] result = gsa.stableMarriage();

        for (int i = 0; i < result.length; i++) {
            System.out.println("Hombre " + i + " está emparejado con Mujer " + result[i]);
        }

        System.out.println("Solteros:");
        System.out.println("Hombres solteros: " + gsa.getSingleMen());
        System.out.println("Mujeres solteras: " + gsa.getSingleWomen());

    }

}
