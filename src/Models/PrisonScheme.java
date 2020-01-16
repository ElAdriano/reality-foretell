package Models;

import Management.Fields;
import Management.ImageCreator;
import Management.SchemeGenerator;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class PrisonScheme {
    private ArrayList<PrisonWard> prisonWards;
    private ArrayList<Door> doorsInScheme;
    private ArrayList<LightBulb> lightBulbsInScheme;
    private ArrayList<Camera> camerasInScheme;
    private ArrayList<Window> windowsInScheme;

    private MonitoringRoom monitoringRoom;
    private SanitaryNook sanitaryNook;

    private Image imageToShow;
    private Fields[][] prisonPlan;
    private int planSquareSize;

    private double rate;

    public PrisonScheme() {
        this.camerasInScheme = new ArrayList<>();
        this.doorsInScheme = new ArrayList<>();
        this.lightBulbsInScheme = new ArrayList<>();
        this.camerasInScheme = new ArrayList<>();
        this.windowsInScheme = new ArrayList<>();

        this.planSquareSize = (int) Math.max(SchemeGenerator.conditions.bDimensionOfPrison, SchemeGenerator.conditions.aDimensionOfPrison);

        prisonPlan = new Fields[planSquareSize][planSquareSize];

        fillAsOutsideArea();
        fillAsCorridor();

        //TODO create PrisonScheme instance
    }

    private void fillAsCorridor() {
        for (int w = 0; w < planSquareSize; w++) {
            for (int h = 0; h < planSquareSize; h++) {
                if (!(w >= (int) SchemeGenerator.conditions.bDimensionOfPrison - (int) SchemeGenerator.conditions.dDimensionOfPrison
                        && h < (int) SchemeGenerator.conditions.cDimensionOfPrison)) {
                    prisonPlan[w][h] = Fields.CORRIDOR;
                }
            }
        }
    }

    private void fillAsOutsideArea() {
        for (int i = 0; i < planSquareSize; i++) {
            for (int j = 0; j < planSquareSize; j++) {
                prisonPlan[i][j] = Fields.OUTSIDE_FIELD;
            }
        }
    }

    private void createImageToShow() {
        imageToShow = ImageCreator.createImage(prisonPlan, planSquareSize, planSquareSize);
    }

    public void arrangePrisonScheme() {
        //TODO initializing PrisonScheme components and whole prison
    }

    public void updateImage(Image image) {
        imageToShow = image;
    }

    public Image getImageToShow() {
        return imageToShow;
    }

    public int getPlanSquareSize() {
        return planSquareSize;
    }

    public double getRate() {
        return rate;
    }

    public Fields[][] getPrisonPlan() {
        return prisonPlan;
    }
}
