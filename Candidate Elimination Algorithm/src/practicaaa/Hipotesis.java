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
public class Hipotesis {

    private String[] hipotesis;

    public Hipotesis(String[] s) {
        hipotesis = s;
    }

    public String[] get_hipotesis() {
        String[] h = new String[hipotesis.length];
        h = hipotesis.clone();
        return h;
    }

    public void set_hipotesis(String s, int atributo) {
        hipotesis[atributo] = s;
    }

    public boolean satisface(String[] ejemplo) {

        int contador = 0;

        for (int i = 0; i < ejemplo.length - 1; i++) {
            if (hipotesis[i].equals("VACIO")) {
                contador--;
            } else if (hipotesis[i].equals("TODO")) {
                contador++;
            } else if (hipotesis[i].equals(ejemplo[i])) {
                contador++;
            }
        }
        return (contador == (ejemplo.length - 1));
    }

    public boolean esMasGeneral(Hipotesis h) {
        String[] aux = this.get_hipotesis();
        String[] auxH = h.get_hipotesis();

        for (int i = 0; i < aux.length; i++) {
            if (aux[i].equals(auxH[i])) {

            } else if (aux[i].equals("TODO") && !(auxH[i].equals("TODO"))) {

            } else if (aux[i].equals("TODO") && auxH[i].equals("TODO")) {

            } else if (!(aux[i].equals("TODO")) && auxH.equals("TODO")) {
                return false;
            } else if (aux[i].equals("VACIO") && auxH[i].equals("VACIO")) {

            } else if (aux[i].equals("VACIO") && !(auxH[i].equals("VACIO"))) {
                return false;
            } else if (!(aux[i].equals("TODO")) && !(aux[i].equals("VACIO")) && !(auxH[i].equals("TODO")) && !(auxH[i].equals("VACIO")) && !(aux[i].equals(auxH[i]))) {
                return false;
            } else if (!(aux[i].equals("TODO")) && !(aux[i].equals("VACIO")) && auxH[i].equals("TODO")) {
                return false;
            }

        }
        return true;
    }

}
