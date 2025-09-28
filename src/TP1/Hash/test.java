package TP1.Hash;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class test {
    
    public static void main(String[] args) {
        ContadorPalabrasHash contador = new ContadorPalabrasHash();

        String archivo = "/home/dana/Documentos/Materias/Diseño de algoritmos/DisenioDeAlgoritmos/texto.txt"; //hay que asegurarse de que este archivo exista en el directorio de trabajo

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            
            String linea;
            while ((linea = br.readLine()) != null) {
                contador.contarPalabras(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        contador.imprimirConteo();
    }

}
