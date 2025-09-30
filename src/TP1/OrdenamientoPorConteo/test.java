package TP1.OrdenamientoPorConteo;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        int[] arr = {62,31,84,96,19,47};
        System.out.println("Arreglo original:"+Arrays.toString(arr));
        System.out.println("Arreglo ordenado:"+Arrays.toString(ordenarPorConteo(arr))+"\n");

        int[] arr2 = {5,4,3,2,1,0};
        System.out.println("Arreglo original:"+Arrays.toString(arr2));
        System.out.println("Arreglo ordenado:"+Arrays.toString(ordenarPorConteo(arr2))+"\n");

        int[] arr3 = {1,2,3,4,5,6};
        System.out.println("Arreglo original:"+Arrays.toString(arr3));
        System.out.println("Arreglo ordenado:"+Arrays.toString(ordenarPorConteo(arr3))+"\n");

        int[] arr4 = {20,10,7,3,2,7};
        System.out.println("Arreglo original:"+Arrays.toString(arr4));
        System.out.println("Arreglo ordenado:"+Arrays.toString(ordenarPorConteo(arr4))+"\n");

       /*  int[] arr5 = {2,2,2,2,4,4,4,4,1};
        System.out.println("Arreglo original:"+Arrays.toString(arr5));
        System.out.println("Arreglo ordenado:"+Arrays.toString(ordenarPorConteo(arr5))+"\n"); */


        //PRUEBAS CON CHAR
        char[] arr6 = {'b','a','d','c','f','e'};
        System.out.println("Arreglo original:"+Arrays.toString(arr6));
        System.out.println("Arreglo ordenado:"+Arrays.toString(ordenarPorConteoChar(arr6))+"\n");

        char[] arr7 = {'b','a','b','c','a','e'};
        System.out.println("Arreglo original:"+Arrays.toString(arr7));
        System.out.println("Arreglo ordenado:"+Arrays.toString(ordenarPorConteoChar(arr7))+"\n");

        char[] arr8 = {'b','a','d','A','f','e'};
        System.out.println("Arreglo original:"+Arrays.toString(arr8));
        System.out.println("Arreglo ordenado:"+Arrays.toString(ordenarPorConteoChar(arr8))+"\n");

        char[] arr9 = {'a','B','b','A'};
        System.out.println("Arreglo original:"+Arrays.toString(arr9));
        System.out.println("Arreglo ordenado:"+Arrays.toString(ordenarPorConteoChar(arr9))+"\n");

    }

    public static int[] ordenarPorConteo(int[] arr) {
        int n = arr.length;

        int contador[] = new int[n]; // Suponiendo que los números están en el rango 0-100
        int s[] = new int[n];

        for (int i = 0; i < n; i++) {
            contador[i] = 0;
        }

        for(int i=0;i<=n-2;i++){
            System.out.println("Elemento a comparar: "+arr[i]);
            for(int j=i+1;j<=n-1;j++){
                System.out.println("    Comparando con: "+arr[j]);
                if(arr[i]<arr[j]){
                    contador[j]++;
                    System.out.println("        "+arr[i]+" < "+arr[j]+" entonces contador["+j+"]++");
                }else{
                    contador[i]++;
                    System.out.println("        "+arr[i]+" >= "+arr[j]+" entonces contador["+i+"]++");
                }
            }
        }

        for(int i=0;i<=n-1;i++){
            s[contador[i]] = arr[i];
        }

        return s;
    }


    public static char[] ordenarPorConteoChar(char[] arr) {
        int n = arr.length;

        int contador[] = new int[n]; // Suponiendo que los números están en el rango 0-100
        char s[] = new char[n];

        for (int i = 0; i < n; i++) {
            contador[i] = 0;
        }

        for(int i=0;i<=n-2;i++){
            System.out.println("Elemento a comparar: "+arr[i]);
            for(int j=i+1;j<=n-1;j++){
                System.out.println("    Comparando con: "+arr[j]);
                if(Character.toLowerCase(arr[i])< Character.toLowerCase(arr[j])){
                    contador[j]++;
                    System.out.println("        "+arr[i]+" < "+arr[j]+" entonces contador["+j+"]++");
                }else{
                    contador[i]++;
                    System.out.println("        "+arr[i]+" >= "+arr[j]+" entonces contador["+i+"]++");
                }
            }
        }

        for(int i=0;i<=n-1;i++){
            s[contador[i]] = arr[i];
        }

        return s;
    }
}
