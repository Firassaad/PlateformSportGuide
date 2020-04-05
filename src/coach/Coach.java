/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coach;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Firas.SAADAOUI
 */
public class Coach extends Application {
     public static Scene s;
     public static Stage st;
     public static Parent root;
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXMLAdmin.fxml"));
      
        // Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXML_coach1.fxml"));
        
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXML_calcul_Cal.fxml"));
        
        // root = FXMLLoader.load(getClass().getResource("/FXML/FXML_login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXML_SignUp.fxml"));
         //Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXML_Super.fxml"));
       // Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXML_admin_exercice.fxml"));
        // root = FXMLLoader.load(getClass().getResource("/FXML/FXML_Accueil.fxml"));
     
        
       s = new Scene(root);
        
        stage.setScene(s);
        st = stage;
        st.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
