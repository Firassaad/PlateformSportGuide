/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Métier;

import Entities.Exercice;
import static Métier.mertier_programme.GetConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MedAli.BENROMDHANE
 */
public class metier_exercice {

    public static void add_exercice(String Libellé, int prog, int rep_debutant, int rep_intermidiare, int rep_expert, String video) throws SQLException {
        Connection con;
        con = GetConnection();
        String sql = "INSERT INTO exercice ( Libellé , programme , rep_debutant , rep_intermidiare , rep_expert ,video ) VALUES (?,?,?,?,?,?)";

        PreparedStatement statement = con.prepareStatement(sql);
        //statement.setInt(1, 123);
        statement.setString(1, Libellé);
        statement.setInt(2, prog);
        statement.setInt(3, rep_debutant);
        statement.setInt(4, rep_intermidiare);
        statement.setInt(5, rep_expert);
        statement.setString(6, video);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new exercice( was inserted successfully!");
        }

    }

    public static void Update_exercice(int id, String Libellé, int programme) throws SQLException {
        Connection con;
        con = GetConnection();
        String sql = "UPDATE `exercice` SET `Libellé`=?,`programme`=? WHERE `id`=? ";
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setString(1, Libellé);
        statement.setInt(2, programme);
        statement.setInt(3, id);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("A exercice  was updated successfully!");
        }
    }

    public static void delete_exercice(int id) throws SQLException {
        // JDBC exécutant Delete
        Connection con;
        con = GetConnection();
        String sql = "DELETE FROM exercice WHERE id=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A exercice was deleted successfully!");
        }
    }

    public static List<Exercice> Afficher_exercice() throws SQLException {
        List<Exercice> l = new ArrayList<Exercice>();
        Connection con;
        con = GetConnection();
        Statement state = con.createStatement();
        //L'objet ResultSet contient le résultat de la requête SQL
        ResultSet result = state.executeQuery("SELECT * FROM Exercice");
        //On récupère les MetaData
        ResultSetMetaData resultMeta = result.getMetaData();

        while (result.next()) {

            Exercice e = new Exercice();

            e.setLibellé(result.getString("Libellé"));

            l.add(e);

        }
        for (int j = 0; j < l.size(); j++) {
            System.out.println(l.get(j).toString());
        }
        return l;

    }

    public static List<Exercice> Afficher_exercice1() throws SQLException {
        List<Exercice> l = new ArrayList<Exercice>();
        Connection con;
        con = GetConnection();
        Statement state = con.createStatement();
        //L'objet ResultSet contient le résultat de la requête SQL
        ResultSet result = state.executeQuery("SELECT * FROM Exercice");
        //On récupère les MetaData
        ResultSetMetaData resultMeta = result.getMetaData();

        while (result.next()) {

            Exercice e = new Exercice();

            e.setLibellé(result.getString("Libellé"));
            e.setProgramme(result.getInt("programme"));
            e.setRep_debutant(result.getInt("rep_debutant"));
            e.setRep_intermidiare(result.getInt("rep_intermidiare"));
            e.setRep_expert(result.getInt("rep_expert"));
            e.setVideo(result.getString("video"));
            l.add(e);

        }
        for (int j = 0; j < l.size(); j++) {
            System.out.println(l.get(j).toString());
        }
        return l;

    }

    public static String GetProgLibelleByIdProg(int id) throws SQLException {
        Connection con;
        con = GetConnection();
        String sql = "Select * FROM programme WHERE id=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        Exercice e = new Exercice();
        while (result.next()) {
            e.setLibellé(result.getString("Libellé"));
        }
        System.out.println(e.getLibellé());
        return e.getLibellé();
    }

    public static List<Exercice> getListDeb(List<Exercice> l1, int f) {
        List<Exercice> le = new ArrayList<>();
        List<Exercice> lv = new ArrayList<>();
        List<Exercice> la = new ArrayList<>();
        // do {
        for (int i = 0; i < f; i++) {
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
        int a = 0;
        for (int k = 0; k < le.size(); k++) {

            System.out.println("le    :" + a + "  :" + le.get(k).toString());
            a++;
        }
        int b = 0;
        for (int k = 0; k < la.size(); k++) {

            System.out.println("la  :" + b + "  :" + la.get(k).toString());
            b++;
        }
        return le;

    }

    ///////////////////////////////////////////////Debut Partie nombre calories/////////////////////////////////////
    public static float Nbre_Cal_Haaris_et_Benedict(String sexe, float poids, float taille, int age) {
        float resultat = 0;
        if (sexe.equals("Homme")) {
            resultat = (float) (66.5 + (float) (13.8 * poids) + (float) (5 * taille) - (float) (6.8 * age));
        } else if (sexe.equals("Femme")) {
            resultat = (float) (655.1 + (float) (9.6 * poids) + (float) (1.9 * taille) - (float) (4.7 * age));
        }
        return resultat;
    }

    public static float Nbre_Cal_Roza_et_Shizgal(String sexe, float poids, float taille, int age) {
        float resultat = 0;
        if (sexe.equals("Homme")) {
            resultat = (float) (88.362 + (float) (13.397 * poids) + (float) (4.799 * taille) - (float) (5.677 * age));
        } else if (sexe.equals("Femme")) {
            resultat = (float) (447.593 + (float) (9.247 * poids) + (float) (3.098 * taille) - (float) (4.330 * age));
        }
        return resultat;
    }

        ///////////////////////////////////////////////Fin Partie nombre calories/////////////////////////////////////
}
