/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Programme;
import Entities.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class FXML_SuperController implements Initializable {
    @FXML
    private TableView<User> tabViewuser;
    @FXML
    private TableColumn<User, String> colNomPrenom;
    @FXML
    private TableColumn<User, String> colLogin;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, Integer> colTlp;
    @FXML
    private Label label_nomprenom;
    @FXML
    private Label label_email;
    @FXML
    private Label label_tlp;
    @FXML
    private ComboBox<String> ComboRole;
    @FXML
    private Button attribuerRoleBtn;
    @FXML
    private TableColumn<User, String> colRole;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            buildData();
        } catch (SQLException ex) {
            Logger.getLogger(FXML_SuperController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String roles[] =  {"admin","user"}; 
  
        ComboRole.setItems(FXCollections.observableArrayList(roles));
    }    
        private void buildData() throws SQLException {

        colNomPrenom.setCellValueFactory(new PropertyValueFactory<User, String>("Nom_prenom"));
        colLogin.setCellValueFactory(new PropertyValueFactory<User, String>("Login"));
        colEmail.setCellValueFactory(new PropertyValueFactory<User, String>("Email"));
        colTlp.setCellValueFactory(new PropertyValueFactory<User, Integer>("Telephone"));
        colRole.setCellValueFactory(new PropertyValueFactory<User, String>("role"));


        tabViewuser.getItems().setAll(Métier.Métier_user.GetListOfUser());

    }

    @FXML
    private void mousePressed(MouseEvent event) {
        label_nomprenom.setText(tabViewuser.getSelectionModel().getSelectedItem().getNom_prenom());
        label_email.setText(tabViewuser.getSelectionModel().getSelectedItem().getEmail());
        Integer j =tabViewuser.getSelectionModel().getSelectedItem().getTelephone();
        label_tlp.setText(j.toString());
//        System.out.println(tabViewuser.getSelectionModel().getSelectedItem().getId()+" "+ ComboRole.getSelectionModel().getSelectedItem().toString());
    }

    @FXML
    private void OnClick_attribuerRoleBtn(ActionEvent event) throws SQLException {
        Métier.Métier_user.affecRole(tabViewuser.getSelectionModel().getSelectedItem().getId() ,ComboRole.getSelectionModel().getSelectedItem().toString());
        buildData();
        
    }
}
