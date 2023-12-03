package Pagos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Payu extends Pasarela {
    public Payu() {
        super.dataCSV = "nombreTitular;numCuenta;ccv;montoDeducido;diaTransaccion;numTransaccionInternaPasarela;resultadoTransaccion";
        super.datosParaGUI = "Nombre del titular;Numero de cuenta;CCV;Costo Alquiler;Dia de la transaccion";
    }

    @Override
    public String agregarTraza(String nombrePasarela,String[] parametros) {

        String workingDir = System.getProperty("user.dir");
        String pathTraza = workingDir + File.separator + "data" + File.separator + "pagos" + File.separator + "Traza" + nombrePasarela + ".csv";
        // hace un try por si la lista no tiene los parametros necesarios
        try {
            String nombreTitular = parametros[0];
            String numCuenta = parametros[1];
            String ccv = parametros[2];
            int montoDeducido = Integer.parseInt(parametros[3]);
            String dia = parametros[4];
            // Aca se implementaria el chequeo real de si fue o no exitosa :)
            String resultadoTransaccion = "Exitosa";
            

            BufferedWriter writer = new BufferedWriter(new FileWriter(pathTraza, true));
            long numTransaccionL = Files.lines(Paths.get(pathTraza)).count();
            String numTransaccion = String.valueOf(numTransaccionL);
            writer.newLine();
            writer.write(nombreTitular + ";" + numCuenta + ";" + ccv + ";" + montoDeducido + ";" + numTransaccion + ";" + resultadoTransaccion + ";" + dia);
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
