/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coach;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Firas.SAADAOUI
 */
public class mainTest {

    public static void main(String[] args) throws SQLException {
        // Métier.mertier_programme.add_prog("abdo" , 3);
        //  Métier.mertier_programme.getExByProg(2);
        // Métier.metier_exercice.add_exercice("haltere dorseaux", 2);
        //Métier.Métir_user.getUserByID(1);
        //Métier.Métier_user.GetListOfUser();
        //Métier.metier_exercice.GetProgLibelleByIdProg(7);

        /* List<String> ls = new ArrayList<String>();

         ls.add("firas1");
         ls.add("saadaoui2");
         ls.add("firas3");
         ls.add("firas4");
         ls.add("firas5");
         ls.add("firas6");
         ls.add("firas7");

         List<String> l21 = getListDeb(ls, 4);
         Set s = new HashSet(l21);
         List l22 = new ArrayList<String>(s);
         System.out.println("in" + l22.size());
         for (int k = 0; k < l22.size(); k++) {

         System.out.println("-------->" + l22.get(k).toString());

         }
         */
        int[] T = {5,6,1,2};
        int[] F = {1,6,3,4};
        verifTfarah(T, F);
        verifV(T, F);

    }

    public static void verifTfarah(int[] T, int[] F) {
        int j = 0;
        for (int i = 0; i < F.length; i++) {

            if (F[i] == T[i]) {
                j++;
               // System.out.println("===>" + i);
            }

        }
        System.out.println("nombre T " + j);
    }

    public static void verifV(int[] T, int[] F) {
        // for (int i = 0; i < F.length; i++) {
        int x = 0;
        for (int f = 0; f < F.length; f++) {
            int k = F[f];
            for (int j = 0; j < T.length; j++) {
                if (k == T[j] && f != j) {
                   // System.out.println("=====>pos T" + T[j] + "==========> K  :" + k);
                    x++;
                }
            }
        }
        System.out.println("nombre V  = "+x);
    }

    public static List<String> getListDeb(List<String> l1, int f) {
        List<String> le = new ArrayList<>();
        List<String> lv = new ArrayList<>();
        List<String> la = new ArrayList<>();
        // do {
        for (int i = 0; i < l1.size() - 1; i++) {
            //if (i < j) {
            int f1 = (int) (Math.round(Math.random() * (l1.size())));
            le.add(l1.get(f1));
            for (int k = 0; k < le.size(); k++) {
                if (l1.contains(le.get(k))) {
                    lv = l1;
                    lv.remove(le.get(k));

                }
            }
            // }
        }
        la = le;

        if (la.size() < f) {
            int f1 = (int) (Math.round(Math.random() * (la.size())));
            la.add(lv.get(f1));
        }
        /* for (int k = 0; k < lv.size(); k++) {
         System.out.println("lv  :" + lv.get(k).toString());
         }*/
        for (int k = 0; k < le.size(); k++) {
            System.out.println("le    :" + le.get(k).toString());
        }
        for (int k = 0; k < la.size(); k++) {
            System.out.println("la    :" + la.get(k).toString());
        }
        return le;

    }



    /*  Ces formules sont à pondérer  avec les coefficients suivants selon l’activité physique :
     Peu ou pas d’activité : x 1,2
     Activité 1 à 3 fois par semaine : x 1,375
     Activité 3 à 5 fois par semaine : x 1,55
     Activité 6 à 7 fois par semaine : x1,725*/
}
