package uet.oop.bomberman.Menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.music.Sound;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    public AnchorPane pane;
    private static Stage stageMenu = new Stage();
    private static Stage stageLV1 = new Stage();
    public static Scene sceneMenu;
    public static Scene sceneControl;
    public static Scene sceneLV1;
    public static Scene sceneWin;
    private Stage stage;
    private static boolean OnMusic = false;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    public void NewGame() throws IOException {
        GoToLevelScene();
    }

    @FXML
    public void GoToLv1Map() throws IOException {
        Sound.StageStart();
        BombermanGame.stageLV1.show();
    }
    public void GoToLevelScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LV1.fxml"));
        sceneLV1 = new Scene(root);
        stageMenu.setScene(sceneLV1);
    }

    @FXML
    public void Control() throws IOException {
        GoToControl();
    }
    private void GoToControl() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/GoToControl.fxml"));
        sceneControl = new Scene(root);
        stageMenu.setScene(sceneControl);
        stageMenu.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void RunMenu(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MenuScene.fxml"));
        sceneMenu = new Scene(root);
        stageMenu.setScene(sceneMenu);
        stageMenu.show();
    }
    public void ReturnMenu() {
        stageMenu.setScene(sceneMenu);
        stageMenu.show();
    }

    public static void RunSceneWin() throws IOException {
        Parent root = FXMLLoader.load(MenuController.class.getResource("/fxml/Win.fxml"));
        sceneWin = new Scene(root);
        stageLV1.setScene(sceneWin);
        stageLV1.show();
    }

    @FXML
    public void OnOffMusic() {
        if(OnMusic == false) {
            Sound.onPlayAudio();
            OnMusic = true;
        }
        else {
            Sound.offPlayAudio();
            OnMusic = false;
        }
    }
}