package TP3.DivideYVenceras;

public class mergesort {
    public static void main(String args[]){
        int arr[] = {38, 27, 43, 3, 9, 82, 10};

        int n= arr.length;

        mergeSort(arr);
        
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }


    // Merges two sorted arrays into one sorted array
    // Input: Arrays B[0..p-1] and C[0..q-1] both sorted
    // Output: Sorted array A[0..p+q-1] of the elements of B and C
    static void merge(int B[], int C[], int A[]){
        int i = 0;
        int j = 0;
        int k = 0;

        int p = B.length;
        int q = C.length;


        while(i<p && j<q){
            if(B[i]<=C[j]){
                A[k]=B[i];
                i++;
            }else{
                A[k]=C[j];
                j++;
            }
            k++;
        }

        if(i==p){
            while(j < q){
                A[k] = C[j];
                j++;
                k++;
            }
        }else{
            while(i < p){
                A[k] = B[i];
                i++;
                k++;
            }
        }
    }

    // Sorts array A[0..n-1] by recursive mergesort
    // An array A[0..n-1] of orderable elements
    // Output: Array A[0..n-1] sorted in nondecreasing order
    static void mergeSort(int A[]){
        int n = A.length;

        if (n>1) {
            // Find the middle point
            int m = n / 2;

            // Create temp arrays
            int sizeB = m;
            int sizeC = n - m;
            int B[] = new int[sizeB];
            int C[] = new int[sizeC];
 
            // Copy data to temp arrays
            for (int i = 0; i < m; i++){
                B[i] = A[i];
            }
            for (int j = m; j < n; j++){
                C[j-m] = A[j];
            } 

            // Sort first and second halves
            mergeSort(B);
            mergeSort(C); 

            // Merge the sorted halves
            merge(B,C,A);
        }
    }
}
