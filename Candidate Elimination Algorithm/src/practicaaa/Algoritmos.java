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
public class Algoritmos {

    public static ArrayList<ListaHipotesis> eliminacion_candidatos(int tamanioDataset, int tamanioEjemplo, ArrayList<String[]> dataset) {
        //INICIALIZAR G Y S A LA MAS GENERAL Y ESPECIFICA RESPECTIVAMENTE
        ArrayList<ListaHipotesis> conjuntos = new ArrayList<ListaHipotesis>();
        String[] aux = new String[tamanioEjemplo - 1];

        for (int i = 0; i < tamanioEjemplo - 1; i++) {
            aux[i] = "TODO";
        }

        Hipotesis general = new Hipotesis(aux);

        String[] aux2 = new String[tamanioEjemplo - 1];

        for (int i = 0; i < tamanioEjemplo - 1; i++) {
            aux2[i] = "VACIO";
        }

        Hipotesis especifica = new Hipotesis(aux2);

        ListaHipotesis G = new ListaHipotesis();
        ListaHipotesis S = new ListaHipotesis();

        G.addHipotesis(general);
        S.addHipotesis(especifica);

        //PARA CADA EJEMPLO d del conjunto D
        for (int i = 0; i < tamanioDataset; i++) {
            String[] ejemplo = dataset.get(i);
            if (ejemplo[tamanioEjemplo - 1].equals("+")) { //Si es positivo
                //Eliminar de G cualquier hipotesis inconsistente con d
                ArrayList<Hipotesis> hG = G.getListaHipotesis();
                for (int j = 0; j < G.getListaHipotesis().size(); j++) {

                    boolean cumple = G.getListaHipotesis().get(j).satisface(ejemplo);
                    if (!cumple) {

                        G.removeHipotesis(j);
                        j = j - 1;

                    }

                }
                //Para cada hipotesis s de S inconsistente con d
                ArrayList<Hipotesis> hS = S.getListaHipotesis();
                for (int r = 0; r < hS.size(); r++) {
                    Hipotesis s = hS.get(r);
                    boolean cumple = s.satisface(ejemplo);
                    if (!cumple) {
                        S.removeHipotesis(r); //Elimino de S las inconsistentes
                        //Incluir en S todas las generalizaciones minimales de s
                        //que sean consistentes con d y existe en G una mas general
                        ArrayList<Hipotesis> gMinimal = ListaHipotesis.generalizacionesMinimales(s, ejemplo);
                        for (int it = 0; it < gMinimal.size(); it++) {
                            boolean satisface = gMinimal.get(it).satisface(ejemplo);
                            if (satisface) {
                                S.addHipotesis(gMinimal.get(it));
                            }
                        }
                        //Comprobacion de que en G existe una hipotesis mas general
                        for (int iterator = 0; iterator < G.getListaHipotesis().size(); iterator++) {
                            Hipotesis hAux = G.getListaHipotesis().get(iterator);
                            if (S.getListaHipotesis().get(0).esMasGeneral(hAux)) {
                                G.removeHipotesis(iterator);
                            }
                        }
                        //Aqui deberiamos comprobar que no existe una 
                        //hipotesis mas general en S, sin embargo
                        //como nosotros generalizamos hace que solo haya
                        //una hipotesis en S, por lo tanto es innecesario
                        //realizar la comprobacion
                    }

                }
            } else { //Si el ejemplo no es positivo
                //Eliminar de S cualquier hipotesis inconsistente con d
                ArrayList<Hipotesis> hS2 = S.getListaHipotesis();
                for (int k = 0; k < hS2.size(); k++) {
                    boolean cumple = hS2.get(k).satisface(ejemplo);
                    if (cumple) {
                        S.removeHipotesis(k);
                    }
                }
                //Para cada hipotesis g de G inconsistente con d
                ArrayList<Hipotesis> hG2 = G.getListaHipotesis();
                for (int e = 0; e < hG2.size(); e++) {
                    Hipotesis g = hG2.get(e);
                    boolean cumple = g.satisface(ejemplo);
                    if (cumple) {
                        G.removeHipotesis(e); //Elimino g de G si acepta el ejemplo
                        //Inserto en G todas las especializaciones minimales de g
                        //que sean consistentes con D y de forma que exista en
                        //S una hipotesis mas especifica
                        ArrayList<Hipotesis> eMinimal = ListaHipotesis.especializacionesMinimales(dataset, g, ejemplo);
                        for (int it2 = 0; it2 < eMinimal.size(); it2++) {
                            boolean satisface = eMinimal.get(it2).satisface(ejemplo);
                            if (!satisface) {
                                G.addHipotesis(eMinimal.get(it2));
                            }
                        }
                        if (S.getListaHipotesis().size() > 0) {
                            Hipotesis HS = S.getListaHipotesis().get(0);
                            for (int p = 0; p < G.getListaHipotesis().size(); p++) {
                                boolean EsMasGeneral = G.getListaHipotesis().get(p).esMasGeneral(HS);
                                if (!EsMasGeneral) {
                                    G.removeHipotesis(p);
                                    p = p - 1;
                                }
                            }
                        }
                        //Elimino de G aquellas hipotesis tales que
                        //exista en G otra hipotesis mas especifica
                        for (int t = 0; t < G.getListaHipotesis().size() - 1; t++) {
                            for (int q = t + 1; q < G.getListaHipotesis().size(); q++) {
                                Hipotesis tH = G.getListaHipotesis().get(t);
                                Hipotesis qH = G.getListaHipotesis().get(q);

                                if (tH.esMasGeneral(qH)) {
                                    G.removeHipotesis(t);
                                }
                                if (qH.esMasGeneral(tH)) {
                                    G.removeHipotesis(q);
                                }
                            }
                        }

                    }

                }

            }
        }

        conjuntos.add(G);
        conjuntos.add(S);

        return conjuntos;
    }
}
