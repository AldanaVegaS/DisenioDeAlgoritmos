package TP3;

public class MultipDP {
    
    public static class Result {
        public final long[][] m; // costes mínimos
        public final int[][] s;  // puntos de división
        public Result(long[][] m, int[][] s) {
            this.m = m;
            this.s = s;
        }
    }

    public static Result matrixChainOrder(int[] p) {
        int n = p.length - 1;
        long[][] m = new long[n][n];
        int[][] s = new int[n][n];

        // Inicializar m[i][i] = 0 (costo de multiplicar una sola matriz)
        for (int i = 0; i < n; i++) {
            m[i][i] = 0;
        }

        // l = longitud de la cadena (desde 2 hasta n)
        for (int l = 2; l <= n; l++) {
            // i va de 0 a n-l
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1; // índice j
                m[i][j] = Long.MAX_VALUE;
                // probar todas las posibles divisiones k entre i y j-1
                for (int k = i; k <= j - 1; k++) {
                    // costo = m[i][k] + m[k+1][j] + p[i]*p[k+1]*p[j+1]
                    long q = m[i][k] + m[k + 1][j] + 
                             (long)p[i] * p[k + 1] * p[j + 1];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        return new Result(m, s);
    }

    public static void printOptimalParens(int[][] s, int i, int j) {
        if (i == j) {
            System.out.print("A" + (i + 1)); // A1, A2, ...
        } else {
            System.out.print("(");
            printOptimalParens(s, i, s[i][j]);
            System.out.print(" x ");
            printOptimalParens(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    // Método auxiliar para mostrar la matriz m (costes)
    public static void printMatrix(long[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (i > j) {
                    System.out.printf("%12s", "-");
                } else {
                    System.out.printf("%12d", m[i][j]);
                }
            }
            System.out.println();
        }
    }

}
