/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FicherosCSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ParserCSV {

    public static final String separador = ";";
    public static final String delimitador = "\"";

    public static ArrayList<String[]> leeCSV(String nombre) throws FileNotFoundException, IOException {
        ArrayList<String[]> dataset = new ArrayList<String[]>();
        BufferedReader br = null;
        try {
            nombre = nombre + ".csv";
            File fichero = new File("FicherosCSV");
            String ruta = fichero.getAbsolutePath() +"\\..\\src\\FicherosCSV\\" + nombre;
            br = new BufferedReader(new FileReader(ruta));
            String linea = br.readLine();

            while (linea != null) {
                String[] valores = linea.split(separador);
                valores = quitar_delimitadores(valores);

                String[] ejemplo = valores.clone();
                dataset.add(ejemplo);
                linea = br.readLine();
            }
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo");
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return dataset;

    }

    private static String[] quitar_delimitadores(String[] valores) {
        String devuelve[] = new String[valores.length];

        for (int i = 0; i < devuelve.length; i++) {
            devuelve[i] = valores[i].replaceAll("^" + delimitador, "").replaceAll(delimitador + "$", "");
        }
        return devuelve;
    }

}
