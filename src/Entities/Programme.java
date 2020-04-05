/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author MedAli.BENROMDHANE
 */
public class Programme {

    private int Id;
    private String Libellé;
    private int Rep_debutant;
     private int Rep_intermediaire;
      private int Rep_expert;
    

    public Programme() {
    }

    public Programme(int Id, String Libellé, int Rep_debutant, int Rep_intermediaire, int Rep_expert) {
        this.Id = Id;
        this.Libellé = Libellé;
        this.Rep_debutant = Rep_debutant;
        this.Rep_intermediaire = Rep_intermediaire;
        this.Rep_expert = Rep_expert;
    }

    @Override
    public String toString() {
        return "Programme{" + "Id=" + Id + ", Libell\u00e9=" + Libellé + ", Rep_debutant=" + Rep_debutant + ", Rep_intermediaire=" + Rep_intermediaire + ", Rep_expert=" + Rep_expert + '}';
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getLibellé() {
        return Libellé;
    }

    public void setLibellé(String Libellé) {
        this.Libellé = Libellé;
    }

    public int getRep_debutant() {
        return Rep_debutant;
    }

    public void setRep_debutant(int Rep_debutant) {
        this.Rep_debutant = Rep_debutant;
    }

    public int getRep_intermediaire() {
        return Rep_intermediaire;
    }

    public void setRep_intermediaire(int Rep_intermediaire) {
        this.Rep_intermediaire = Rep_intermediaire;
    }

    public int getRep_expert() {
        return Rep_expert;
    }

    public void setRep_expert(int Rep_expert) {
        this.Rep_expert = Rep_expert;
    }


}
