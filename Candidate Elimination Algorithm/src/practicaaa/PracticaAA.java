/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaaa;

import FicherosCSV.ParserCSV;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class PracticaAA {

    public static void main(String[] args) throws IOException {
        //General
        ArrayList<String[]> dataset = new ArrayList<String[]>();
        System.out.println("Inserte el nombre del fichero a ejecutar: ");
        Scanner sc = new Scanner(System.in);
        String nombre = sc.nextLine();
        dataset = ParserCSV.leeCSV(nombre);
        int tamanioDataset = dataset.size();
        int tamanioEjemplo = dataset.get(0).length;

        //Algoritmo eliminacion de candidatos
        ArrayList<ListaHipotesis> h = Algoritmos.eliminacion_candidatos(tamanioDataset, tamanioEjemplo, dataset);
        ListaHipotesis G = h.get(0);
        ListaHipotesis S = h.get(1);

        System.out.print("El conjunto G es {");
        for (int i = 0; i < G.getListaHipotesis().size(); i++) {
            Hipotesis hG = G.getListaHipotesis().get(i);
            System.out.print("<");
            for (int j = 0; j < hG.get_hipotesis().length; j++) {
                System.out.print(hG.get_hipotesis()[j] + ", ");
            }
            System.out.print(">,");
        }
        System.out.println("}");
        System.out.println(" ");

        System.out.print("El conjunto S es {");
        if (S.getListaHipotesis().size() > 0) {
            Hipotesis hS = S.getListaHipotesis().get(0);
            System.out.print("<");
            for (int i = 0; i < hS.get_hipotesis().length; i++) {
                System.out.print(hS.get_hipotesis()[i] + ", ");
            }
            System.out.println(">}");
        } else {
            System.out.println("}");
        }

    }

}
