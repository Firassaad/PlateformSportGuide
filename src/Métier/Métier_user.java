/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Métier;

import Entities.Exercice;
import Entities.Programme;
import Entities.User;
import static Métier.mertier_programme.GetConnection;
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
 * @author Firas
 */
public class Métier_user {

    public static User getUserByID(int id) throws SQLException {
        Connection con;
        con = GetConnection();
        User u = new User();
        String sql = "SELECT * FROM `user`  WHERE `id`=? ";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSetMetaData resultMeta = statement.getMetaData();
        ResultSet result = statement.executeQuery();
        while (result.next()) {

            u.setId(result.getInt("id"));
            u.setNom_prenom(result.getString("Nom_prenom"));
            u.setLogin(result.getString("Login"));
            u.setMdp(result.getString("Mdp"));
            u.setRole(result.getString("role"));

        }
        System.out.println(u.toString());
        return u;

    }
    
        public static List<User> GetListOfUser() throws SQLException {
        List<User> l = new ArrayList<User>();
        Connection con;
        con = GetConnection();
        Statement state = con.createStatement();
        //L'objet ResultSet contient le résultat de la requête SQL
        ResultSet result = state.executeQuery("SELECT * FROM user");
        //On récupère les MetaData
        ResultSetMetaData resultMeta = result.getMetaData();   
        
        while (result.next()) {
        
            User p = new User();
            p.setId(result.getInt("id"));
            p.setNom_prenom(result.getString("Nom_prenom"));
            p.setLogin(result.getString("Login"));
            p.setMdp(result.getString("Mdp"));
            p.setRole(result.getString("role"));
            p.setEmail(result.getString("Email"));
            p.setTelephone(result.getInt("Telephone"));
            l.add(p);
  

        }
                  for (int j = 0; j < l.size(); j++) {
                System.out.println(l.get(j).toString());
            }
        return l;

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

  
      public static void add_user(String Nom_prenom, String Login, String Mdp, String Email, int Telephone ) throws SQLException {
        Connection con;
        con = GetConnection();
        String sql = "INSERT INTO user (Nom_prenom ,Login ,Mdp,Email,Telephone) VALUES (?,?,?,?,?)";

        PreparedStatement statement = con.prepareStatement(sql);
        //statement.setInt(1, 123);
        statement.setString(1, Nom_prenom);
        statement.setString(2, Login);  
        statement.setString(3, Mdp);
        statement.setString(4, Email);
        statement.setInt(5, Telephone);
        
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new user was inserted successfully!");
        }

    }
       public static List<User> Afficher_User() throws SQLException {
        List<User> l = new ArrayList<User>();
        Connection con;
        con = GetConnection();
        Statement state = con.createStatement();
        //L'objet ResultSet contient le résultat de la requête SQL
        ResultSet result = state.executeQuery("SELECT * FROM User");
        //On récupère les MetaData
        ResultSetMetaData resultMeta = result.getMetaData();

        while (result.next()) {

            User e = new User();

            e.setNom_prenom(result.getString("Nom_prenom"));
            e.setLogin(result.getString("Login"));
            e.setEmail(result.getString("Email"));
            e.setTelephone(result.getInt("Telephone"));
           
            l.add(e);

        }
        for (int j = 0; j < l.size(); j++) {
            System.out.println(l.get(j).toString());
        }
        return l;

    }
       
       public static void affecRole(int id, String role) throws SQLException {
        Connection con;
        con = GetConnection();
        String sql = "UPDATE `user` SET `role`=? WHERE `id`=? ";
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setString(1, role);      
        statement.setInt(2, id);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("A role  assignet to user s successfully!");
        }
    }

}
