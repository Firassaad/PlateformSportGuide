/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.User;
import Métier.Métier_user;
import static Métier.Métier_user.GetConnection;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Firas.SAADAOUI
 */
public class FXML_loginController implements Initializable {

    @FXML
    private Button loginButton;
    @FXML
    private TextField username_text;
    @FXML
    private TextField mdp_text;
    @FXML
    private AnchorPane achor;
    @FXML
    private ImageView imageview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void OnLoginButton(ActionEvent event) throws SQLException, IOException, InvocationTargetException, IndexOutOfBoundsException {

        Connection con;
        con = Métier_user.GetConnection();
        List<User> l = Métier_user.GetListOfUser();
        System.out.println("list size " + l.size());
        String login = username_text.getText();
        String mdp = mdp_text.getText();
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getLogin().equals(login) && l.get(i).getMdp().equals(mdp) && l.get(i).getRole().equals("admin")) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/FXML_menu.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                coach.Coach.st.close();
                stage.show();
                coach.Coach.st = stage;
                Stage stage1 = (Stage) achor.getScene().getWindow();
                // do what you have to do
                stage1.close();

            } else if (l.get(i).getLogin().equals(login) && l.get(i).getMdp().equals(mdp) && l.get(i).getRole().equals("user")) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/FXML_coach1.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                coach.Coach.st.close();
                stage.show();
                coach.Coach.st = stage;
                Stage stage1 = (Stage) achor.getScene().getWindow();
                // do what you have to do
                stage1.close();

            } else if (l.get(i).getLogin().equals(login) && l.get(i).getMdp().equals(mdp) && l.get(i).getRole().equals("super")) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/FXML_Super.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                coach.Coach.st.close();
                stage.show();
                coach.Coach.st = stage;
                Stage stage1 = (Stage) achor.getScene().getWindow();
                // do what you have to do
                stage1.close();

            } else {
                System.out.println("access denied");
            }
            username_text.setText("");
            mdp_text.setText("");

            /*  FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/FXML_login.fxml")); 
             Stage root1 = (Stage) fxmlLoader.load();
             root1.close();*/
        }

    }

}
