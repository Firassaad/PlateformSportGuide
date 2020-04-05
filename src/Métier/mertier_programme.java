/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Métier;

import Entities.Exercice;
import Entities.Programme;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class mertier_programme {

    public static void add_prog(String Libellé, int rep_deb, int rep_inter, int rep_exper) throws SQLException {
        Connection con;
        con = GetConnection();
        String sql = "INSERT INTO programme ( Libellé , Rep_debutant ,	Rep_intermediaire,Rep_expert) VALUES (?,?,?,?)";

        PreparedStatement statement = con.prepareStatement(sql);
        //statement.setInt(1, 123);
        statement.setString(1, Libellé);
        statement.setInt(2, rep_deb);
        statement.setInt(3, rep_inter);
        statement.setInt(4, rep_exper);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new programme was inserted successfully!");
        }

    }

    public static List<Exercice> getExByProg(int id) throws SQLException {
        List<Exercice> l = new ArrayList<Exercice>();
        Connection con;
        con = GetConnection();
        String sql = "SELECT * FROM exercice WHERE programme=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSetMetaData resultMeta = statement.getMetaData();
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Exercice e = new Exercice();
            e.setId(result.getInt("id"));
            e.setLibellé(result.getString("Libellé"));
            e.setRep_debutant(result.getInt("rep_debutant"));
            e.setRep_intermidiare(result.getInt("rep_intermidiare"));
            e.setRep_expert(result.getInt("rep_expert"));
            e.setProgramme(result.getInt("programme"));
            e.setVideo(result.getString("video"));

            l.add(e);
        }
        /* for (int j = 0; j < l.size(); j++) {
         System.out.println(l.get(j).toString());
         }*/
        return l;

    }

    public static void Update_programme(int id, String Libellé, String Repétition) throws SQLException {
        Connection con;
        con = GetConnection();
        String sql = "UPDATE `programme` SET `Libellé`=?,`Repétition`=? WHERE `id`=? ";
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setString(1, Libellé);
        statement.setString(2, Repétition);
        statement.setInt(3, id);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("A programme  was updated successfully!");
        }
    }

    public static void delete_programme(int id) throws SQLException {
        // JDBC exécutant Delete
        Connection con;
        con = GetConnection();
        String sql = "DELETE FROM programme WHERE id=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A programme was deleted successfully!");
        }
    }

    public static Connection GetConnection() {
        String name, pass, url;
        Connection con = null;
        // Connection con = null;  
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Coach", "root", "");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println(e.toString());
        }
        return con;
    }
     public static List<Programme> Afficher_Prog() throws SQLException {
        List<Programme> l = new ArrayList<Programme>();
        Connection con;
        con = GetConnection();
        Statement state = con.createStatement();
        //L'objet ResultSet contient le résultat de la requête SQL
        ResultSet result = state.executeQuery("SELECT * FROM programme");
        //On récupère les MetaData
        ResultSetMetaData resultMeta = result.getMetaData();   
        
        while (result.next()) {
        
            Programme p = new Programme();
            p.setId(result.getInt("id"));
            p.setLibellé(result.getString("Libellé"));
            p.setRep_debutant(result.getInt("Rep_debutant"));
            p.setRep_intermediaire(result.getInt("Rep_intermediaire"));
            p.setRep_expert(result.getInt("Rep_expert"));
           
            l.add(p);
  

        }
                  for (int j = 0; j < l.size(); j++) {
                System.out.println(l.get(j).toString());
            }
        return l;

    }

}
