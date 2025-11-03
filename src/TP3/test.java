package TP3;

public class test {
    public static void main(String[] args) {

        int res;
        int arr[] = {
            5, 12, 7, 10, 6, 14, 9, 3, 8, 11, 13,
            4, 15, 6, 7, 12, 10, 5, 9, 8, 11, 14,
            13, 2, 4
        };
        
        double startTime, endTime;

        System.out.println("Test Recursivo:");
        startTime = System.currentTimeMillis();
        res = MultipRecursion.matrixMultiplication(arr);
        endTime = System.currentTimeMillis();

        System.out.println("Costo: " + res);
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " ms.");


        System.out.println("Test Programa Dinamica:");
        startTime = System.currentTimeMillis();
        MultipDP.Result mCost = MultipDP.matrixChainOrder(arr);
        endTime = System.currentTimeMillis();

        System.out.println("Costo: " + mCost.m[0][arr.length - 2]);
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " ms.");

    }
}
