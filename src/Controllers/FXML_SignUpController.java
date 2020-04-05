/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Medali.benromdhane
 */
public class FXML_SignUpController implements Initializable {

    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_tlp;
    @FXML
    private TextField txt_mail;
    @FXML
    private TextField txt_password;
    @FXML
    private TextField txt_confirm_password;
    @FXML
    private Button btn_inscrit;
    @FXML
    private TextField txt_login;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onInscript(ActionEvent event) throws SQLException {
        if (txt_password.getText().equals(txt_confirm_password.getText())) {
            String nom_prenom = txt_prenom.getText() + "" + txt_nom.getText();
            String email = txt_mail.getText();
            String login = txt_login.getText();
            String pswd = txt_password.getText();
            ;
            Métier.Métier_user.add_user(nom_prenom, login, pswd, email, Integer.parseInt(txt_tlp.getText()));
            txt_prenom.setText("");
            txt_nom.setText("");
            txt_mail.setText("");
            txt_login.setText("");
            txt_password.setText("");
            txt_tlp.setText("");

        } else {
            System.out.println("diff mdps");
        }
    }

}
