/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaaa;

import FicherosCSV.ParserCSV;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import javax.print.attribute.HashAttributeSet;

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
        System.out.println("Indique el nombre de la clase positiva: ");
        String clasePos=sc.nextLine();
        dataset = ParserCSV.leeCSV(nombre);
        int tamanioDataset = dataset.size();
        int tamanioEjemplo = dataset.get(0).length;

        //Algoritmo eliminacion de candidatos
        ArrayList<ListaHipotesis> h = Algoritmos.eliminacion_candidatos(tamanioDataset, tamanioEjemplo, dataset,clasePos);
        ListaHipotesis G = h.get(0);
        ListaHipotesis S = h.get(1);

        Set<Hipotesis> GSet=new HashSet<Hipotesis>();
       
        for(int i=0;i<G.getListaHipotesis().size();i++)
        {
            GSet.add(G.getListaHipotesis().get(i));
        }
        System.out.print("El conjunto G es {");
        Iterator<Hipotesis>it=GSet.iterator();
        while(it.hasNext())
        {
            System.out.print(it.next().toString());
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
