import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class matrizFilesAdj {

    // Lee la matriz desde el archivo
    public double[][] fileToMatrix(String fileName){
        double[][] matriz = null;
        try{
            File file = new File("C:\\archivos\\" + fileName);
            FileReader reader = new FileReader(file);
            BufferedReader bufer = new BufferedReader(reader);

            // Contar filas
            int rows = 0;
            String linea;
            while((linea = bufer.readLine()) != null){
                rows++;
            }
            reader.close();

            // Volver a leer para llenar matriz
            reader = new FileReader(file);
            bufer = new BufferedReader(reader);
            matriz = new double[rows][rows];
            int i = 0;
            while((linea = bufer.readLine()) != null){
                String[] parts = linea.trim().split("\\s+");
                for(int j=0; j<parts.length; j++){
                    matriz[i][j] = Double.parseDouble(parts[j]);
                }
                i++;
            }
            reader.close();

        } catch(Exception e){
            System.out.println("Error al leer el archivo: " + e.toString());
        }
        return matriz;
    }
    // Escribir matriz en archivo
    public void writeMatrixToFile(String fileName, double[][] matriz){
        try{
            FileWriter file = new FileWriter("C:\\archivos\\" + fileName);
            PrintWriter writer = new PrintWriter(file);
            for(int i=0; i<matriz.length; i++){
                for(int j=0; j<matriz[i].length; j++){
                    writer.print(matriz[i][j] + " ");
                }
                writer.println();
            }
            file.close();
        } catch(Exception e){
            System.out.println("Error al escribir el archivo: " + e.toString());
        }
    }

    // Calcula la matriz adjunta 
    public double[][] adjoint(double[][] A){
        int n = A.length;
        double[][] adj = new double[n][n];

        if(n == 1){
            adj[0][0] = 1;
            return adj;
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                double[][] temp = getMinor(A, i, j);
                double det = determinant(temp);
                adj[j][i] = ((i+j)%2==0 ? 1 : -1) * det;
            }
        }
        return adj;
    }

    // Obtiene la submatriz eliminando fila p y columna q
    private double[][] getMinor(double[][] A, int p, int q){
        int n = A.length;
        double[][] minor = new double[n-1][n-1];
        int i2=0;
        for(int i=0; i<n; i++){
            if(i==p) continue;
            int j2=0;
            for(int j=0; j<n; j++){
                if(j==q) continue;
                minor[i2][j2] = A[i][j];
                j2++;
            }
            i2++;
        }
        return minor;
    }

    // Determinante recursivo
    private double determinant(double[][] A){
        int n = A.length;
        if(n==1) return A[0][0];
        if(n==2) return A[0][0]*A[1][1] - A[0][1]*A[1][0];

        double det=0;
        for(int j=0; j<n; j++){
            double[][] minor = getMinor(A,0,j);
            det += ( (j%2==0?1:-1) * A[0][j] * determinant(minor) );
        }
        return det;
    }
}
    