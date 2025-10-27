package TP2.EmpresasEstudiantes;
import java.util.Queue;
import java.util.LinkedList;

public class GaleShapleyAlgorithm {
    
    // Implementacion del algoritmo base, sin pares prohibidos. Versión generica.

    private int n; // Cantidad del conjunto A y B (ofertante y receptor)
    private int[][] prefA; // prefA[i][j] es la j-ésima preferencia del ofertante i
    private int[][] rankB; // rankB[j][i] es el ranking de el receptor j para el ofertante i
    private boolean isCompany; // Indica si el conjunto A son empresas (true) o estudiantes (false)

    public GaleShapleyAlgorithm(int[][] prefA, int[][] rankB, boolean isCompany) {
        this.n = prefA.length;
        this.prefA = prefA;
        this.rankB = rankB;
        this.isCompany = isCompany;
    }

    // Devuelve un array donde el índice es el ofertante y el valor es la pareja receptor
    public int[] stableMatching() {
        
        int a, b;
        int[] currentMatchB = new int[n]; // currentMatchB[j] es el ofertante A emparejado con el receptor i
        int[] currentMatchA = new int[n]; // currentMatchA[i] es el receptor B actualmente emparejado con el ofertante i
        int[] nextProposal = new int[n]; // nextProposal[i] es el indice del siguiente receptor a quien el ofertante i le propondrá
        Queue<Integer> freeA = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            currentMatchB[i] = -1; // Inicialmente, todo receptor B esta libre
            currentMatchA[i] = -1; // Inicialmente, todo ofertante A esta libre
            nextProposal[i] = 0; // Inicialmente, cada ofertante A propone al primer receptor B en su lista
            freeA.add(i); // Todos los ofertantes A comienzan libres
        }

        while (!freeA.isEmpty()) {
            a = freeA.peek(); // Tomar un ofertante A libre

            b = prefA[a][nextProposal[a]]; // El siguiente receptor B a la que el ofertante A le propondrá

            if (nextProposal[a] >= n) {
                if (isCompany) {
                    System.out.println("Empresa " + a + " ya no tiene a quién proponer. Se elimina de la cola.");
                } else {
                    System.out.println("Estudiante " + a + " ya no tiene a quién proponer. Se elimina de la cola.");
                }
                continue;
            }

            nextProposal[a]++; // Preparar la siguiente propuesta para el ofertante a

            if (currentMatchB[b] == -1) { // El receptor b está libre
                currentMatchA[a] = b;
                currentMatchB[b] = a;
                freeA.poll();

                // Iteraciones por pantalla
                if (isCompany) {
                    System.out.println(" --> Aceptado (Estudiante libre)");
                } else {
                    System.out.println(" --> Aceptado (Empresa libre)");
                }

            } else {
                int aCurrent = currentMatchB[b];
                // El receptor b ya esta emparejado, verificar si prefiere al nuevo ofertante a

                if (isCompany) {
                    System.out.print(" --> Estudiante emparejado con Empresa " + aCurrent);
                } else {
                    System.out.print(" --> Empresa emparejada con Estudiante " + aCurrent);
                }

                if (rankB[b][a] < rankB[b][aCurrent]) {
                    // El receptor b prefiere al nuevo ofertante a
                    currentMatchB[b] = a;
                    currentMatchA[a] = b;
                    currentMatchA[aCurrent] = -1; // El ofertante aCurrent queda libre
                    freeA.poll(); // El ofertante a ya no está libre
                    freeA.add(aCurrent); // El ofertante actualmente emparejado vuelve a estar libre

                    // Iteraciones por pantalla
                    if (isCompany) {
                        System.out.println(" --> Aceptado (Prefiere Empresa nueva " + a + "). Libera Empresa " + aCurrent);
                    } else {
                        System.out.println(" --> Aceptado (Prefiere Estudiante nuevo " + a + "). Libera Estudiante " + aCurrent);
                    }
                } else {
                    if (isCompany) {
                        System.out.println(" --> Rechazado (Prefiere Empresa actual " + aCurrent + ")");
                    } else {
                        System.out.println(" --> Rechazado (Prefiere Estudiante actual " + aCurrent + ")");
                    }
                }
                // Si el receptor b prefiere a su actual pareja, el ofertante a sigue libre y propone al siguiente receptor.
            }
        }
        return currentMatchA; // currentMatchA[i] es el receptor emparejado con el ofertante i
    }

}
