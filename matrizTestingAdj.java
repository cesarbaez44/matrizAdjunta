
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class matrizTestingAdj {

    public static void printMatrix(double[][] matriz){
        if(matriz == null){
            System.out.println("(matriz nula)");
            return;
        }
        for(int i=0; i<matriz.length; i++){
            for(int j=0; j<matriz[i].length; j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        matrizFilesAdj files = new matrizFilesAdj();
        BufferedReader bufer = new BufferedReader(new InputStreamReader(System.in));
        String fileName;

        System.out.println("matriz adjunta");
        System.out.print("Escribe el nombre del archivo de la matriz: ");
        fileName = bufer.readLine();

        double[][] matriz = files.fileToMatrix(fileName);
        System.out.println("Matriz original:");
        printMatrix(matriz);

        double[][] adj = files.adjoint(matriz);
        System.out.println("\nMatriz adjunta:");
        printMatrix(adj);

        System.out.print("\nEscribe el nombre del archivo de salida: ");
        fileName = bufer.readLine();
        files.writeMatrixToFile(fileName, adj);

        System.out.println("Proceso terminado. El archivo se guardó en C:\\archivos\\" + fileName);
    }
}