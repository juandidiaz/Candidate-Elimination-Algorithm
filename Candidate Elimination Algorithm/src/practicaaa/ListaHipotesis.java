/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaaa;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ListaHipotesis implements Cloneable{

    private ArrayList<Hipotesis> hipotesis;

    public ListaHipotesis() {
        hipotesis = new ArrayList<Hipotesis>();
    }
   
  
    public void addHipotesis(Hipotesis h) {
        hipotesis.add(h);
    }

    public void removeHipotesis(int j) {
        hipotesis.remove(j);
    }
    
       public void removeHipotesis2(Object j) {
        hipotesis.remove(j);
    }
   public void setListaHipotesis(ArrayList<Hipotesis> h)
   {
       this.hipotesis=h;
   }

    public ArrayList<Hipotesis> getListaHipotesis() {
        ArrayList<Hipotesis> h = (ArrayList<Hipotesis>) hipotesis.clone();
        return h;
    }

    public static ArrayList<Hipotesis> generalizacionesMinimales(Hipotesis s, String[] ejemplo) {

        ArrayList<Hipotesis> hipotesisGen = new ArrayList<Hipotesis>();
        String[] hypotheses = s.get_hipotesis().clone();
        for (int i = 0; i < ejemplo.length - 1; i++) {
            if (hypotheses[i].equals("TODO")) {

            } else if (hypotheses[i].equals("VACIO")) {
                s.set_hipotesis(ejemplo[i], i);
            } else if (!(hypotheses[i].equals("TODO")) && !(hypotheses[i].equals("VACIO")) && hypotheses[i].equals(ejemplo[i])) {

            } else if (!(hypotheses[i].equals("TODO")) && !(hypotheses[i].equals("VACIO")) && !(hypotheses[i].equals(ejemplo[i]))) {
                s.set_hipotesis("TODO", i);
            }
        }
        hipotesisGen.add(s);
        return hipotesisGen;
    }

    public static ArrayList<Hipotesis> especializacionesMinimales(ArrayList<String[]> dataset, Hipotesis g, String[] ejemplo) {

        ArrayList<Hipotesis> hypotheses = new ArrayList<Hipotesis>();

        ArrayList<String[]> opciones = new ArrayList<String[]>();
        for (int i = 0; i < dataset.get(0).length - 1; i++) {
            String[] aux = new String[1];
            aux[0] = dataset.get(0)[i];
            opciones.add(i, aux);
        }

        for (int i = 1; i < dataset.size(); i++) {
            for (int j = 0; j < dataset.get(i).length - 1; j++) {
                int busca = 0;

                boolean encontrado = false;
                String[] str_options = opciones.get(j);

                while (busca < str_options.length && !encontrado) {
                    if (str_options[busca].equals(dataset.get(i)[j])) {
                        encontrado = true;
                    }

                    busca++;
                }

                if (!encontrado) {
                    String[] aux = new String[1];
                    aux[0] = dataset.get(i)[j];
                    String[] aux2 = opciones.get(j);
                    String[] aux3 = new String[opciones.get(j).length + 1];
                    for (int r = 0; r < aux2.length; r++) {
                        aux3[r] = aux2[r];
                    }
                    aux3[aux3.length - 1] = aux[0];
                    opciones.set(j, aux3);
                }

            }
        }

        String[] gAux = g.get_hipotesis().clone();

        for (int j = 0; j < ejemplo.length - 1; j++) {
            if (gAux[j].equals("TODO")) {
                String Vc2 = ejemplo[j];

                if (opciones.get(j).length == 1 && opciones.get(j)[0].equals(Vc2)) {
                    String[] gAux2 = gAux.clone();
                    gAux2[j] = "VACIO";
                    Hipotesis h = new Hipotesis(gAux2);
                    hypotheses.add(h);
                } else if (opciones.get(j).length > 1) {
                    for (int r = 0; r < opciones.get(j).length; r++) {
                        String[] gAux2 = gAux.clone();
                        gAux2[j] = opciones.get(j)[r];
                        Hipotesis h = new Hipotesis(gAux2);
                        hypotheses.add(h);
                    }
                }

            } else if (gAux[j].equals("VACIO")) {

            } else if (!(gAux[j].equals("TODO")) && !(gAux[j].equals("VACIO"))) {
                String[] gAux2 = gAux.clone();
                gAux2[j] = "VACIO";
                Hipotesis h = new Hipotesis(gAux2);
                hypotheses.add(h);
            }
        }

        return hypotheses;
    }
    
    @Override
    public String toString()
    {
        String s="";
        s=s+"{";
        for(int i=0;i<this.hipotesis.size();i++)
        {
            Hipotesis g=this.hipotesis.get(i);
            s=s+g.toString();
        }
        s=s+"}";
        return s;
    }

}
