package Management;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Master extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = FXMLLoader.load(getClass().getResource("/GUI/Templates/StartMenu.fxml"));

        primaryStage.setScene(stage.getScene());
        primaryStage.setTitle(stage.getTitle());
        primaryStage.setResizable(false);
        primaryStage.setWidth(606);
        primaryStage.setHeight(450);
        primaryStage.getIcons().add(new Image("/GUI/Backgrounds/RealityForetellIcon.png"));
        primaryStage.show();
    }
}
