package GUI.Controllers;

import Management.ImageHolder;
import Management.SchemeGenerator;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Visualization {

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label progressLabel;
    @FXML
    private int currentSchemeRenderedIndex;
    @FXML
    private Pane schemeView;

    @FXML
    private Button nextImageButton;
    @FXML
    private Button previousImageButton;

    @FXML
    private Label parameterInfoLabel;

    public Visualization() throws InterruptedException {
        SchemeGenerator schemeGenerator = new SchemeGenerator();
        schemeGenerator.start();
        schemeGenerator.join();
    }

    public void initialize() {
        currentSchemeRenderedIndex = 0;
        updateComponents();
    }

    @FXML
    private void saveCurrentlyShowedImage() throws IOException {
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "schemes/";
        File schemesDirectory = new File(path);

        if (!schemesDirectory.exists()) {
            schemesDirectory.mkdir();
        }

        Image imageToSave = ImageHolder.getImageByIndex(currentSchemeRenderedIndex);
        String fileName = "Scheme_" + (currentSchemeRenderedIndex + 1);

        String extension = "png";
        File savedFile = new File(path + fileName + "." + extension);
        ImageIO.write(SwingFXUtils.fromFXImage(imageToSave, null), extension, savedFile);
    }

    @FXML
    public void onNextImageButtonClick(ActionEvent actionEvent) {
        int amountOfImages = ImageHolder.getGeneratedSchemesAmount();
        currentSchemeRenderedIndex++;
        if (currentSchemeRenderedIndex + 1 >= amountOfImages) {
            disableButton(actionEvent);
        }
        if (currentSchemeRenderedIndex < amountOfImages) {
            updateComponents();
            previousImageButton.setDisable(false);
        }
    }

    @FXML
    public void onPreviousImageButton(ActionEvent actionEvent) {
        currentSchemeRenderedIndex--;
        if (currentSchemeRenderedIndex - 1 < 0) {
            disableButton(actionEvent);
        }
        if (currentSchemeRenderedIndex >= 0) {
            updateComponents();
            nextImageButton.setDisable(false);
        }
    }

    private void updateComponents() {
        int amountOfSchemes = ImageHolder.getGeneratedSchemesAmount();
        if (amountOfSchemes == 0) {
            return;
        } else {
            progressBar.setProgress((double) (currentSchemeRenderedIndex + 1) / amountOfSchemes);
            progressLabel.setText((currentSchemeRenderedIndex + 1) + " / " + amountOfSchemes);

            parameterInfoLabel.setText("Wymiary budynku:\nWysokość : " + SchemeGenerator.conditions.aDimensionOfPrison + "\n" +
                    "Szerokość : " + SchemeGenerator.conditions.bDimensionOfPrison + "\n" +
                    "Cena budowy : " + ImageHolder.getPriceToImage(currentSchemeRenderedIndex));
            schemeView.setBackground(ImageHolder.getBackground(currentSchemeRenderedIndex));
        }
    }

    private void disableButton(ActionEvent actionEvent) {
        Button sourceButton = (Button) actionEvent.getSource();
        sourceButton.setDisable(true);
    }
}
