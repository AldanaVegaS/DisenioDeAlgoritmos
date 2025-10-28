package TP2.EmpresasEstudiantes;

public class testSC {
    public static void main(String[] args) {

        int i, n;
        int[] matches;
        boolean isCompany = false;

        int[][] prefStudent = {
            {3, 1, 0, 2},
            {2, 3, 0, 1},
            {0, 1, 3, 2},
            {2, 1, 0, 3}
        };

        int[][] rankCompany = {
            {0, 2, 1, 3},
            {1, 0, 2, 3},
            {1, 0, 3, 2},
            {3, 2, 0 ,1}
        };


        GaleShapleyAlgorithm gsa = new GaleShapleyAlgorithm(prefStudent, rankCompany, isCompany);
        matches = gsa.stableMatching();
        n = matches.length;

        if (isCompany) {
            System.out.println("\n(Empresa, Estudiante)");
        } else {
            System.out.println("\n(Estudiante, Empresa)");
        }

        for (i = 0; i < n; i++)
            System.out.println("(" + i + ", " + matches[i] + ")");
    }
}
