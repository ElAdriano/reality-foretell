package GUI.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartMenu {

    @FXML
    private Button startButton;

    @FXML
    private Button quitButton;

    @FXML
    private void onStartButtonClick(javafx.event.ActionEvent actionEvent) {
        Stage nextStage = null;
        try {
            nextStage = FXMLLoader.load(getClass().getResource("../Templates/DataForm.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        Stage renderedStage = (Stage) startButton.getScene().getWindow();

        renderedStage.setTitle(nextStage.getTitle());
        renderedStage.setScene(nextStage.getScene());
    }

    @FXML
    private void onQuitButtonClick(javafx.event.ActionEvent actionEvent) {
        Stage renderedStage = (Stage) quitButton.getScene().getWindow();
        renderedStage.close();
    }

}
