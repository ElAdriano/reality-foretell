package GUI.Controllers;

import Management.SchemeGenerator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

public class DimensionsForm {

    private final double DEFAULT_LAYOUT_Y = 83;
    private final double INITIAL_HEIGHT = 120;

    @FXML
    private Slider aDimensionSlider;
    @FXML
    private Slider bDimensionSlider;
    @FXML
    private Slider cDimensionSlider;
    @FXML
    private Slider dDimensionSlider;

    @FXML
    private Label aDimensionMonitor;
    @FXML
    private Label bDimensionMonitor;
    @FXML
    private Label cDimensionMonitor;
    @FXML
    private Label dDimensionMonitor;
    @FXML
    private Button startProcessButton;
    @FXML
    private Pane prisonBuilding;
    @FXML
    private Pane outSidePrisonBuilding;
    @FXML
    private Pane proTipWindow;
    @FXML
    private Pane backArrowViewIcon;
    private Background backArrowNotHover;
    private Background backArrowHover;

    private DecimalFormat sizeFormat;

    public void initialize() {
        sizeFormat = new DecimalFormat("0.00");
        getEnteredDataFromCache();

        proTipWindow.setVisible(false);
        backArrowNotHover = new Background(new BackgroundImage(new Image("GUI/Backgrounds/BackArrowCircle.png"), null, null, null, null));
        backArrowHover = new Background(new BackgroundImage(new Image("GUI/Backgrounds/BackArrowHoverCircle.png"), null, null, null, null));
        backArrowViewIcon.setBackground(backArrowNotHover);

        aDimensionSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            aDimensionSlider.setValue((double) newValue);
            aDimensionMonitor.setText(sizeFormat.format(newValue));
            prisonBuilding.setLayoutY(DEFAULT_LAYOUT_Y - ((double) newValue - INITIAL_HEIGHT) / 2);
            prisonBuilding.setPrefHeight((double) newValue);
            outSidePrisonBuilding.setMaxHeight((double) newValue - 20);

            if (aDimensionSlider.getValue() - cDimensionSlider.getValue() < 20) {
                cDimensionSlider.setValue((double) newValue - 20);
                cDimensionMonitor.setText(sizeFormat.format(cDimensionSlider.getValue()));
                outSidePrisonBuilding.setPrefHeight((double) newValue - 20);
            }
        });

        bDimensionSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            bDimensionSlider.setValueChanging(true);

            bDimensionSlider.setValue((double) newValue);
            bDimensionMonitor.setText(sizeFormat.format((double) newValue));

            prisonBuilding.setPrefWidth((double) newValue);
            prisonBuilding.setLayoutX(prisonBuilding.getLayoutX() - ((double) newValue - (double) oldValue) / 2);

            outSidePrisonBuilding.setPrefWidth((double) newValue - outSidePrisonBuilding.getLayoutX());
            dDimensionSlider.setValue((double) newValue - outSidePrisonBuilding.getLayoutX());
            dDimensionMonitor.setText(sizeFormat.format(dDimensionSlider.getValue()));

            if ((double) newValue - outSidePrisonBuilding.getLayoutX() > dDimensionSlider.getMax()) {
                prisonBuilding.setPrefWidth(outSidePrisonBuilding.getLayoutX() + dDimensionSlider.getMax());
                bDimensionSlider.setValue(outSidePrisonBuilding.getLayoutX() + dDimensionSlider.getMax());
                bDimensionMonitor.setText(sizeFormat.format(bDimensionSlider.getValue()));
            }

            if ((double) newValue - outSidePrisonBuilding.getLayoutX() <= 20) {
                outSidePrisonBuilding.setPrefWidth(20);
                outSidePrisonBuilding.setLayoutX((double) newValue - 20);
                dDimensionSlider.setValue(20);
                dDimensionMonitor.setText(sizeFormat.format(dDimensionSlider.getValue()));
            }

            bDimensionSlider.setValueChanging(false);
        });

        cDimensionSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (!aDimensionSlider.isValueChanging()) {
                if ((double) newValue <= outSidePrisonBuilding.getMaxHeight()) {
                    outSidePrisonBuilding.setPrefHeight((double) newValue);
                } else {
                    cDimensionSlider.setValue(outSidePrisonBuilding.getMaxHeight());
                }
                cDimensionMonitor.setText(String.valueOf(sizeFormat.format(cDimensionSlider.getValue())));
            }
        });

        dDimensionSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (!bDimensionSlider.isValueChanging()) {
                if (prisonBuilding.getWidth() - (double) newValue >= 20) {
                    outSidePrisonBuilding.setPrefWidth((double) newValue);
                    outSidePrisonBuilding.setLayoutX(prisonBuilding.getWidth() - (double) newValue);
                    dDimensionSlider.setValue((double) newValue);
                    dDimensionMonitor.setText(sizeFormat.format((double) newValue));
                } else {
                    outSidePrisonBuilding.setPrefWidth(prisonBuilding.getWidth() - 20);
                    outSidePrisonBuilding.setLayoutX(20);
                    dDimensionSlider.setValue(prisonBuilding.getWidth() - 20);
                    dDimensionMonitor.setText(sizeFormat.format(prisonBuilding.getWidth() - 20));
                }
            }
        });
    }

    private void getEnteredDataFromCache() {
        loadValues(aDimensionSlider, aDimensionMonitor, SchemeGenerator.conditions.aDimensionOfPrison);
        loadValues(bDimensionSlider, bDimensionMonitor, SchemeGenerator.conditions.bDimensionOfPrison);
        loadValues(cDimensionSlider, cDimensionMonitor, SchemeGenerator.conditions.cDimensionOfPrison);
        loadValues(dDimensionSlider, dDimensionMonitor, SchemeGenerator.conditions.dDimensionOfPrison);

        prisonBuilding.setLayoutY(prisonBuilding.getLayoutY() - (aDimensionSlider.getValue() - prisonBuilding.getPrefHeight()) / 2);
        prisonBuilding.setPrefHeight(aDimensionSlider.getValue());

        prisonBuilding.setLayoutX(prisonBuilding.getLayoutX() - (bDimensionSlider.getValue() - prisonBuilding.getPrefWidth()) / 2);
        prisonBuilding.setPrefWidth(bDimensionSlider.getValue());

        outSidePrisonBuilding.setPrefHeight(cDimensionSlider.getValue());

        outSidePrisonBuilding.setPrefWidth(dDimensionSlider.getValue());
        outSidePrisonBuilding.setLayoutX(prisonBuilding.getPrefWidth() - dDimensionSlider.getValue());
    }

    private void loadValues(Slider slider, Label monitor, double value) {
        double valueToSetForSlider = value;
        if (valueToSetForSlider == -1) {
            valueToSetForSlider = (slider.getMin() + slider.getMax()) / 2;
        }
        slider.setValue(valueToSetForSlider);
        monitor.setText(sizeFormat.format(slider.getValue()));
    }

    @FXML
    private void onStartProcessButtonClick() {
        Stage nextStage = null;
        try {
            saveSlidersData();
            nextStage = FXMLLoader.load(getClass().getResource("/GUI/Templates/ShowResults.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        Stage renderedStage = (Stage) startProcessButton.getScene().getWindow();
        renderedStage.setScene(nextStage.getScene());
    }

    @FXML
    private void closeProTipWindow() {
        proTipWindow.setVisible(false);
    }

    @FXML
    private void openProTipWindow() {
        proTipWindow.setVisible(true);
    }

    private void saveSlidersData() {
        SchemeGenerator.conditions.aDimensionOfPrison = Math.round(100.0 * aDimensionSlider.getValue()) / 100.0;
        SchemeGenerator.conditions.bDimensionOfPrison = Math.round(100.0 * bDimensionSlider.getValue()) / 100.0;
        SchemeGenerator.conditions.cDimensionOfPrison = Math.round(100.0 * cDimensionSlider.getValue()) / 100.0;
        SchemeGenerator.conditions.dDimensionOfPrison = Math.round(100.0 * dDimensionSlider.getValue()) / 100.0;
    }

    @FXML
    public void onBackArrowButtonMouseEntered() {
        changeBackground(backArrowHover);
    }

    @FXML
    public void onBackArrowButtonMouseExited() {
        changeBackground(backArrowNotHover);
    }

    private void changeBackground(Background background) {
        backArrowViewIcon.setBackground(background);
    }

    @FXML
    public void onBackArrowButtonMouseClicked() {
        saveSlidersData();
        Stage nextStage = null;
        try {
            nextStage = FXMLLoader.load(getClass().getResource("/GUI/Templates/DataForm.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        Stage renderedStage = (Stage) startProcessButton.getScene().getWindow();
        renderedStage.setScene(nextStage.getScene());
    }

}
