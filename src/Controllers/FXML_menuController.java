/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class FXML_menuController implements Initializable {
    @FXML
    private Button btn_prog;
    @FXML
    private Button btn_ex;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onclick_bnt_prog(ActionEvent event) throws IOException {
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/FXMLAdmin.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
    }

    @FXML
    private void on_click_btn_ex(ActionEvent event) throws IOException {
                   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/FXML_admin_exercice.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
    }
    
}
