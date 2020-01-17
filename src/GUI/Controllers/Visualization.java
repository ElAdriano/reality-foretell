package GUI.Controllers;

import Management.ImageHolder;
import Management.SchemeGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;

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

    public Visualization() throws InterruptedException {
        System.out.println("Visualization");
        SchemeGenerator schemeGenerator = new SchemeGenerator();
        schemeGenerator.start();
        schemeGenerator.join();
    }

    public void initialize() {
        System.out.println("initialize");
        currentSchemeRenderedIndex = 0;
        updateComponents();
    }

    private void showSavedConditions() {
        System.out.println("maxBudget : " + SchemeGenerator.conditions.budget);
        System.out.println("amountOfPrisoners : " + SchemeGenerator.conditions.amountOfPrisoners);
        System.out.println("priceOfPrisonWard : " + SchemeGenerator.conditions.priceOfPrisonWard);
        System.out.println("maxAmountOfPrisonersInPrisonWard : " + SchemeGenerator.conditions.maxAmountOfPrisonersInPrisonWard);
        System.out.println("priceOfSanitaryNook : " + SchemeGenerator.conditions.priceOfSanitaryNook);
        System.out.println("cameraRange : " + SchemeGenerator.conditions.cameraRange);
        System.out.println("amountOfGenerations : " + SchemeGenerator.conditions.amountOfGenerations);
        System.out.println("aDimensionOfPrison : " + SchemeGenerator.conditions.aDimensionOfPrison);
        System.out.println("bDimensionOfPrison : " + SchemeGenerator.conditions.bDimensionOfPrison);
        System.out.println("cDimensionOfPrison : " + SchemeGenerator.conditions.cDimensionOfPrison);
        System.out.println("dDimensionOfPrison : " + SchemeGenerator.conditions.dDimensionOfPrison);
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

            schemeView.setBackground(ImageHolder.getBackground(currentSchemeRenderedIndex));
        }
    }

    private void disableButton(ActionEvent actionEvent) {
        Button sourceButton = (Button) actionEvent.getSource();
        sourceButton.setDisable(true);
    }
}
