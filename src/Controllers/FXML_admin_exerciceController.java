/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.FXMLAdminController.getObservableListIdProgramme;
import Entities.Exercice;
import static Métier.mertier_programme.GetConnection;
import coach.Coach;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class FXML_admin_exerciceController implements Initializable {

    String path = "";
    final FileChooser fileChooser = new FileChooser();

    final TextArea textArea = new TextArea();
    public Scene s;
    public Stage st;
    @FXML
    private Button test_Buton;
    @FXML
    private Label label_path;
    @FXML
    private Button chooseVideo;
    @FXML
    private MediaView mediaview;
    private MediaPlayer mediaPlayer;

    @FXML
    private Button button_Logout;
    @FXML
    private TableView<Exercice> tableViewEx;

    @FXML
    private TableColumn<Exercice, String> col_libellé1;
    @FXML
    private TableColumn<Exercice, String> col_prog1;
    @FXML
    private TableColumn<Exercice, Integer> col_deb1;
    @FXML
    private TableColumn<Exercice, Integer> col_int1;
    @FXML
    private TableColumn<Exercice, Integer> col_expert1;
    @FXML
    private Button add_ex;
    @FXML
    private TextField rep_expert_txt;
    @FXML
    private TextField rep_intermidiare_txt;
    @FXML
    private ComboBox<String> prog_combo;
    @FXML
    private TextField text_libellé_exercice;
    @FXML
    private TextField rep_debutant_txt;
    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            prog_combo.setItems(getObservableListIdProgramme());
           
            buildData1();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(FXML_admin_exerciceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        private void buildData1() throws SQLException {

        col_libellé1.setCellValueFactory(new PropertyValueFactory<Exercice, String>("Libellé"));
        col_prog1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Exercice, String>, ObservableValue<String>>() {

            public ObservableValue<String> call(TableColumn.CellDataFeatures<Exercice, String> param) {
                return new SimpleStringProperty(param.getValue().getLibellé());
            }
        });
        col_deb1.setCellValueFactory(new PropertyValueFactory<Exercice, Integer>("rep_debutant"));
        col_int1.setCellValueFactory(new PropertyValueFactory<Exercice, Integer>("rep_intermidiare"));
        col_expert1.setCellValueFactory(new PropertyValueFactory<Exercice, Integer>("rep_expert"));

        tableViewEx.getItems().setAll(Métier.metier_exercice.Afficher_exercice1());

    }

    @FXML
    private void ontest(ActionEvent event) {
    }

    @FXML
    private void onClickChooseVid(ActionEvent event) {
        File file = fileChooser.showOpenDialog(Coach.st);
        if (file != null) {
            //openFile(file);
            List<File> files = Arrays.asList(file);
            printLog(textArea, files);
            path = file.getPath();
            label_path.setText(path);
            System.out.println(path);
        }
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
    private void OnChooseEx(MouseEvent event) {
        String file = tableViewEx.getSelectionModel().getSelectedItem().getVideo();
        if (file != null) {
            initPlayer(file);
        }
    }

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
    private Desktop desktop = Desktop.getDesktop();

    public void openFile(File file) {
        try {
            this.desktop.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printLog(TextArea textArea, List<File> files) {
        if (files == null || files.isEmpty()) {
            return;
        }
        for (File file : files) {
            textArea.appendText(file.getAbsolutePath() + "\n");
        }

    }

    @FXML
    private void On_add_ex(ActionEvent event) throws SQLException {
                if (text_libellé_exercice.getText() != "" && rep_debutant_txt.getText() != "" && rep_intermidiare_txt.getText() != "" && rep_expert_txt.getText() != "" && prog_combo.getSelectionModel().getSelectedItem().toString() != "") {
            System.out.println("label_path.getText()on  dd:" + label_path.getText());
            Métier.metier_exercice.add_exercice(text_libellé_exercice.getText(), retrunIdProg(prog_combo.getSelectionModel().getSelectedItem().toString()), Integer.parseInt(rep_debutant_txt.getText()), Integer.parseInt(rep_intermidiare_txt.getText()), Integer.parseInt(rep_expert_txt.getText()), label_path.getText());

            text_libellé_exercice.setText("");
            rep_debutant_txt.setText("");
            rep_intermidiare_txt.setText("");
            rep_expert_txt.setText("");
            label_path.setText("");

            buildData1();
        } else {
            System.out.println(" remplir tt les champs");
        }
    }
        public int retrunIdProg(String str) throws SQLException {
        Connection con;
        int f = 0;
        con = GetConnection();
        String sql = "SELECT * FROM programme where Libellé=? ";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, str);
        ResultSetMetaData resultMeta = statement.getMetaData();
        ResultSet result = statement.executeQuery();
        while (result.next()) {

            f = result.getInt("id");

        }
        System.out.println("voila f :" + f);
        return f;
    }

    @FXML
    private void CHOOSE(ActionEvent event) {
    }

}
