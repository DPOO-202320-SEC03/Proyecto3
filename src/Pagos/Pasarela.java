package Pagos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Pasarela {

    // dataCSV es el encabezado del archivo CSV que se creará para guardar las trazas, este es el default
    public String dataCSV = "nombreTitular;numCuenta;ccv;montoDeducido;numTransaccionInternaPasarela;resultadoTransaccion";
    public String datosParaGUI = "Nombre del titular;Numero de cuenta;CCV;Costo Alquiler";

    public Pasarela() {
    }
    
    public ArrayList<String> cargarPasarelas(String filePath) throws IOException {
        ArrayList<String> pasarelas = new ArrayList<>();
         try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String pasarela;
            while ((pasarela = reader.readLine()) != null) {
                pasarelas.add(pasarela);
            }
            cargarTrazas(pasarelas);
            return pasarelas;
        }
    }

    public void cargarCSVTrazaIndividual(String pasarela, String dataCSV) throws IOException {
        String workingDir = System.getProperty("user.dir");
        String pathTraza = workingDir + File.separator + "data" + File.separator + "pagos" + File.separator + "Traza" + pasarela + ".csv";
        File file = new File(pathTraza);
        if (!file.exists()) {
            file.createNewFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathTraza, true))) {
                writer.write(dataCSV);
            }
        }
    }

    public void cargarTrazas(ArrayList<String> pasarelas) throws IOException {
        for (String pasarela : pasarelas) {
            try {
                Class<?> clase = Class.forName("Pagos."+pasarela);
                Object pasarelaNueva = clase.getDeclaredConstructor().newInstance();
                if (Pasarela.class.isAssignableFrom(clase)) {
                    Pasarela pasarelaInstance = (Pasarela) pasarelaNueva;
                    pasarelaInstance.cargarCSVTrazaIndividual(pasarela, pasarelaInstance.dataCSV);
                } else {
                    System.out.println("La clase " + pasarela + " no extiende de Pasarelas.");
                }
            } catch (IOException e) {
                System.out.println("Hubo un error de lectura");
            } catch (ClassNotFoundException e) {
                System.out.println("No existe la clase " + pasarela);
            } catch (Exception e) {
                System.out.println("Hubo otro error construyendo la agenda telefónica: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // Este metodo puede ser editado por cada pasarela para agregarle datos adicionales a la traza

    public String agregarTraza(String nombrePasarela,String[] datos) {

        String workingDir = System.getProperty("user.dir");
        String pathTraza = workingDir + File.separator + "data" + File.separator + "pagos" + File.separator + "Traza" + nombrePasarela + ".csv";
        // hace un try por si la lista no tiene los parametros necesarios
        try {
            String nombreTitular = datos[0];
            String numCuenta = datos[1];
            String ccv = datos[2];
            int montoDeducido = Integer.parseInt(datos[3]);
            // Aca se implementaria el chequeo real de si fue o no exitosa :)
            String resultadoTransaccion = "Exitosa";

            BufferedWriter writer = new BufferedWriter(new FileWriter(pathTraza, true));
            long numTransaccionL = Files.lines(Paths.get(pathTraza)).count();
            String numTransaccion = String.valueOf(numTransaccionL);
            writer.newLine();
            writer.write(nombreTitular + ";" + numCuenta + ";" + ccv + ";" + montoDeducido + ";" + numTransaccion + ";" + resultadoTransaccion);
            writer.close();
            return "Transacción realizada con éxito";
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error en el número de parametros proporcionados");
            return "Error en el número de parametros proporcionados";
        } catch (Exception e) {
            System.out.println("No se pudo agregar la traza");
            return "No se pudo agregar la traza";
        }
    }
}
