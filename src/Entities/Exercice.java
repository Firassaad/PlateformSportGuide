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
public class Exercice {

    private int Id;
    private String Libellé;
    private int rep_debutant;
    private int rep_intermidiare;
    private int rep_expert;
    private int programme;
    private String video;

    public Exercice() {
    }

    @Override
    public String toString() {
        return "Exercice{" + "Id=" + Id + ", Libell\u00e9=" + Libellé + ", rep_debutant=" + rep_debutant + ", rep_intermidiare=" + rep_intermidiare + ", rep_expert=" + rep_expert + ", programme=" + programme + ", video=" + video + '}';
    }

    public Exercice(int Id, String Libellé, int rep_debutant, int rep_intermidiare, int rep_expert, int programme, String video) {
        this.Id = Id;
        this.Libellé = Libellé;
        this.rep_debutant = rep_debutant;
        this.rep_intermidiare = rep_intermidiare;
        this.rep_expert = rep_expert;
        this.programme = programme;
        this.video = video;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
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
        return rep_debutant;
    }

    public void setRep_debutant(int rep_debutant) {
        this.rep_debutant = rep_debutant;
    }

    public int getRep_intermidiare() {
        return rep_intermidiare;
    }

    public void setRep_intermidiare(int rep_intermidiare) {
        this.rep_intermidiare = rep_intermidiare;
    }

    public int getRep_expert() {
        return rep_expert;
    }

    public void setRep_expert(int rep_expert) {
        this.rep_expert = rep_expert;
    }

    public int getProgramme() {
        return programme;
    }

    public void setProgramme(int programme) {
        this.programme = programme;
    }

}
