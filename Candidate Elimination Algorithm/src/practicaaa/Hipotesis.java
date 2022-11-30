/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaaa;

import java.util.ArrayList;
import java.util.Arrays;

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
            if(this.hipotesis[i].equals("TODO") || (this.hipotesis[i].equals(ejemplo[i])))
            {
                
            }
            else{
                return false;
            }
        }
        return true;
    }

    public boolean esMasGeneral(Hipotesis h) {
        String[] aux = this.get_hipotesis().clone();
        String[] auxH = h.get_hipotesis().clone();
        int contador = 0;
        for (int i = 0; i < aux.length; i++) {

            if (aux[i].equals("TODO") || aux[i].equals(auxH[i])) {

            } else {
                return false;
            }
        }

        return true;
    }

    public boolean esMasEspecifica(Hipotesis h) {
        String[] aux = this.get_hipotesis();
        String[] auxH = h.get_hipotesis();
        int contador = 0;
        for (int i = 0; i < aux.length; i++) {
            if (aux[i].equals("VACIO") || aux[i].equals(auxH[i]) || auxH[i].equals("TODO")) {

            } else {
                return false;
            }
        }
        return true;
    }
    
    public boolean hipotesisIgual(Hipotesis h)
    {
        String[] aux=this.hipotesis.clone();
        String[] auxH=h.get_hipotesis().clone();
        int contador=0;
        
        for(int i=0;i<aux.length;i++)
        {
            if(aux[i].equals(auxH[i]))
                contador++;
        }
        
        return (contador==aux.length);
    }
    
    @Override
    public String toString(){
        String s="<";
        for(int i=0;i<this.hipotesis.length;i++)
        {
            s=s+this.hipotesis[i]+", ";
        }
        s=s+">  ";
        return s;
    }

    /**
     *
     * @return
     */
    @Override 
    public int hashCode()
    {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hipotesis other = (Hipotesis) obj;
        return Arrays.deepEquals(this.hipotesis, other.hipotesis);
    }
}
