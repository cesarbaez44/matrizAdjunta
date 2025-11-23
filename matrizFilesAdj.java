import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
}
    