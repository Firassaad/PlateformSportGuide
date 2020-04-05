/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class FXML_calcul_CalController implements Initializable {

    @FXML
    private CheckBox check_h;
    @FXML
    private CheckBox check_f;
    @FXML
    private TextField txt_poids;
    @FXML
    private TextField text_taille;
    @FXML
    private TextField text_age;
    float resultCal = 0;
    @FXML
    private Label label_result;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void OnCalcul_calories(ActionEvent event) {
        float result = 0;
        if (check_h.isSelected()) {
            System.out.println("h isselected");
            float a = Métier.metier_exercice.Nbre_Cal_Haaris_et_Benedict("Homme", Float.parseFloat(txt_poids.getText()), Float.parseFloat(text_taille.getText()), Integer.parseInt(text_age.getText()));
            float b = Métier.metier_exercice.Nbre_Cal_Roza_et_Shizgal("Homme", Float.parseFloat(txt_poids.getText()), Float.parseFloat(text_taille.getText()), Integer.parseInt(text_age.getText()));
            result = a + b / 2;
            System.out.println("========>"+result);
        } else if (check_f.isSelected()) {
            System.out.println("f isselected h");
            float a = Métier.metier_exercice.Nbre_Cal_Haaris_et_Benedict("Femme", Float.parseFloat(txt_poids.getText()), Float.parseFloat(text_taille.getText()), Integer.parseInt(text_age.getText()));
            float b = Métier.metier_exercice.Nbre_Cal_Roza_et_Shizgal("Homme", Float.parseFloat(txt_poids.getText()), Float.parseFloat(text_taille.getText()), Integer.parseInt(text_age.getText()));
            result = a + b / 2;
               System.out.println("========>"+result);
        }
        resultCal = result;
        label_result.setText(Float.toString(resultCal));
    }

}
