/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Medali.benromdhane
 */
public class User {

    private int Id;
    private String Nom_prenom;
    private String Login;
    private String Mdp;
    private String Email;
    private int Telephone;
    private String role;

    public User() {
    }

    public User(int Id, String Nom_prenom, String Login, String Mdp, String Email, int Telephone, String role) {
        this.Id = Id;
        this.Nom_prenom = Nom_prenom;
        this.Login = Login;
        this.Mdp = Mdp;
        this.Email = Email;
        this.Telephone = Telephone;
        this.role = role;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNom_prenom() {
        return Nom_prenom;
    }

    public void setNom_prenom(String Nom_prenom) {
        this.Nom_prenom = Nom_prenom;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getMdp() {
        return Mdp;
    }

    public void setMdp(String Mdp) {
        this.Mdp = Mdp;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getTelephone() {
        return Telephone;
    }

    public void setTelephone(int Telephone) {
        this.Telephone = Telephone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "Id=" + Id + ", Nom_prenom=" + Nom_prenom + ", Login=" + Login + ", Mdp=" + Mdp + ", Email=" + Email + ", Telephone=" + Telephone + ", role=" + role + '}';
    }
    
}