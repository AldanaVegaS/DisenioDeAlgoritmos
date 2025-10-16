package TP2.GaleShapley;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.BitSet;

public class GaleShapleyAlgorithm {
    
    // Implementacion con pares prohibidos.

    private int n; // Cantidad de hombres y mujeres
    private BitSet[] forbidden; // Matriz de pares prohibidos
    private int[][] prefM; // rankM[i][j] es el ranking de la mujer j para el hombre i
    private int[][] rankW; // rankW[j][i] es el ranking del hombre i para la mujer j
    private int[] currentMatchW; // currentMatchW[j] es el hombre actualmente emparejado con la mujer j
    private int[] currentMatchM; // currentMatchM[i] es la mujer actualmente emparejada con el hombre i

    public GaleShapleyAlgorithm(BitSet[] forbidden, int[][] prefM, int[][] rankW) {
        this.n = prefM.length;
        this.forbidden = forbidden;
        this.prefM = prefM;
        this.rankW = rankW;
        this.currentMatchW = new int[n];
        this.currentMatchM = new int[n];
    }

    // Devuelve un array donde el índice es el hombre y el valor es la mujer emparejada
    public int[] stableMarriage() {
        
        int m, w, candidate;
        int[] nextProposal = new int[n]; // nextProposal[i] es el indice de la siguiente mujer a la que el hombre i le propondrá
        Queue<Integer> freeMen = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            currentMatchW[i] = -1; // Inicialmente, todas las mujeres están libres
            currentMatchM[i] = -1; // Inicialmente, todos los hombres están libres
            nextProposal[i] = 0; // Inicialmente, cada hombre propone a la primera mujer en su lista
            freeMen.add(i); // Todos los hombres comienzan libres
        }

        while (!freeMen.isEmpty()) {
            m = freeMen.peek(); // Tomar un hombre libre

            w = -1;
            boolean found = false;

            while (nextProposal[m] < n && !found) { // Buscar la siguiente mujer no prohibida
                candidate = prefM[m][nextProposal[m]];
                if (!forbidden[m].get(candidate)) { // Si el par (m, candidate) no está prohibido
                    w = candidate; // La siguiente mujer a la que el hombre m le propondrá
                    found = true;
                }
                nextProposal[m]++;
            }

            if (!found) {
                freeMen.poll(); // Si el hombre m ha propuesto a todas las mujeres, se saca de la cola
                continue;
            }
            
            nextProposal[m]++; // Preparar la siguiente propuesta para el hombre m

            if (currentMatchW[w] == -1) { // La mujer w está libre
                currentMatchM[m] = w;
                currentMatchW[w] = m;
                freeMen.poll();
            } else {
                int mCurrent = currentMatchW[w];
                // La mujer w ya está emparejada, verificar si prefiere al nuevo hombre
                if (rankW[w][m] < rankW[w][mCurrent]) {
                    // La mujer w prefiere al nuevo hombre m
                    currentMatchW[w] = m;
                    currentMatchM[m] = w;
                    currentMatchM[mCurrent] = -1; // El hombre mCurrent queda libre
                    freeMen.poll(); // El hombre m ya no está libre
                    freeMen.add(mCurrent); // El hombre actualmente emparejado vuelve a estar libre
                }
                // Si la mujer w prefiere a su actual pareja, el hombre m sigue libre y propone a la siguiente mujer
            }
        }
        return currentMatchM; // currentMatchM[i] es la mujer emparejada con el hombre i
    }

    // Metodo para obtener una lista de hombres solteros
    public List<Integer> getSingleMen() {
        List<Integer> singles = new ArrayList<>();
        for (int i = 0; i < currentMatchM.length; i++)
            if (currentMatchM[i] == -1)
                singles.add(i);
        return singles;
    }

    // Metodo para obtener una lista de mujeres solteras
    public List<Integer> getSingleWomen() {
        List<Integer> singles = new ArrayList<>();
        for (int i = 0; i < currentMatchW.length; i++)
            if (currentMatchW[i] == -1)
                singles.add(i);
        return singles;
    }

}
