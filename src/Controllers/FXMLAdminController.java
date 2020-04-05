/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Exercice;
import Entities.Programme;
import static Métier.mertier_programme.GetConnection;
import coach.Coach;
import static coach.Coach.s;
import static coach.Coach.st;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import sun.net.dns.ResolverConfiguration;

/**
 * FXML Controller class
 *
 * @author Medali.benromdhane
 */
public class FXMLAdminController implements Initializable {

    String path = "";
    final FileChooser fileChooser = new FileChooser();

    final TextArea textArea = new TextArea();
    public Scene s;
    public Stage st;
    @FXML
    private TextField text_libellé_prog;
    @FXML
    private TextField text_rep_debutant;
    @FXML
    private TextField text_rep_intermediaire;
    @FXML
    private TextField text_rep_expert;
    @FXML
    private Button button_ajout;
    @FXML
    private TableView<Programme> tableViewProg;
    @FXML
    private TableColumn<Programme, String> col_libellé;
    @FXML
    private TableColumn<Programme, Integer> col_deb;
    @FXML
    private TableColumn<Programme, Integer> col_int;
    @FXML
    private TableColumn<Programme, Integer> col_exp;
    @FXML
    private TextField text_libellé_exercice;
    @FXML
    private ComboBox<String> prog_combo;
    ObservableList<String> items = FXCollections.observableArrayList();
    @FXML
    private TextField rep_debutant_txt;
    @FXML
    private TextField rep_intermidiare_txt;
    @FXML
    private TextField rep_expert_txt;
    @FXML
    private Button add_ex;
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
    private Button button_Logout;
    @FXML
    private MediaView mediaview;
    private MediaPlayer mediaPlayer;
    @FXML
    private Button test_Buton;
    @FXML
    private Button chooseVideo;
    @FXML
    private Label label_path;
    @FXML
    private AnchorPane anchor;

    // prog_combo.setItems(items);
    //final ComboBox prog_combo = new ComboBox(options);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            prog_combo.setItems(getObservableListIdProgramme());
            buildData();
            buildData1();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onclick_button_ajout(ActionEvent event) throws SQLException {
        if (text_libellé_prog.getText() != "" && text_rep_debutant.getText() != "" && text_rep_intermediaire.getText() != "") {
            Métier.mertier_programme.add_prog(text_libellé_prog.getText(), Integer.parseInt(text_rep_debutant.getText()), Integer.parseInt(text_rep_intermediaire.getText()), Integer.parseInt(text_rep_expert.getText()));
            text_libellé_prog.setText("");
            text_rep_debutant.setText("");
            text_rep_intermediaire.setText("");
            text_rep_expert.setText("");

            buildData();
        } else {
            System.out.println(" remplir tt les champs");
        }
    }

    private void buildData() throws SQLException {

        col_libellé.setCellValueFactory(new PropertyValueFactory<Programme, String>("Libellé"));
        col_deb.setCellValueFactory(new PropertyValueFactory<Programme, Integer>("Rep_debutant"));
        col_int.setCellValueFactory(new PropertyValueFactory<Programme, Integer>("Rep_intermediaire"));
        col_exp.setCellValueFactory(new PropertyValueFactory<Programme, Integer>("Rep_expert"));

        tableViewProg.getItems().setAll(Métier.mertier_programme.Afficher_Prog());

    }

    private void buildData1() throws SQLException {

        col_libellé1.setCellValueFactory(new PropertyValueFactory<Exercice, String>("Libellé"));
        col_prog1.setCellValueFactory(new Callback<CellDataFeatures<Exercice, String>, ObservableValue<String>>() {

            public ObservableValue<String> call(CellDataFeatures<Exercice, String> param) {
                return new SimpleStringProperty(param.getValue().getLibellé());
            }
        });
        col_deb1.setCellValueFactory(new PropertyValueFactory<Exercice, Integer>("rep_debutant"));
        col_int1.setCellValueFactory(new PropertyValueFactory<Exercice, Integer>("rep_intermidiare"));
        col_expert1.setCellValueFactory(new PropertyValueFactory<Exercice, Integer>("rep_expert"));

        tableViewEx.getItems().setAll(Métier.metier_exercice.Afficher_exercice1());

    }

    public static ObservableList<String> getObservableListIdProgramme() throws SQLException {
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
    private void CHOOSE(ActionEvent event) {
        //System.out.println("la choose");
        System.out.println(prog_combo.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void On_add_ex(ActionEvent event) throws SQLException {
        if (text_libellé_prog.getText() != "" && text_rep_debutant.getText() != "" && text_rep_intermediaire.getText() != "" && text_rep_expert.getText() != "" && prog_combo.getSelectionModel().getSelectedItem().toString() != "") {
            System.out.println("label_path.getText()on  dd:" + label_path.getText());
            Métier.metier_exercice.add_exercice(text_libellé_exercice.getText(), retrunIdProg(prog_combo.getSelectionModel().getSelectedItem().toString()), Integer.parseInt(rep_debutant_txt.getText()), Integer.parseInt(rep_intermidiare_txt.getText()), Integer.parseInt(rep_expert_txt.getText()), label_path.getText());

            text_libellé_prog.setText("");
            text_rep_debutant.setText("");
            text_rep_intermediaire.setText("");
            text_rep_expert.setText("");
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

    void test() {
        System.out.println("teeeeeeeeeeeeeeeeeeeest");
    }

    @FXML
    private void OnChooseEx(MouseEvent event) {
        // System.out.println(tableViewEx.getSelectionModel().getSelectedItem().getVideo());
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

    @FXML
    private void ontest(ActionEvent event) throws IOException, InterruptedException {
        /*Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXMLPopup.fxml"));

        s = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(s);
        st = stage;
       // st.show();
        //TimeUnit.SECONDS.sleep(5);
       // st.close();
        Window parent = root.getScene().getWindow();
        Popup n = new Popup();
        System.out.println("fir");
        n.show(parent);*/
Popup popup = new Popup();
FXMLPopupController controller = new FXMLPopupController();
FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FXMLPopup.fxml"));
loader.setController(controller);
popup.getContent().add((Parent)loader.load());
    }
    private Desktop desktop = Desktop.getDesktop();

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

}
