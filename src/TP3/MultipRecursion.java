package TP3;
import java.util.*;

// matrix chain multiplication using recursion
public class MultipRecursion {

    // Matrix Ai has dimension arr[i-1] x arr[i]
    private static int minMultRec(int arr[], int i, int j)
    {

        // If there is only one matrix
        if (i + 1 == j)
            return 0;

        int res = Integer.MAX_VALUE;

        for (int k = i + 1; k < j; k++) {
            int curr = minMultRec(arr, i, k)
                       + minMultRec(arr, k, j)
                       + arr[i] * arr[k] * arr[j];

            res = Math.min(curr, res);
        }

        // Return minimum count
        return res;
    }

    public static int matrixMultiplication(int arr[]){
        int n = arr.length;
        return minMultRec(arr, 0, n - 1);
    }
}