/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Exercice;
import Entities.Programme;
import static Métier.mertier_programme.GetConnection;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Firas.SAADAOUI
 */
public class FXML_coach1Controller implements Initializable {

    ObservableList<String> items = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> prog_combo;
    @FXML
    private TableView<Exercice> ListExercice;
    @FXML
    private TableColumn<Exercice, String> Libellé_exercice;
    @FXML
    private CheckBox Débutant;
    @FXML
    private CheckBox Intermediaire;
    @FXML
    private CheckBox Expert;
    @FXML
    private TableColumn<Exercice, Integer> Répitition_n;
    @FXML
    private Button button_Logout;
    @FXML
    private MediaView mediaview;
    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            prog_combo.setItems(getObservableList());
        } catch (SQLException ex) {
            Logger.getLogger(FXML_coach1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onChoose(ActionEvent event) throws SQLException {
        int f = 0;
        List<Programme> l = new ArrayList<Programme>();
        // System.out.println(prog_combo.getSelectionModel().getSelectedItem().toString());
        Connection con;
        con = GetConnection();
        String sql = "SELECT * FROM programme where Libellé=? ";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, prog_combo.getSelectionModel().getSelectedItem().toString());
        ResultSetMetaData resultMeta = statement.getMetaData();
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            Programme e = new Programme();
            e.setId(result.getInt("id"));
            f = result.getInt("id");
            e.setLibellé(result.getString("Libellé"));
            e.setRep_debutant(result.getInt("Rep_debutant"));
            e.setRep_intermediaire(result.getInt("Rep_intermediaire"));
            e.setRep_expert(result.getInt("Rep_expert"));

            l.add(e);
        }
        /* for (int j = 0; j < l.size(); j++) {
         System.out.println(l.get(j).toString());
         }*/

        if (Débutant.isSelected()) {
            System.out.println("Débutant");
            Libellé_exercice.setCellValueFactory(new PropertyValueFactory<Exercice, String>("Libellé"));
            Répitition_n.setCellValueFactory(new PropertyValueFactory<Exercice, Integer>("rep_debutant"));

            ListExercice.getItems().setAll(Métier.metier_exercice.getListDeb(Métier.mertier_programme.getExByProg(f), (Métier.mertier_programme.getExByProg(f).size()) - 4));
        } else if (Intermediaire.isSelected()) {
            System.out.println("Intermediaire");
            Libellé_exercice.setCellValueFactory(new PropertyValueFactory<Exercice, String>("Libellé"));
            Répitition_n.setCellValueFactory(new PropertyValueFactory<Exercice, Integer>("rep_intermidiare"));

            ListExercice.getItems().setAll(Métier.metier_exercice.getListDeb(Métier.mertier_programme.getExByProg(f), (Métier.mertier_programme.getExByProg(f).size()) - 3));

        } else if (Expert.isSelected()) {
            System.out.println("Expert");
            Libellé_exercice.setCellValueFactory(new PropertyValueFactory<Exercice, String>("Libellé"));
            Répitition_n.setCellValueFactory(new PropertyValueFactory<Exercice, Integer>("rep_expert"));

            ListExercice.getItems().setAll(Métier.metier_exercice.getListDeb(Métier.mertier_programme.getExByProg(f), (Métier.mertier_programme.getExByProg(f).size() - 1)));
        } else {
            System.out.println("choisir un niveau");
        }
    }

    public static ObservableList<String> getObservableList() throws SQLException {
        List<String> l = new ArrayList<String>();
        Connection con;
        con = GetConnection();
        String sql = "SELECT * FROM programme ";
        PreparedStatement statement = con.prepareStatement(sql);

        ResultSetMetaData resultMeta = statement.getMetaData();
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            // Exercice e = new Exercice();
            // e.setId(result.getInt("id"));
            //e.setLibellé(result.getString("Libellé"));
            // e.setProgramme(result.getInt("programme"));

            l.add(result.getString("Libellé"));
        }
        /* for (int j = 0; j < l.size(); j++) {
         System.out.println(l.get(j).toString());
         }*/
        ObservableList<String> l1 = FXCollections.observableArrayList(l);
        return l1;

    }

    @FXML
    private void Débutant(ActionEvent event) {
        Intermediaire.setSelected(false);
        Expert.setSelected(false);
        prog_combo.setValue("");
    }

    @FXML
    private void Intermediaire(ActionEvent event) {
        Débutant.setSelected(false);
        Expert.setSelected(false);
        prog_combo.setValue("");
    }

    @FXML
    private void Expert(ActionEvent event) {
        Débutant.setSelected(false);
        Intermediaire.setSelected(false);
        prog_combo.setValue("");
    }

    private MediaPlayer mediaPlayer;

    private void initPlayer(String uri) {
        if (uri == null) {
            return;
        }
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = null;
        }
        Media media = new Media(Paths.get(uri).toUri().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaview.setMediaPlayer(mediaPlayer);
        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                System.out.println("video is running . . .");
            }
        });
    }

    @FXML
    private void OnButtonLogout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/FXML_login.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        coach.Coach.st.close();
        stage.show();
        Stage stage1 = (Stage) anchor.getScene().getWindow();
        // do what you have to do
        stage1.close();
    }

    @FXML
    private void ONclickExShowVid(MouseEvent event) {
        System.out.println(ListExercice.getSelectionModel().getSelectedItem().toString());
        String file = ListExercice.getSelectionModel().getSelectedItem().getVideo();
        if (file != null) {
            initPlayer(file);
        }
    }

}
